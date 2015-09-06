/*
 * UIProfileManagerServiceImpl.java
 *
 * Copyright (c) 1998 - 2007 BusinessTechnology, Ltd.
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

import com.mg.framework.api.orm.OrmTemplate;
import com.mg.framework.api.orm.Restrictions;
import com.mg.framework.api.ui.UIProfile;
import com.mg.framework.api.ui.UIProfileManager;
import com.mg.framework.utils.ServerUtils;
import com.mg.merp.security.model.SecUser;
import com.mg.merp.security.model.SecUserProfile;
import com.mg.merp.security.model.SecUserProfileItem;

import javax.transaction.Status;
import javax.transaction.SystemException;

/**
 * Реализация менеджера профилей пользовательского интерфейса
 *
 * @author Oleg V. Safonov
 * @version $Id: UIProfileManagerServiceImpl.java,v 1.2 2007/03/13 16:49:20 safonov Exp $
 */
public class UIProfileManagerServiceImpl implements UIProfileManager {
  //private static final short DELPHI_UI = 0;
  /**
   * тип профиля для интерфейса на базе ULC
   */
  private static final short ULC_UI = 1;
  /**
   * имя атрибута сессии хранящего заголовок профилей данного пользователя
   */
  private static final String UI_PROFILE_ATTR = "MG_UI_PROFILE";

  private UIProfile doLoad(String name) {
    SecUserProfileItem item = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(SecUserProfileItem.class, "pi")
        .add(Restrictions.eq("pi.Profile", getUserProfile()))
        .add(Restrictions.eq("pi.Code", name)));
    return new UIProfilePropertiesImpl(name, item);
  }

  private void doStore(UIProfile profile) {
    try {
      //в некоторых ситуациях транзакция может стать не активной до закрытия форм, как правило профиль сохраняется именно в этот момент,
      //т.о. исключаем ИС, однако не будет сохранен профиль, даже если были в нем изменения, это фича
      if (ServerUtils.getTransactionManager().getStatus() != Status.STATUS_ACTIVE)
        return;
    } catch (SystemException e) {
      //ignore
    }
    SecUserProfileItem item = ((UIProfilePropertiesImpl) profile).getUserProfileItem();
    //возможно новый элемент, установим связь с UI профилем пользователя
    if (item.getProfile() == null)
      item.setProfile(getUserProfile());
    //сохранем только измененный
    if (((UIProfilePropertiesImpl) profile).isDirty())
      ServerUtils.getPersistentManager().merge(item);
  }

  /**
   * загрузить заголовок профилей
   *
   * @return заголовок
   */
  private SecUserProfile getUserProfile() {
    SecUserProfile userProfile = (SecUserProfile) ServerUtils.getCurrentSession().getAttribute(UI_PROFILE_ATTR);
    //если нет в сессии, то грузим
    if (userProfile == null) {
      userProfile = OrmTemplate.getInstance().findUniqueByCriteria(OrmTemplate.createCriteria(SecUserProfile.class, "p")
          .createAlias("p.User", "u")
          .add(Restrictions.eq("p.ProfileType", ULC_UI))
          .add(Restrictions.eq("u.Id", ServerUtils.getUserProfile().getIdentificator())));
      //не был создан для текущего пользователя
      if (userProfile == null) {
        userProfile = createUserProfile();
      }
      ServerUtils.getCurrentSession().setAttribute(UI_PROFILE_ATTR, userProfile);
    }
    return userProfile;
  }

  /**
   * создание заголовка профиля
   *
   * @return заголовок
   */
  private SecUserProfile createUserProfile() {
    SecUserProfile result = new SecUserProfile();
    result.setProfileType(ULC_UI);
    result.setUser(ServerUtils.getPersistentManager().find(SecUser.class, ServerUtils.getUserProfile().getIdentificator()));
    ServerUtils.getPersistentManager().persist(result);
    return result;
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.UIProfileManager#load()
   */
  public UIProfile load(String name) {
    return doLoad(name);
  }

  /* (non-Javadoc)
   * @see com.mg.framework.api.ui.UIProfileManager#store(com.mg.framework.api.ui.UIProfile)
   */
  public void store(UIProfile profile) {
    doStore(profile);
  }

}
