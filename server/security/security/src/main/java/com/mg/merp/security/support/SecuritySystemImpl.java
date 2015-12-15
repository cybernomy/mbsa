/*
 * SecuritySystemImpl.java
 *
 * Copyright (c) 1998 - 2006 BusinessTechnology, Ltd.
 * All rights reserved
 *
 * This program is the proprietary and confidential information
 * of BusinessTechnology, Ltd. and may be used and disclosed only
 * as authorized in a license agreement authorizing and
 * controlling such use and disclosure
 *
 * Millennium Business Suite Anywhere System.
 *
 */
package com.mg.merp.security.support;

import com.mg.framework.api.ApplicationException;
import com.mg.framework.api.Logger;
import com.mg.framework.api.UserProfile;
import com.mg.framework.api.jdbc.JdbcTemplate;
import com.mg.framework.api.jdbc.RowMapper;
import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Projections;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.security.BusinessMethodPermission;
import com.mg.framework.api.security.InvalidUserNameOrPassword;
import com.mg.framework.api.security.SecurityAuditEvent;
import com.mg.framework.api.security.SecurityAuditType;
import com.mg.framework.api.security.SecuritySystem;
import com.mg.framework.service.ApplicationDictionaryLocator;
import com.mg.framework.service.UserProfileImpl;
import com.mg.framework.support.ui.UIProducer;
import com.mg.framework.utils.DatabaseUtils;
import com.mg.framework.utils.JmsUtils;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.security.UserServiceLocal;
import com.mg.merp.security.model.LinkUsersGroups;
import com.mg.merp.security.model.MethodAccess;
import com.mg.merp.security.model.ModuleAccess;
import com.mg.merp.security.model.SecUser;
import com.mg.merp.security.support.ui.FolderPermissionsForm;

import net.sf.jguard.core.authentication.configuration.JGuardConfiguration;
import net.sf.jguard.core.authentication.credentials.JGuardCredential;
import net.sf.jguard.core.authorization.policy.LocalAccessController;
import net.sf.jguard.core.authorization.policy.MultipleAppPolicy;
import net.sf.jguard.ext.SecurityConstants;
import net.sf.jguard.ext.authentication.AuthenticationException;
import net.sf.jguard.ext.authentication.manager.AuthenticationHelper;
import net.sf.jguard.ext.authentication.manager.AuthenticationManager;
import net.sf.jguard.ext.authorization.AuthorizationHelper;
import net.sf.jguard.ext.authorization.manager.AuthorizationManager;
import net.sf.jguard.ext.util.CryptUtils;

import java.security.AccessControlException;
import java.security.AccessController;
import java.security.NoSuchAlgorithmException;
import java.security.Permission;
import java.security.PrivilegedAction;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginException;
import javax.transaction.TransactionManager;

/**
 * Реализация системы безопасности, использует jGuard как реализацию JAAS
 *
 * @author Oleg V. Safonov
 * @version $Id$
 */
public class SecuritySystemImpl implements SecuritySystem {
  private static final String STANDARTUSER_PRINCIPAL = "standartuser";
  private static final String LOGIN_CRED = "login";
  private static final String PASSWORD_CRED = "password";
  private Logger logger = ServerUtils.getLogger(SecuritySystemImpl.class);
  private AuthenticationManager authenticationManager = null;
  private AuthorizationManager authorizationManager = null;
  private String authenticationConfigurationLocation = "META-INF/jGuardAuthentication.xml";
  private String authorizationConfigurationLocation = "META-INF/jGuardAuthorization.xml";
  private String applicationName = JAASAuthenticationHolder.MBSA_SYSTEM;
  private JGuardConfiguration configuration;
  private MultipleAppPolicy policy;
  private LocalAccessController accessController;
  private String topicName = "topic/com/mg/jet/securityaudit";

  /* (non-Javadoc)
   * @see com.mg.framework.api.security.SecuritySystem#checkPermission(java.security.Permission)
   */
  public void checkPermission(final Permission perm) throws AccessControlException {
//		Subject caller = JAASAuthenticationHolder.getAuthenticationHolder(conf).getSubject();
//		Subject.doAsPrivileged(caller, new PrivilegedAction<Object>() {
//
//			public Object run() {
//				AccessController.checkPermission(perm);
//				return null;
//			}
//
//		},
//		AccessControlContextUtils.getSubjectOnlyAccessControlContext(caller));
//		Policy policy = Policy.getPolicy();
//		Principal[] principals = null;
//		if (caller != null)
//		{
//			// Get the caller principals
//			Set<Principal> principalsSet = caller.getPrincipals();
//			principals = new Principal[principalsSet.size()];
//			principalsSet.toArray(principals);
//		}
//		ProtectionDomain pd = new ProtectionDomain(null, null, getClass().getClassLoader(), principals);
//		if (!policy.getPermissions(pd).implies(perm))
//		{
//			//String msg = "Denied: " + perm + ", caller=" + caller;
//			String msg = String.format("Denied: %s", perm);
//			AccessControlException e = new AccessControlException(msg);
//			throw e;
//		}
    //TODO: в текущей реализации проверка прав на методы происходит старым методом унаследованным из
    //предыдущих версий системы, в следующих релизах будет переработан с использованием JAAS
    if (perm instanceof BusinessMethodPermission)
      checkBusinessMethodPermission((BusinessMethodPermission) perm);
    else
      Subject.doAsPrivileged(JAASAuthenticationHolder.getAuthenticationHolder(configuration).getSubject(), new PrivilegedAction<Object>() {
        public Object run() {
          if (accessController == null) {
            AccessController.checkPermission(perm);
          } else {
            accessController.checkPermission(perm);
          }
          // the 'null' tells the SecurityManager to consider this resource access
          //in an isolated context, ignoring the permissions of code currently
          //on the execution stack.
          return null;
        }
      }, null);
  }

  /**
   * унаследованная проверка прав на методы бизнес-компонентов, если отсутствует описание
   * бизнес-компонента или его метода, то будет нарушение прав, данное поведение принципиально
   * отличается от версии 2.х где права проверялись только для зарегистрированных методов
   */
  private void checkBusinessMethodPermission(final BusinessMethodPermission permission) throws AccessControlException {
    Map<String, Map<String, Boolean>> permsMap = ((UserProfileImpl) ServerUtils.getUserProfile()).getMethodsPermission();
    if (permsMap.size() == 0)
      throwAccessControlException(permission);
    Map<String, Boolean> perms = permsMap.get(permission.getName());

    if (perms != null) {
      Boolean perm = perms.get(permission.getActions());
      if (perm == null || !perm)
        throwAccessControlException(permission);
    } else
      throwAccessControlException(permission);
  }

  private void throwAccessControlException(final BusinessMethodPermission permission) {
    throw new AccessControlException("access denied " + permission, permission);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.security.SecuritySystem#authenticate(java.lang.String, java.lang.String)
   */
  public void authenticate(String login, String password)
      throws LoginException {
    //JAAS authentication
    JAASAuthenticationHolder.authenticate(login, password, configuration);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.security.SecuritySystem#logout()
   */
  public void logout() {
    //JAAS logout
    JAASAuthenticationHolder auth = JAASAuthenticationHolder.getAuthenticationHolder(configuration);
    if (auth != null)
      auth.logout();
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.security.SecuritySystem#loadUserProfile()
   */
  public UserProfile loadUserProfile(String login, Locale locale, Locale defaultLocale) {
    SecUser user = ((UserServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/Security/User")).findUser(login);
    if (user == null)
      throw new InvalidUserNameOrPassword();

    int[] groups = new int[user.getSecLinkUsersGroups().size()];

    List<String> modules = new ArrayList<String>();
    Map<String, Map<String, Boolean>> methodsPermissions = new HashMap<String, Map<String, Boolean>>();
    int index = 0;
    for (LinkUsersGroups link : user.getSecLinkUsersGroups()) {
      groups[index++] = link.getGroup().getId();
      //load subsystem access
      for (ModuleAccess access : link.getGroup().getSecModulesAccess())
        if (!modules.contains(access.getModule().getName()))
          modules.add(access.getModule().getName());
      //load methods permissions
      List<Object[]> mPerms = OrmTemplate.getInstance().findByCriteria(OrmTemplate.createCriteria(MethodAccess.class, "ma")
          .createAlias("ma.Method.SysClass", "sc").createAlias("ma.Method", "m")
          .setProjection(Projections.projectionList(Projections.property("sc.BeanName")
              , Projections.property("m.CorbaName"), Projections.property("ma.Permission")))
          .add(Restrictions.eq("ma.Group.Id", link.getGroup().getId())));
      for (Object[] mPerm : mPerms) {
        String beanName = ((String) mPerm[0]).replaceAll("/", ".").toUpperCase();//BasicPermission naming convention
        String methodName = (String) mPerm[1];
        boolean permission = (Boolean) mPerm[2];
        Map<String, Boolean> beanMethods = methodsPermissions.get(beanName);
        if (beanMethods == null) {
          beanMethods = new HashMap<String, Boolean>();
          methodsPermissions.put(beanName, beanMethods);
        }
        Boolean oldPerm = beanMethods.get(methodName);
        if (oldPerm == null)
          beanMethods.put(methodName, permission);
        else {
          //объединение прав из разных групп
          if (!oldPerm && permission)
            beanMethods.put(methodName, true);
        }
      }
    }

    UserProfileImpl result = new UserProfileImpl(user.getName(), user.getId(), groups, modules.toArray(new String[modules.size()]));
    result.setMethodsPermission(methodsPermissions);
    if (locale != null) {
      //if we take a locale, use it
      result.setLocale(locale);
    } else {
      //TODO load locale from user profile in DB
      result.setLocale(defaultLocale);
    }
    return result;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.security.SecuritySystem#login(java.lang.String, java.lang.String, boolean)
   */
  public Integer login(String name, String password, boolean smartCard)
      throws ApplicationException, InvalidUserNameOrPassword {
    // TODO Auto-generated method stub
    return null;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.security.SecuritySystem#getModuleAccess(java.lang.Integer)
   */
  public String[] getModuleAccess(Integer userId) throws ApplicationException {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * добавление прав в хранилище
   */
  private void createTreePerm(int folderIdentifier, int folderPart, int group) {
    JdbcTemplate.getInstance().update("insert into folder_rights (id, group_id, folder_id, rights, folderpart) values (?, ?, ?, ?, ?)", new Object[]{DatabaseUtils.getSequenceNextValue("folder_rights_id_gen"), group, folderIdentifier, Boolean.TRUE, folderPart});
  }

  /**
   * реализация установки прав на элемент иерархии
   */
  private void doSetTreePermission(int treeIdentifier, int parentIdentifier, int treePart) {
    //load parents permission
    List<Integer> parentPrems = JdbcTemplate.getInstance().query("select fr.group_id from folder_rights fr where (fr.folderpart = ?) and (fr.folder_id = ?) and (fr.rights = ?)", new Object[]{treePart, parentIdentifier, Boolean.TRUE}, new RowMapper<Integer>() {
      public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return rs.getInt(1);
      }
    });
    if (parentPrems.size() == 0) {
      //add admins
      createTreePerm(treeIdentifier, treePart, ADMIN_GROUP);
      //add user groups
      for (int group : ServerUtils.getUserProfile().getGroups()) {
        if (group != ADMIN_GROUP) //exclude admins duplicate
          createTreePerm(treeIdentifier, treePart, group);
      }
    } else {
      //copy from parent
      for (Integer folderPerm : parentPrems)
        createTreePerm(treeIdentifier, treePart, folderPerm);
    }
  }

  private void doCreateUser(String userName, String passw) {
    try {
      Subject subject = authenticationManager.findUser(userName);
      if (subject == null) {
        subject = new Subject();
        JGuardCredential cred = new JGuardCredential();
        try {
          cred.setId(LOGIN_CRED);
          cred.setValue(userName);
          subject.getPrivateCredentials().add(cred);

          cred = new JGuardCredential();
          cred.setId(PASSWORD_CRED);
          cred.setValue(new String(CryptUtils.cryptPassword(passw.toCharArray())));
          subject.getPrivateCredentials().add(cred);

          authenticationManager.createUser(subject);
          authenticationManager.addPrincipalToUser(subject, STANDARTUSER_PRINCIPAL, applicationName);
        } catch (NoSuchAlgorithmException e) {
          throw new ApplicationException(e);
        } catch (AuthenticationException e) {
          throw new ApplicationException(e);
        }
      }

      UserServiceLocal userService = ((UserServiceLocal) ApplicationDictionaryLocator.locate().getBusinessService("merp/Security/User"));
      SecUser user = userService.findUser(userName);
      if (user == null) {
        user = userService.initialize();
        user.setName(userName);
        user.setFullName(userName);
        userService.create(user);
      }
    } catch (RuntimeException e) {
      try {
        ServerUtils.getTransactionManager().setRollbackOnly();
      } catch (Exception tre) {
        logger.error("Create user exception", tre);
      }
      throw e;
    }
  }

  private void doDeleteUser(String userName) {
    try {
      Subject subject = authenticationManager.findUser(userName);
      if (subject != null)
        try {
          authenticationManager.deleteUser(subject);
        } catch (AuthenticationException e) {
          throw new ApplicationException(e);
        }
    } catch (RuntimeException e) {
      try {
        ServerUtils.getTransactionManager().setRollbackOnly();
      } catch (Exception tre) {
        logger.error("Delete user exception", tre);
      }
      throw e;
    }
  }

  private void doChangePassword(String userName, String passw) {
    try {
      Subject subject = authenticationManager.findUser(userName);
      if (subject != null)
        try {
          for (JGuardCredential cred : subject.getPrivateCredentials(JGuardCredential.class)) {
            if (cred.getId().equals(PASSWORD_CRED)) {
              try {
                cred.setValue(new String(CryptUtils.cryptPassword(passw.toCharArray())));
              } catch (NoSuchAlgorithmException e) {
                throw new ApplicationException(e);
              }

              JGuardCredential identityCred = new JGuardCredential();
              identityCred.setId(LOGIN_CRED);
              identityCred.setValue(userName);

              authenticationManager.updateUser(identityCred, subject);

              break;
            }
          }
        } catch (AuthenticationException e) {
          throw new ApplicationException(e);
        }
    } catch (RuntimeException e) {
      try {
        ServerUtils.getTransactionManager().setRollbackOnly();
      } catch (Exception tre) {
        logger.error("Change password exception", tre);
      }
      throw e;
    }
  }

  private void sendAuditMessage(SecurityAuditEvent message) {
    try {
      JmsUtils.sendObjectMessageToTopic(topicName, message);
    } catch (Exception e) {
      logger.error("security audit failed", e);
    }
  }

  @SuppressWarnings("unchecked")
  private void setupConfiguration(Map<String, Object> authenticationSettings, Map<String, Object> authorizationSettings) {
    String authenticationDatabaseFileLocation = null, authorizationDatabaseFileLocation = null;
    switch (DatabaseUtils.getDBMSType()) {
      case FIREBIRD:
      case INTERBASE:
        authenticationDatabaseFileLocation = "META-INF/authentication.interbase.properties";
        authorizationDatabaseFileLocation = "META-INF/authorization.interbase.properties";
        break;
      case POSTGRESQL:
        authenticationDatabaseFileLocation = "META-INF/authentication.postgresql.properties";
        authorizationDatabaseFileLocation = "META-INF/authorization.postgresql.properties";
        break;
      case ORACLE:
        authenticationDatabaseFileLocation = "META-INF/authentication.oracle.properties";
        authorizationDatabaseFileLocation = "META-INF/authorization.oracle.properties";
        break;
      case DB2:
        authenticationDatabaseFileLocation = "META-INF/authentication.db2.properties";
        authorizationDatabaseFileLocation = "META-INF/authorization.db2.properties";
        break;
    }

    if (authenticationDatabaseFileLocation != null) {
      Map<String, Object> authenticationManagerOptions = (Map<String, Object>) authenticationSettings.get(SecurityConstants.AUTHENTICATION_MANAGER_OPTIONS);
      if (authenticationManagerOptions != null)
        authenticationManagerOptions.put(SecurityConstants.AUTHENTICATION_DATABASE_FILE_LOCATION, authenticationDatabaseFileLocation);
    }
    if (authorizationDatabaseFileLocation != null)
      authorizationSettings.put(SecurityConstants.AUTHORIZATION_DATABASE_FILE_LOCATION, authorizationDatabaseFileLocation);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.security.SecuritySystem#setFolderPermission(int, int, int)
   */
  public void grantTreePermission(int treeIdentifier, int parentIdentifier, int treePart) {
    doSetTreePermission(treeIdentifier, parentIdentifier, treePart);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.security.SecuritySystem#showTreePermission(int, int)
   */
  public void setupTreePermission(int treeIdentifier, int treePart, String className, String parentProperty) {
    ((FolderPermissionsForm) UIProducer.produceForm("com/mg/merp/security/resources/FolderPermissionsForm.mfd.xml")).execute(treePart, treeIdentifier, className, parentProperty);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.security.SecuritySystem#createUser(java.lang.String, java.lang.String)
   */
  public void createUser(String userName, String passw) {
    doCreateUser(userName, passw);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.security.SecuritySystem#changePassword(java.lang.String, java.lang.String)
   */
  public void changePassword(String userName, String passw) {
    doChangePassword(userName, passw);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.security.SecuritySystem#deleteUser(java.lang.String)
   */
  public void deleteUser(String userName) {
    doDeleteUser(userName);
  }

  @SuppressWarnings("unchecked")
  public void start() throws Exception {
    logger.debug("initializing jGuard");

    String webappHomePath = "";

    TransactionManager tm = ServerUtils.getTransactionManager();
    boolean startTran = tm.getTransaction() == null;
    if (startTran)
      tm.begin();

    try {
      Map authenticationSettings = AuthenticationHelper.loadConfiguration(authenticationConfigurationLocation, webappHomePath);
      Map authorizationSettings = AuthorizationHelper.loadConfiguration(authorizationConfigurationLocation, webappHomePath);
      setupConfiguration(authenticationSettings, authorizationSettings);

      configuration = new JGuardConfiguration();

      authenticationManager = AuthenticationHelper.initAuthentication(configuration, authenticationSettings, applicationName);
      authorizationManager = AuthorizationHelper.initAuthorization(authorizationSettings, applicationName);

      policy = new MultipleAppPolicy();
      policy.registerPermissionProvider(Thread.currentThread().getContextClassLoader(), authorizationManager);
      accessController = new LocalAccessController(policy);
    } finally {
      if (startTran)
        tm.commit();
    }
  }

  public void stop() {
    logger.debug("jGuard destroyed");
    policy.unregisterPermissionProvider(Thread.currentThread().getContextClassLoader());
    policy = null;
    configuration = null;
    authenticationManager = null;
    authorizationManager = null;
    accessController = null;
  }

  public void refreshPermissions() {
    authorizationManager.refresh();
  }

  public void addAuditEvent(SecurityAuditType auditType, String beanName, String userName, String details) {
    int clientId = 1;//((Integer) ((PersistentObject) ServerUtils.getCurrentSession().getSystemTenant()).getPrimaryKey())
    sendAuditMessage(new SecurityAuditEvent(clientId, auditType, userName, beanName, details));
  }

}
