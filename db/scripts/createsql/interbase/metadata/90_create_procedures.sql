ALTER PROCEDURE ABS (
    SRC DOUBLE PRECISION)
RETURNS (
    DEST DOUBLE PRECISION)
AS
begin
  if (Src > 0) then Dest = Src;
  else Dest = Src * (-1);
  suspend;
end
^

ALTER PROCEDURE ACC_CALC_AVERAGE_OUTCOST (
    OPERDATE TIMESTAMP,
    ACC INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    CATALOG_ID INTEGER,
    CONTR INTEGER,
    QUANTITY NUMERIC(15,3))
RETURNS (
    COSTNAT NUMERIC(15,4),
    COSTCUR NUMERIC(15,4),
    SUMMANAT NUMERIC(15,4),
    SUMMACUR NUMERIC(15,4),
    REALQUAN NUMERIC(15,3))
AS
declare variable period_id integer;
declare variable remnval_id integer;
declare variable remnbeginnat numeric(15,4);
declare variable remnbegincur numeric(15,4);
declare variable beginquan numeric(15,3);
declare variable turndbnat numeric(15,4);
declare variable turndbcur numeric(15,4);
declare variable quandb numeric(15,3);
declare variable turnktnat numeric(15,4);
declare variable turnktcur numeric(15,4);
declare variable quankt numeric(15,3);
declare variable dcostnat double precision;
declare variable dcostcur double precision;
declare variable dremnbeginnat double precision;
declare variable dremnbegincur double precision;
declare variable dbeginquan double precision;
declare variable a integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: Рассчитывает цену списания для средних цен
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 14.01.2003 Кривопустов Ускорена работа для позиций,
                        отсутствующих в оборотке
**********************************************************/
/*a = dm('enter ACC_CALC_AVERAGE_OUTCOST');*/
  /* find period */
  execute procedure F_Find_Period :operdate
  returning_values :period_id;

  remnbeginnat = 0;
  remnbegincur = 0;
  beginquan = 0;

  turndbnat = 0;
  turndbcur = 0;
  quandb = 0;
  turnktnat = 0;
  turnktcur = 0;
  quankt = 0;

  /*a = wi('acc', acc);
  a = wi('ANL1', ANL1);
  a = wi('ANL2', ANL2);
  a = wi('ANL3', ANL3);
  a = wi('ANL4', ANL4);
  a = wi('ANL5', ANL5);
  a = wi('period_id', period_id);
  a = wi('catalog_id', catalog_id);
  a = wi('contr', contr);*/
  /* find remn on begin period*/
  select r.id, r.remnbeginnat, r.remnbegincur, r.beginquan
  from remnval r
  where ( r.acc_id = :Acc ) and ( r.period_id = :period_id ) and
    ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and 
    ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and 
    ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and 
    ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and 
    ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null))) and
    ( r.batch_id is null ) and
    ( r.catalog_id = :catalog_id ) and
    ((r.contractor_id = :contr)or(r.contractor_id is null))
  into :remnval_id, :remnbeginnat, :remnbegincur, :beginquan;

  if (remnval_id is null) then begin
    summanat = 0;
    summacur = 0;
    realquan = 0;
    /*a = dm('exit ACC_CALC_AVERAGE_OUTCOST');*/
    suspend;
    exit;
  end
  
  /* calculate debet turn */
  select sum(e.summanat), sum(e.summacur), sum(e.quantity) from economicspec e, economicoper eo
  where (e.remnvaldb_id = :remnval_id)and(eo.id = e.economicoper_id)and(eo.keepdate<=:operdate)
  PLAN JOIN (E INDEX (IDX_ECONSPEC_VALDB_ID),EO INDEX (IDX_ECONOMICOPER_ID_KEEPDATE))
  into :turndbnat, :turndbcur, :quandb;
  if (turndbnat is null) then begin
    turndbnat = 0;
    turndbcur = 0;
    quandb = 0;
  end
  /*a = wf('turndbnat',15,3,turndbnat);
  a = wf('quandb',15,3,quandb);*/


  /* calculate kredit turn */
  select sum(e.summanat), sum(e.summacur), sum(e.quantity) from economicspec e, economicoper eo
  where (e.remnvalkt_id = :remnval_id)and(eo.id = e.economicoper_id)and(eo.keepdate<:operdate)
  PLAN JOIN (E INDEX (IDX_ECONSPEC_VALKT_ID),EO INDEX (IDX_ECONOMICOPER_ID_KEEPDATE))
  into :turnktnat, :turnktcur, :quankt;
  if (turnktnat is null) then begin
    turnktnat = 0;
    turnktcur = 0;
    quankt = 0;
  end
  /*a = wf('turnktnat',15,3,turnktnat);
  a = wf('quankt',15,3,quankt);*/

  remnbeginnat = remnbeginnat + turndbnat - turnktnat;
  remnbegincur = remnbegincur + turndbcur - turnktcur;
  beginquan = beginquan + quandb - quankt;

  /*a = wf('remnbeginnat',15,3,remnbeginnat);
  a = wf('beginquan',15,3,beginquan);*/
  if ((beginquan = 0) or (beginquan is null)) then begin
    dcostnat = 0;
    dcostcur = 0;
  end
  else begin
    dremnbeginnat = remnbeginnat;
    dremnbegincur = remnbegincur;
    dbeginquan = beginquan;
    dcostnat = dremnbeginnat/dbeginquan;
    dcostcur = dremnbegincur/dbeginquan;
  end
  /*a = wf('dcostnat',15,3,dcostnat);*/
  realquan = quantity;
  summanat = Round4(dcostnat * realquan);
  summacur = Round4(dcostcur * realquan);
  costnat = Round4(dcostnat);
  costcur = Round4(dcostcur);
  /*a = dm('exit ACC_CALC_AVERAGE_OUTCOST');*/
  suspend;
end
^

ALTER PROCEDURE ACC_CALC_REMNACC_END (
    ACC INTEGER,
    PERIOD_ID INTEGER,
    REMNBEGINNATDB NUMERIC(15,4),
    REMNBEGINNATKT NUMERIC(15,4),
    REMNBEGINCURDB NUMERIC(15,4),
    REMNBEGINCURKT NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4))
RETURNS (
    REMNENDNATDB NUMERIC(15,4),
    REMNENDNATKT NUMERIC(15,4),
    REMNENDCURDB NUMERIC(15,4),
    REMNENDCURKT NUMERIC(15,4))
AS
  declare variable a integer;
  declare variable acctype smallint;
  declare variable summa  numeric(15,4);
  declare variable curcode char(5);
  declare variable id integer;
  declare variable REMNENDNATDB1 NUMERIC(15,4);
  declare variable REMNENDNATKT1 NUMERIC(15,4);
  declare variable REMNENDCURDB1 NUMERIC(15,4);
  declare variable REMNENDCURKT1 NUMERIC(15,4);
begin
  a = wi('acc_id', acc);
  /* get account type */
  select a.acctype, a.currency_code from accplan a
  where ( a.id = :Acc )
  into :acctype, :curcode;
  /* active, active/passive rollup, zero */
  if (AccType in (0,3,4)) then begin
    summa = remnbeginnatdb - remnbeginnatkt + turnnatdb - turnnatkt;
    if (summa > 0) then begin
      remnendnatdb = summa;
      remnendnatkt = 0;
    end
    else begin
      remnendnatdb = 0;
      remnendnatkt = -summa;
    end
    if (curcode is not null) then begin
      summa = remnbegincurdb - remnbegincurkt + turncurdb - turncurkt;
      if (summa > 0) then begin
        remnendcurdb = summa;
        remnendcurkt = 0;
      end
      else begin
        remnendcurdb = 0;
        remnendcurkt = -summa;
      end
    end
    else begin
      remnendcurdb = 0;
      remnendcurkt = 0;
    end
    exit;
  end
  /* passive */
  if (AccType = 1) then begin
    summa = remnbeginnatkt - remnbeginnatdb + TurnnatKt - TurnnatDb;
    if (summa < 0) then begin
      remnendnatdb = -summa;
      remnendnatkt = 0;
    end
    else begin
      remnendnatdb = 0;
      remnendnatkt = summa;
    end
    if (curcode is not null) then begin
      summa = remnbegincurkt - remnbegincurdb + TurncurKt - TurncurDb;
      if (summa < 0) then begin
        remnendcurdb = -summa;
        remnendcurkt = 0;
      end
      else begin
        remnendcurdb = 0;
        remnendcurkt = summa;
      end
    end
    else begin
      remnendcurdb = 0;
      remnendcurkt = 0;
    end
    exit;
  end
  /* active/passive unwrap */
  if (AccType = 2) then begin
    remnendnatdb = 0; remnendnatkt = 0; remnendcurdb = 0; remnendcurkt = 0;
    for
      select r.id, r.remnbeginnatdb, r.remnbeginnatkt, r.remnbegincurdb, r.remnbegincurkt
      from remndbkt r
      where (r.period_id = :period_id) and (r.acc_id = :acc)
      into :id, :remnbeginnatdb, :remnbeginnatkt, :remnbegincurdb, :remnbegincurkt
    do begin
      turncurdb = null; turnnatdb = null; turncurkt = null; turnnatkt = null;

      select sum(es.summacur), sum(es.summanat)
      from economicspec es
      where (es.remndb_id = :id)
      into :turncurdb, :turnnatdb;
    
      if (turncurdb is null) then turncurdb = 0;
      if (turnnatdb is null) then turnnatdb = 0;
    
      select sum(es.summacur), sum(es.summanat)
      from economicspec es
      where (es.remnkt_id = :id)
      into :turncurkt, :turnnatkt;
    
      if (turncurkt is null) then turncurkt = 0;
      if (turnnatkt is null) then turnnatkt = 0;
    
      execute procedure acc_calc_remndbkt_end(
        :remnbeginnatdb, :remnbeginnatkt,
        :turnnatdb, :turnnatkt,
        :remnbegincurdb, :remnbegincurkt,
        :turncurdb, :turncurkt)
      returning_values :remnendnatdb1, :remnendnatkt1, :remnendcurdb1, :remnendcurkt1;

      remnendnatdb = remnendnatdb + remnendnatdb1;
      remnendnatkt = remnendnatkt + remnendnatkt1;
      remnendcurdb = remnendcurdb + remnendcurdb1;
      remnendcurkt = remnendcurkt + remnendcurkt1;
    end

    if (curcode is null) then begin
      remnendcurdb = 0;
      remnendcurkt = 0;
    end
    exit;
  end
end
^

ALTER PROCEDURE ACC_CALC_REMNANL_END (
    ACC INTEGER,
    PERIOD_ID INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    REMNBEGINNATDB NUMERIC(15,4),
    REMNBEGINNATKT NUMERIC(15,4),
    REMNBEGINCURDB NUMERIC(15,4),
    REMNBEGINCURKT NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4))
RETURNS (
    REMNENDNATDB NUMERIC(15,4),
    REMNENDNATKT NUMERIC(15,4),
    REMNENDCURDB NUMERIC(15,4),
    REMNENDCURKT NUMERIC(15,4))
AS
declare variable acctype smallint;
declare variable summa  numeric(15,4);
declare variable curcode char(5);
declare variable id integer;
declare variable REMNENDNATDB1 NUMERIC(15,4);
declare variable REMNENDNATKT1 NUMERIC(15,4);
declare variable REMNENDCURDB1 NUMERIC(15,4);
declare variable REMNENDCURKT1 NUMERIC(15,4);
begin
  /* get account type */
  select a.acctype, a.currency_code from accplan a
  where ( a.id = :Acc )
  into :acctype, :curcode;
  /* active, active/passive rollup, zero */
  if (AccType in (0,3,4)) then begin
    summa = remnbeginnatdb - remnbeginnatkt + turnnatdb - turnnatkt;
    if (summa > 0) then begin
      remnendnatdb = summa;
      remnendnatkt = 0;
    end
    else begin
      remnendnatdb = 0;
      remnendnatkt = -summa;
    end
    if (curcode is not null) then begin
      summa = remnbegincurdb - remnbegincurkt + turncurdb - turncurkt;
      if (summa > 0) then begin
        remnendcurdb = summa;
        remnendcurkt = 0;
      end
      else begin
        remnendcurdb = 0;
        remnendcurkt = -summa;
      end
    end
    else begin
      remnendcurdb = 0;
      remnendcurkt = 0;
    end
    exit;
  end
  /* passive */
  if (AccType = 1) then begin
    summa = remnbeginnatkt - remnbeginnatdb + TurnnatKt - TurnnatDb;
    if (summa < 0) then begin
      remnendnatdb = -summa;
      remnendnatkt = 0;
    end
    else begin
      remnendnatdb = 0;
      remnendnatkt = summa;
    end
    if (curcode is not null) then begin
      summa = remnbegincurkt - remnbegincurdb + TurncurKt - TurncurDb;
      if (summa < 0) then begin
        remnendcurdb = -summa;
        remnendcurkt = 0;
      end
      else begin
        remnendcurdb = 0;
        remnendcurkt = summa;
      end
    end
    else begin
      remnendcurdb = 0;
      remnendcurkt = 0;
    end
    exit;
  end
  /* active/passive unwrap */
  if (AccType = 2) then begin
    remnendnatdb = 0; remnendnatkt = 0; remnendcurdb = 0; remnendcurkt = 0;
    for
      select r.id, r.remnbeginnatdb, r.remnbeginnatkt, r.remnbegincurdb, r.remnbegincurkt
      from remndbkt r
      where (r.period_id = :period_id) and (r.acc_id = :acc) and
      ((r.anlplan1_id = :anl1)or((r.anlplan1_id is null)and(:anl1 is null))) and
      ((r.anlplan2_id = :anl2)or((r.anlplan2_id is null)and(:anl2 is null))) and
      ((r.anlplan3_id = :anl3)or((r.anlplan3_id is null)and(:anl3 is null))) and
      ((r.anlplan4_id = :anl4)or((r.anlplan4_id is null)and(:anl4 is null))) and
      ((r.anlplan5_id = :anl5)or((r.anlplan5_id is null)and(:anl5 is null)))
      into :id, :remnbeginnatdb, :remnbeginnatkt, :remnbegincurdb, :remnbegincurkt
    do begin
      turncurdb = null; turnnatdb = null; turncurkt = null; turnnatkt = null;

      select sum(es.summacur), sum(es.summanat)
      from economicspec es
      where (es.remndb_id = :id)
      into :turncurdb, :turnnatdb;
    
      if (turncurdb is null) then turncurdb = 0;
      if (turnnatdb is null) then turnnatdb = 0;
    
      select sum(es.summacur), sum(es.summanat)
      from economicspec es
      where (es.remnkt_id = :id)
      into :turncurkt, :turnnatkt;
    
      if (turncurkt is null) then turncurkt = 0;
      if (turnnatkt is null) then turnnatkt = 0;
    
      execute procedure acc_calc_remndbkt_end(
        :remnbeginnatdb, :remnbeginnatkt,
        :turnnatdb, :turnnatkt,
        :remnbegincurdb, :remnbegincurkt,
        :turncurdb, :turncurkt)
      returning_values :remnendnatdb1, :remnendnatkt1, :remnendcurdb1, :remnendcurkt1;

      remnendnatdb = remnendnatdb + remnendnatdb1;
      remnendnatkt = remnendnatkt + remnendnatkt1;
      remnendcurdb = remnendcurdb + remnendcurdb1;
      remnendcurkt = remnendcurkt + remnendcurkt1;
    end

    if (curcode is null) then begin
      remnendcurdb = 0;
      remnendcurkt = 0;
    end
    exit;
  end
end
^

ALTER PROCEDURE ACC_CALC_REMNDBKT_END (
    REMNBEGINNATDB NUMERIC(15,4),
    REMNBEGINNATKT NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    REMNBEGINCURDB NUMERIC(15,4),
    REMNBEGINCURKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4))
RETURNS (
    REMNENDNATDB NUMERIC(15,4),
    REMNENDNATKT NUMERIC(15,4),
    REMNENDCURDB NUMERIC(15,4),
    REMNENDCURKT NUMERIC(15,4))
AS
declare variable summa numeric(15,4);
begin
  summa = remnbeginnatdb - remnbeginnatkt + TurnNatdb - TurnNatKt;
  if (summa > 0) then begin
    remnendnatdb = summa;
    remnendnatkt = 0;
  end
  else begin
    remnendnatdb = 0;
    remnendnatkt = -summa;
  end
  if ((TurnCurDb <> 0) or (TurnCurKt <> 0)) then begin
    summa = remnbegincurdb - remnbegincurkt + TurnCurDb - TurnCurKt;
    if (summa > 0) then begin
      remnendcurdb = summa;
      remnendcurkt = 0;
    end
    else begin
      remnendcurdb = 0;
      remnendcurkt = -summa;
    end
  end
  else begin
    remnendcurdb = remnbegincurdb;
    remnendcurkt = remnbegincurkt;
  end
end
^

ALTER PROCEDURE ACC_CALC_REMNVAL_END (
    BEGINQUAN NUMERIC(18,3),
    REMNBEGINNAT NUMERIC(18,4),
    REMNBEGINCUR NUMERIC(18,4),
    QUANTITYDB NUMERIC(18,3),
    QUANTITYKT NUMERIC(18,3),
    TURNNATDB NUMERIC(18,4),
    TURNNATKT NUMERIC(18,4),
    TURNCURDB NUMERIC(18,4),
    TURNCURKT NUMERIC(18,4))
RETURNS (
    ENDQUAN NUMERIC(18,3),
    REMNENDNAT NUMERIC(18,4),
    REMNENDCUR NUMERIC(18,4))
AS
begin
  remnendnat = remnbeginnat + TurnnatDb - TurnnatKt;
  endquan = beginquan + QuantityDb - QuantityKt;
  /* if has currency turn */
  if ((TurnCurDb <> 0) or (TurnCurKt <> 0)) then
    remnendcur = remnbegincur + TurnCurDb - TurnCurKt;
  else
    remnendcur = remnbegincur;
end
^

ALTER PROCEDURE ACC_CARRY_FORWARD_BALANCE (
    PERIOD_ID INTEGER,
    ACCOUNT_ID INTEGER,
    REMANTKIND SMALLINT)
AS
declare variable prevper_id integer;
declare variable begindate  timestamp;
declare variable remn_id integer;
declare variable prevremn_id integer;
declare variable remnbegnatdb numeric(15,4);
declare variable remnbegnatkt numeric(15,4);
declare variable remnbegcurdb numeric(15,4);
declare variable remnbegcurkt numeric(15,4);
declare variable remnendnatdb numeric(15,4);
declare variable remnendnatkt numeric(15,4);
declare variable remnendcurdb numeric(15,4);
declare variable remnendcurkt numeric(15,4);
declare variable Anl1 integer;
declare variable Anl2 integer;
declare variable Anl3 integer;
declare variable Anl4 integer;
declare variable Anl5 integer;
declare variable catalog_id integer;
declare variable contr_id integer;
declare variable batch_id integer;
declare variable begquan numeric(15,3);
declare variable endquan numeric(15,3);
declare variable docbasetype char(15);
declare variable docbasenumber char(20);
declare variable docbasedate  timestamp;
declare variable doctype char(15);
declare variable docnumber char(20);
declare variable docdate  timestamp;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: 
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 06.02.2003 Кривопустов Корректный перенос строк REMNVAL
                        для партионных счетов
**********************************************************/

execute procedure p_check_period :period_id;

  select p.datefrom from period p where p.id = :period_id
  into :begindate;

  /* Find prev period, may be several, get first */
  for
    select p.id from period p where p.dateto < :begindate
    order by p.datefrom desc
    into :prevper_id
  do begin
    /* RemnAcc */
    if (remantkind = 0) then begin
      /* delete lines with empty turnover */
      delete from remnacc r
        where (r.period_id = :period_id) and (r.acc_id = :account_id) and
              (not exists (select * from economicspec s 
                           where (s.remnaccdb_id = r.id) or (s.remnacckt_id = r.id) ));
      /* update begin remains with zero */
      update remnacc r set
          r.remnbeginnatdb = 0,
          r.remnbeginnatkt = 0,
          r.remnbegincurdb = 0,
          r.remnbegincurkt = 0
        where (r.period_id = :period_id) and (r.acc_id = :account_id);
      /**/
      select r.id from remnacc r
      where (r.period_id = :period_id) and (r.acc_id = :account_id)
      into :remn_id;

      select r.id, r.remnbeginnatdb, r.remnbeginnatkt, r.remnbegincurdb, r.remnbegincurkt
      from remnacc r
      where (r.period_id = :prevper_id) and (r.acc_id = :account_id)
      into :prevremn_id, :remnbegnatdb, :remnbegnatkt, :remnbegcurdb, :remnbegcurkt;

      if (prevremn_id is null) then Exit;

      select f.remnendnatdb, f.remnendnatkt, f.remnendcurdb, f.remnendcurkt
      from acc_remnacc_load(:prevremn_id) f
      into :remnendnatdb, :remnendnatkt, :remnendcurdb, :remnendcurkt;

      if (remn_id is null) then begin
        remn_id = gen_id(remnacc_id_gen, 1);
        insert into remnacc (id, period_id, acc_id, remnbeginnatdb, remnbeginnatkt,
          remnbegincurdb, remnbegincurkt)
        values (:remn_id, :period_id, :account_id, :remnendnatdb, :remnendnatkt,
          :remnendcurdb, :remnendcurkt);
      end
      else begin
        update remnacc set
          remnbeginnatdb = :remnendnatdb,
          remnbeginnatkt = :remnendnatkt,
          remnbegincurdb = :remnendcurdb,
          remnbegincurkt = :remnendcurkt
        where id = :remn_id;
      end
      Exit;
    end
    else
    /* RemnAnl */
    if (remantkind = 1) then begin
      /* delete lines with empty turnover */
      delete from remnanl r
        where (r.period_id = :period_id) and (r.acc_id = :account_id) and
              (not exists (select * from economicspec s 
                           where (s.remnanldb_id = r.id) or (s.remnanlkt_id = r.id) ));
      /* update begin remains with zero */
      update remnanl r set 
          r.remnbeginnatdb = 0,
          r.remnbeginnatkt = 0,
          r.remnbegincurdb = 0,
          r.remnbegincurkt = 0
        where (r.period_id = :period_id) and (r.acc_id = :account_id);
      /**/
      for
        select r.id, r.remnbeginnatdb, r.remnbeginnatkt, r.remnbegincurdb, r.remnbegincurkt,
          r.anlplan1_id, r.anlplan2_id, r.anlplan3_id, r.anlplan4_id, r.anlplan5_id
        from remnanl r where (r.period_id = :prevper_id) and (r.acc_id = :account_id)
        into :prevremn_id, :remnbegnatdb, :remnbegnatkt, :remnbegcurdb, :remnbegcurkt,
          :Anl1, :Anl2, :Anl3, :Anl4, :Anl5
      do begin
        if (prevremn_id is not null) then begin

          select f.remnendnatdb, f.remnendnatkt, f.remnendcurdb, f.remnendcurkt
          from acc_remnanl_load(:prevremn_id) f
          into :remnendnatdb, :remnendnatkt, :remnendcurdb, :remnendcurkt;

          remn_id = null;
          select r.id from remnanl r where 
            (r.period_id = :period_id) and (r.acc_id = :account_id) and
            ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and 
            ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and 
            ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and 
            ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and 
            ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null)))
          into :remn_id;
          if (remn_id is null) then begin
            remn_id = gen_id(remnanl_id_gen, 1);
            insert into remnanl (id, period_id, acc_id, anlplan1_id, anlplan2_id, anlplan3_id, anlplan4_id, anlplan5_id,
              remnbeginnatdb, remnbeginnatkt,
              remnbegincurdb, remnbegincurkt)
            values (:remn_id, :period_id, :account_id, :Anl1, :Anl2, :Anl3, :Anl4, :Anl5,
              :remnendnatdb, :remnendnatkt,
              :remnendcurdb, :remnendcurkt);
          end
          else begin
            update remnanl set
              remnbeginnatdb = :remnendnatdb,
              remnbeginnatkt = :remnendnatkt,
              remnbegincurdb = :remnendcurdb,
              remnbegincurkt = :remnendcurkt
            where id = :remn_id;
          end
        end
      end
      Exit;
    end
    else
    /* RemnVal */
    if (remantkind = 2) then begin
      /* delete lines with empty turnover */
      delete from remnval r
        where (r.period_id = :period_id) and (r.acc_id = :account_id) and
              (not exists (select * from economicspec s 
                           where (s.remnvaldb_id = r.id) or (s.remnvalkt_id = r.id) ));
      /* update begin remains with zero */
      update remnval r set 
          r.remnbeginnat = 0,
          r.beginquan = 0,
          r.remnbegincur = 0
        where (r.period_id = :period_id) and (r.acc_id = :account_id);
      /**/
      for
        select r.id, r.remnbeginnat, r.remnbegincur, r.beginquan,
          r.anlplan1_id, r.anlplan2_id, r.anlplan3_id, r.anlplan4_id, r.anlplan5_id,
          r.catalog_id, r.contractor_id, r.batch_id
        from remnval r where (r.period_id = :prevper_id) and (r.acc_id = :account_id)
        into :prevremn_id, :remnbegnatdb, :remnbegcurdb, :begquan,
          :Anl1, :Anl2, :Anl3, :Anl4, :Anl5, :catalog_id, :contr_id, :batch_id
      do begin
        if (prevremn_id is not null) then begin
          select f.remnendnat, f.remnendcur, f.endquan
          from acc_remnval_load(:prevremn_id) f
          into :remnendnatdb, :remnendcurdb, :endquan;

          remn_id = null;
          select r.id from remnval r where
            (r.period_id = :period_id) and (r.acc_id = :account_id) and (r.catalog_id = :catalog_id) and
            ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and 
            ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and 
            ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and 
            ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and 
            ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null))) and
            ((r.contractor_id = :contr_id)or(r.contractor_id is null)) and
            ((r.batch_id = :batch_id)or((r.batch_id is null)and(:batch_id is null)))
          into :remn_id;
          if (remn_id is null) then begin
            remn_id = gen_id(remnval_id_gen, 1);
            insert into remnval (id, period_id, acc_id, anlplan1_id, anlplan2_id, anlplan3_id, anlplan4_id, anlplan5_id, catalog_id,
              contractor_id,
              remnbeginnat, remnbegincur, beginquan, isremove, batch_id)
            values (:remn_id, :period_id, :account_id, :Anl1, :Anl2, :Anl3, :Anl4, :Anl5, :catalog_id,
              :contr_id, :remnendnatdb, :remnendcurdb, :endquan, 1, :batch_id);
          end
          else begin
            update remnval set
              remnbeginnat = :remnendnatdb,
              beginquan = :endquan,
              remnbegincur = :remnendcurdb
            where id = :remn_id;
          end
        end
      end
      Exit;
    end
    else
    /* RemnDbKt */
    if (remantkind = 3) then begin
      /* delete lines with empty turnover */
      delete from remndbkt r
        where (r.period_id = :period_id) and (r.acc_id = :account_id) and
              (not exists (select * from economicspec s 
                           where (s.remndb_id = r.id) or (s.remnkt_id = r.id) ));
      /* update begin remains with zero */
      update remndbkt r set 
          r.remnbeginnatdb = 0,
          r.remnbeginnatkt = 0,
          r.remnbegincurdb = 0,
          r.remnbegincurkt = 0
        where (r.period_id = :period_id) and (r.acc_id = :account_id);
      /**/
      for
        select r.id, r.remnbeginnatdb, r.remnbeginnatkt, r.remnbegincurdb, r.remnbegincurkt,
          r.anlplan1_id, r.anlplan2_id, r.anlplan3_id, r.anlplan4_id, r.anlplan5_id,
          r.contractor_id,
          r.docbasetype, r.docbasenumber, r.docbasedate,
          r.doctype, r.docnumber, r.docdate
        from remndbkt r where (r.period_id = :prevper_id) and (r.acc_id = :account_id)
        into :prevremn_id, :remnbegnatdb, :remnbegnatkt, :remnbegcurdb, :remnbegcurkt,
          :Anl1, :Anl2, :Anl3, :Anl4, :Anl5, :contr_id,
          :docbasetype, :docbasenumber, :docbasedate,
          :doctype, :docnumber, :docdate
      do begin
        if (prevremn_id is not null) then begin
          select f.remnendnatdb, f.remnendnatkt, f.remnendcurdb, f.remnendcurkt
          from acc_remndbkt_load(:prevremn_id) f
          into :remnendnatdb, :remnendnatkt, :remnendcurdb, :remnendcurkt;

          remn_id = null;
          select r.id from remndbkt r where 
            (r.period_id = :period_id) and (r.acc_id = :account_id) and
            ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and 
            ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and 
            ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and 
            ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and 
            ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null))) and
            ((r.contractor_id = :contr_id)or(r.contractor_id is null)) and
            (r.docbasetype = :docbasetype) and (r.docbasenumber = :docbasenumber) and (r.docbasedate = :docbasedate) and
            (r.doctype = :doctype) and (r.docnumber = :docnumber) and (r.docdate = :docdate)   
          into :remn_id;
          if (remn_id is null) then begin
            remn_id = gen_id(remndbkt_id_gen, 1);
            insert into remndbkt (id, period_id, acc_id, anlplan1_id, anlplan2_id, anlplan3_id, anlplan4_id, anlplan5_id,
              contractor_id,
              remnbeginnatdb, remnbeginnatkt,
              remnbegincurdb, remnbegincurkt,
              docbasetype, docbasenumber, docbasedate,
              doctype, docnumber, docdate)
            values (:remn_id, :period_id, :account_id, :Anl1, :Anl2, :Anl3, :Anl4, :Anl5,
              :contr_id,
              :remnendnatdb, :remnendnatkt, :remnendcurdb, :remnendcurkt,
              :docbasetype, :docbasenumber, :docbasedate,
              :doctype, :docnumber, :docdate);
          end
          else begin
            update remndbkt set
              remnbeginnatdb = :remnendnatdb,
              remnbeginnatkt = :remnendnatkt,
              remnbegincurdb = :remnendcurdb,
              remnbegincurkt = :remnendcurkt
            where id = :remn_id;
          end
        end
      end
      exit;
    end
  end
end
^

ALTER PROCEDURE ACC_CARRY_FORWARD_BALANCE_ALL (
    PERIOD_ID INTEGER,
    REMANTKIND SMALLINT)
AS
declare variable account_id integer;
begin
  for
    select a.id from accplan a order by a.upacc
    into :account_id
  do
    execute procedure acc_carry_forward_balance(:period_id, :account_id, :remantkind);
end
^

ALTER PROCEDURE ACC_CHECK_LAST_BATCH (
    OPERDATE TIMESTAMP,
    ACCBATCH_ID INTEGER,
    QUANTITY NUMERIC(15,3),
    SUMMANAT NUMERIC(15,4),
    SUMMACUR NUMERIC(15,4))
RETURNS (
    REALSUMMANAT NUMERIC(15,4),
    REALSUMMACUR NUMERIC(15,4))
AS
declare variable period_id integer;
declare variable remnval_id integer;
declare variable remnbeginnat numeric(15,4);
declare variable remnbegincur numeric(15,4);
declare variable beginquan numeric(15,3);
declare variable turndbnat numeric(15,4);
declare variable turndbcur numeric(15,4);
declare variable quandb numeric(15,3);
declare variable turnktnat numeric(15,4);
declare variable turnktcur numeric(15,4);
declare variable quankt numeric(15,3);
declare variable a integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 15.12.2003
 Назначение: для партионного учета, если списывается всё
   количество по строке оборотки, то возвращается вся оставшаяся
   сумма, независимо от цены партии (Bug#2084)
 Используется: процедурой F_CALCULATE_OUT_COST
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
**********************************************************/
  /*a = dm('Enter ACC_CHECK_LAST_BATCH');*/

  REALSUMMANAT = SUMMANAT;
  REALSUMMACUR = SUMMACUR;

  execute procedure f_find_period(:operdate)
  returning_values :period_id;

  select r.id, r.remnbeginnat, r.remnbegincur, r.beginquan
  from remnval r
  where ( r.batch_id = :ACCBATCH_ID ) and ( r.period_id = :period_id )
  into :remnval_id, :remnbeginnat, :remnbegincur, :beginquan;

  if (remnval_id is null) then begin
    /*a = dm('Exit ACC_CHECK_LAST_BATCH: remnval_id is null');*/
    suspend;
    exit;
  end
  
  /* calculate debet turn */
  select sum(e.summanat), sum(e.summacur), sum(e.quantity) from economicspec e, economicoper eo
  where (e.remnvaldb_id = :remnval_id)and(eo.id = e.economicoper_id)and(eo.keepdate<=:operdate)
  PLAN JOIN (E INDEX (IDX_ECONSPEC_VALDB_ID),EO INDEX (IDX_ECONOMICOPER_ID_KEEPDATE))
  into :turndbnat, :turndbcur, :quandb;
  if (turndbnat is null) then begin
    turndbnat = 0;
    turndbcur = 0;
    quandb = 0;
  end

  /* calculate kredit turn */
  select sum(e.summanat), sum(e.summacur), sum(e.quantity) from economicspec e, economicoper eo
  where (e.remnvalkt_id = :remnval_id)and(eo.id = e.economicoper_id)and(eo.keepdate<=:operdate)
  PLAN JOIN (E INDEX (IDX_ECONSPEC_VALKT_ID),EO INDEX (IDX_ECONOMICOPER_ID_KEEPDATE))
  into :turnktnat, :turnktcur, :quankt;
  if (turnktnat is null) then begin
    turnktnat = 0;
    turnktcur = 0;
    quankt = 0;
  end

  remnbeginnat = remnbeginnat + turndbnat - turnktnat;
  remnbegincur = remnbegincur + turndbcur - turnktcur;
  beginquan = beginquan + quandb - quankt;

  /*a = wf('beginquan', 15, 3, beginquan);
  a = wf('quantity', 15, 3, quantity);*/

  if (Round(beginquan - quantity, 3) = 0) then begin
    REALSUMMANAT = remnbeginnat;
    REALSUMMACUR = remnbegincur;
  end

  suspend;
end
^

ALTER PROCEDURE ACC_COPY_ACC_ANL (
    SRCACC INTEGER,
    DSTACC INTEGER,
    SRCPARENT INTEGER,
    DSTPARENT INTEGER)
AS
declare variable id integer;
declare variable new_id integer;
declare variable upcode char(10);
declare variable code char(10);
declare variable anlname varchar(80);
declare variable anllevel smallint;
declare variable usestdform smallint;
declare variable stdform smallint;
begin
  for
    select a.id, a.upcode, a.code, a.anlname, a.anllevel, a.usestdform, a.stdform
    from anlplan a
    where (a.accplan_id = :srcacc) and
      ((a.parent_id = :srcparent) or ((a.parent_id is null) and (:srcparent is null)))
    into :id, :upcode, :code, :anlname, :anllevel, :usestdform, :stdform
  do begin
    new_id = gen_id(anlplan_id_gen,1);

    insert into anlplan (id, accplan_id, parent_id, upcode, code, anlname, anllevel, usestdform, stdform)
    values (:new_id, :dstacc, :dstparent, :upcode, :code, :anlname, :anllevel, :usestdform, :stdform);

    execute procedure acc_copy_acc_anl(:srcacc, :dstacc, :id, :new_id);
  end
end
^

ALTER PROCEDURE ACC_DEL_EMPTY_REMNACC (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER)
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 09.02.2002
 Измененено:
 Назначение: удаление пустых строк в оборотке по счетам
 Используется: сервером приложения
**********************************************************/
  if (period1 is not null) then
    select p.datefrom from period p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from period p where p.id = :period2 into :date2;

  delete from remnacc r
  where (r.period_id in (select p.id from period p
                         where ((p.datefrom >= :date1) or (:period1 is null)) and
                               ((p.datefrom <= :date2) or (:period2 is null)))) and
        (r.remnbeginnatdb=0) and
        (r.remnbeginnatkt=0) and
        (r.remnbegincurdb=0) and
        (r.remnbegincurkt=0) and
        (not exists (select * from economicspec s
                     where (s.remnaccdb_id = r.id) or (s.remnacckt_id = r.id) ));
end
^

ALTER PROCEDURE ACC_DEL_EMPTY_REMNANL (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER)
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 09.02.2002
 Измененено:
 Назначение: удаление пустых строк в оборотке по аналитике
 Используется: сервером приложения
**********************************************************/
  if (period1 is not null) then
    select p.datefrom from period p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from period p where p.id = :period2 into :date2;

  delete from remnanl r
  where (r.period_id in (select p.id from period p
                         where ((p.datefrom >= :date1) or (:period1 is null)) and
                               ((p.datefrom <= :date2) or (:period2 is null)))) and
        (r.remnbeginnatdb=0) and
        (r.remnbeginnatkt=0) and
        (r.remnbegincurdb=0) and
        (r.remnbegincurkt=0) and
        (not exists (select * from economicspec s
                     where (s.remnanldb_id = r.id) or (s.remnanlkt_id = r.id) ));
end
^

ALTER PROCEDURE ACC_DEL_EMPTY_REMNDBKT (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER)
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 09.02.2002
 Измененено:
 Назначение: удаление пустых строк в оборотке по контрагентам
 Используется: сервером приложения
**********************************************************/
  if (period1 is not null) then
    select p.datefrom from period p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from period p where p.id = :period2 into :date2;

  delete from remndbkt r
  where (r.period_id in (select p.id from period p
                         where ((p.datefrom >= :date1) or (:period1 is null)) and
                               ((p.datefrom <= :date2) or (:period2 is null)))) and
        (r.remnbeginnatdb=0) and
        (r.remnbeginnatkt=0) and
        (r.remnbegincurdb=0) and
        (r.remnbegincurkt=0) and
        (not exists (select * from economicspec s
                     where (s.remndb_id = r.id) or (s.remnkt_id = r.id) ));
end
^

ALTER PROCEDURE ACC_DEL_EMPTY_REMNVAL (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER)
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 09.02.2002
 Измененено:
 Назначение: удаление пустых строк в оборотке по ТМЦ
 Используется: сервером приложения
**********************************************************/
  if (period1 is not null) then
    select p.datefrom from period p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from period p where p.id = :period2 into :date2;

  delete from remnval r
  where (r.period_id in (select p.id from period p
                         where ((p.datefrom >= :date1) or (:period1 is null)) and
                               ((p.datefrom <= :date2) or (:period2 is null)))) and
        (r.remnbeginnat=0) and
        (r.remnbegincur=0) and
        (r.beginquan=0) and
        (not exists (select * from economicspec s
                     where (s.remnvaldb_id = r.id) or (s.remnvalkt_id = r.id) ));
end
^

ALTER PROCEDURE ACC_GET_TURN_REALIZ_BASE (
    DATE1 TIMESTAMP,
    DATE2 TIMESTAMP,
    ACC_ID INTEGER)
RETURNS (
    COSTQUANT NUMERIC(15,3),
    COSTSUM NUMERIC(15,4),
    REALIZQUANT NUMERIC(15,3),
    REALIZSUM NUMERIC(15,4),
    PARTNER_CODE CHAR(20),
    DOCBASETYPE CHAR(15),
    DOCBASENUMBER CHAR(20),
    DOCBASEDATE TIMESTAMP)
AS
declare variable eo_id integer;
declare variable catalog_id integer;
begin
  /**********************************************************
   Автор: Константин Кривопустов
   Создано: 07.06.2001
   Измененено:
   Назначение: оборотная ведомость по реализации (по покупателю и основанию)
   Используется: сервером приложения
  **********************************************************/
  for
    select eo.id, c.code, eo.docbasetype, eo.docbasenumber, eo.docbasedate,
      es1.catalog_id, sum(es1.quantity), sum(es1.summanat)
    from economicoper eo
      join economicspec es1 on es1.economicoper_id = eo.id
      left join contractor c on c.id = eo.to_id
    where (eo.keepdate between :date1 and :date2) and (es1.acckt_id = :acc_id)
    group by eo.id, c.code, eo.docbasetype, eo.docbasenumber, eo.docbasedate, es1.catalog_id
    into :eo_id, :partner_code, :docbasetype, :docbasenumber, :docbasedate,
      :catalog_id, :realizquant, :realizsum
  do begin
    select sum(es2.quantity), sum(es2.summanat)
    from economicspec es2
    where (es2.economicoper_id = :eo_id) and (es2.accdb_id = :acc_id) and
      (es2.catalog_id = :catalog_id)
    into :costquant, :costsum;
  
    suspend;
  end
end
^

ALTER PROCEDURE ACC_GET_TURN_REALIZ_PRODUCT (
    DATE1 TIMESTAMP,
    DATE2 TIMESTAMP,
    ACC_ID INTEGER)
RETURNS (
    COSTQUANT NUMERIC(15,3),
    COSTSUM NUMERIC(15,4),
    REALIZQUANT NUMERIC(15,3),
    REALIZSUM NUMERIC(15,4),
    CATALOG_ID INTEGER,
    PARTNER_CODE CHAR(20))
AS
declare variable eo_id integer;
begin
  /**********************************************************
   Автор: Константин Кривопустов
   Создано: 07.06.2001
   Измененено:
   Назначение: оборотная ведомость по реализации (по продукту)
   Используется: сервером приложения
  **********************************************************/
  for
    select eo.id, c.code, es1.catalog_id, sum(es1.quantity), sum(es1.summanat)
    from economicoper eo
      join economicspec es1 on es1.economicoper_id = eo.id
      left join contractor c on c.id = eo.to_id
    where (eo.keepdate between :date1 and :date2) and (es1.acckt_id = :acc_id)
    group by eo.id, c.code, es1.catalog_id
    into :eo_id, :partner_code, :catalog_id, :realizquant, :realizsum
  do begin
    select sum(es2.quantity), sum(es2.summanat)
    from economicspec es2
    where (es2.economicoper_id = :eo_id) and (es2.accdb_id = :acc_id) and
      (es2.catalog_id = :catalog_id)
    into :costquant, :costsum;
  
    suspend;
  end
end
^

ALTER PROCEDURE ACC_INCLUDE_TURN_IN_REMNACC (
    ACC INTEGER,
    PERIOD_ID INTEGER)
RETURNS (
    REMNACC_ID INTEGER)
AS
  declare variable ans integer;
begin
  /* find real remnacc */
  select r.id
  from remnacc r
  where (r.acc_id = :Acc) and (r.period_id = :period_id)
  into :remnacc_id;

  ans = WI('remnacc_id',remnacc_id);
  if (remnacc_id is null) then
  begin
    /* insert remnacc */
    remnacc_id = gen_id(remnacc_id_gen,1);
    ans = WI('remnacc_id',remnacc_id);
    insert into remnacc(id, PERIOD_ID, ACC_ID, REMNBEGINNATDB, REMNBEGINNATKT)
    values
    (:remnacc_id,:period_id,:Acc,0,0);
  end
end
^

ALTER PROCEDURE ACC_INCLUDE_TURN_IN_REMNANL (
    ACC INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    PERIOD_ID INTEGER)
RETURNS (
    REMNANL_ID INTEGER)
AS
begin
  /* find real remnanl */
  select r.id
  from remnanl r
  where ( r.acc_id = :Acc ) and ( r.period_id = :period_id ) and
    ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and 
    ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and 
    ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and 
    ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and 
    ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null)))
  into :RemnAnl_id;

  if ( RemnAnl_Id is null ) then begin
    /* insert remnanl */
    RemnAnl_Id = gen_id(remnanl_id_gen,1);
    insert into remnanl(id,period_id,acc_id,remnbeginnatdb,remnbeginnatkt,
      remnbegincurdb,remnbegincurkt,
      anlplan1_id,anlplan2_id,anlplan3_id,anlplan4_id,anlplan5_id)
    values
    (:RemnAnl_Id,:period_id,:Acc,0,0,
     0,0,
     :Anl1,:Anl2,:Anl3,:Anl4,:Anl5);
  end
end
^

ALTER PROCEDURE ACC_INCLUDE_TURN_IN_REMNDBKT (
    ACC INTEGER,
    ANLFORM SMALLINT,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    PERIOD_ID INTEGER,
    TURNDB NUMERIC(15,4),
    TURNKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    CONTR INTEGER,
    DOCBASETYPE VARCHAR(15),
    DOCBASENUMBER VARCHAR(20),
    DOCBASEDATE TIMESTAMP,
    DOCTYPE VARCHAR(15),
    DOCNUMBER VARCHAR(20),
    DOCDATE TIMESTAMP,
    SPECMARK CHAR(20))
RETURNS (
    REMNDBKT_ID INTEGER)
AS
  declare variable ans smallint;
begin
  /* Check contractor */
  REMNDBKT_ID = null;
  if (contr is null) then begin
    if ((turndb <> 0) or (turncurdb <> 0)) then
      exception E_CONTRACTOR_TO_EMPTY;
    else if ((turnkt <> 0) or (turncurkt <> 0)) then exception E_CONTRACTOR_FROM_EMPTY;

    if ((turndb = 0) and (turncurdb = 0) and (turnkt = 0) and (turncurkt = 0)) then
      exit;
  end
  /* find line */
  select r.id
  from remndbkt r
  where (r.period_id = :period_id) and (r.acc_id = :acc) and
    ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and 
    ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and 
    ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and 
    ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and 
    ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null))) and
    ((r.contractor_id = :contr)/*or(r.contractor_id is null)*/) and
    (r.docbasetype = :docbasetype) and (DelSpace(r.docbasenumber) = DelSpace(:docbasenumber)) and (r.docbasedate = :docbasedate) and
    (r.doctype = :doctype) and (DelSpace(r.docnumber) = DelSpace(:docnumber)) and (r.docdate = :docdate)
  into :RemnDbKt_Id;
    
  if ( RemnDbKt_Id is null ) then begin
    /* insert remndbkt */
    ans = WS('dbt',docbasetype);
    ans = WS('dt',doctype);
    RemnDbKt_Id = gen_id(remndbkt_id_gen,1);
    insert into remndbkt(ID,PERIOD_ID,ACC_ID,CONTRACTOR_ID,
      ANLPLAN1_ID,ANLPLAN2_ID,ANLPLAN3_ID,ANLPLAN4_ID,ANLPLAN5_ID,
      REMNBEGINNATDB,REMNBEGINNATKT,
      REMNBEGINCURDB,REMNBEGINCURKT,
      DOCBASETYPE,DOCBASENUMBER,DOCBASEDATE,DOCTYPE,DOCNUMBER,DOCDATE)
    values
    (:RemnDbKt_id,:period_id,:acc,:contr,
     :Anl1,:Anl2,:Anl3,:Anl4,:Anl5,
     0,0,0,0,
     :docbasetype,:docbasenumber,:docbasedate,:doctype,:docnumber,:docdate);
  end
end
^

ALTER PROCEDURE ACC_INCLUDE_TURN_IN_REMNVAL (
    ACC INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    PERIOD_ID INTEGER,
    CATALOG_ID INTEGER,
    TURNDB NUMERIC(15,4),
    TURNKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    QUANTITYDB NUMERIC(15,3),
    QUANTITYKT NUMERIC(15,3),
    CONTR INTEGER,
    SPECMARK CHAR(20),
    ACCBATCH_ID INTEGER)
RETURNS (
    REMNVAL_ID INTEGER)
AS
  declare variable anlform smallint;
  declare variable ans integer;
begin
  /* Check contractor */
  if (contr is null) then begin
    if ((turndb <> 0) or (turncurdb <> 0)) then exception E_CONTRACTOR_TO_EMPTY;
    else if ((turnkt <> 0) or (turncurkt <> 0)) then exception E_CONTRACTOR_FROM_EMPTY;
  end 
  
  select a.anlform from accplan a where (a.id = :acc) into :anlform;
  /* If not batchcalc */
  if (anlform <> 4) then accbatch_id = null;
  
  /* find real remnval */
  select r.id
  from remnval r
  where (r.acc_id = :Acc) and (r.period_id = :period_id) and
    ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and 
    ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and 
    ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and 
    ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and 
    ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null))) and
    ((r.batch_id = :accbatch_id)or((r.batch_id is null)and(:accbatch_id is null))) and
    ( r.catalog_id = :catalog_id ) and
    ((r.contractor_id = :contr)or(r.contractor_id is null))
  into :RemnVal_id;
  
  if ( RemnVal_Id is null ) then begin
    /* insert remnval */
    RemnVal_Id = gen_id(remnval_id_gen,1);
    insert into remnval(ID,PERIOD_ID,ACC_id,CATALOG_ID,ANLPLAN1_ID,ANLPLAN2_ID,ANLPLAN3_ID,ANLPLAN4_ID,ANLPLAN5_ID,
      BEGINQUAN,REMNBEGINNAT,REMNBEGINCUR,BATCH_ID,contractor_id)
    values
    (:RemnVal_Id,:period_id,:Acc,:catalog_id,:Anl1,:Anl2,:Anl3,:Anl4,:Anl5,
     0,0,0,:accbatch_id,:contr);
  end
end
^

ALTER PROCEDURE ACC_INV_FREEZE (
    INVID INTEGER,
    FREEZEDATE TIMESTAMP)
AS
declare variable AmsId integer;
declare variable FreezeMonth integer;
declare variable FreezeYear integer;
declare variable AbsMonth integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Измененено: 11.02.2002
 Назначение: заморозка инв.объекта
 Используется: сервером приложения
**********************************************************/
  /*execute procedure F_YEAR(FreezeDate) returning_values FreezeYear;*/
  FreezeYear = extract(year from FreezeDate);
  /*execute procedure F_MONTH(FreezeDate) returning_values FreezeMonth;*/
  FreezeMonth = extract(month from FreezeDate);
  AbsMonth = FreezeYear * 12 + FreezeMonth;
  AmsId = gen_id(ACC_AMORTIZATION_ID_GEN,1);
  insert into ACC_AMORTIZATION (id, inventory_id, imonth, n_months)
    values (:AmsId, :InvId, :AbsMonth, 0);
  insert into invhistory (ID, INVENTORY_ID, AMORTIZATION_ID, KIND, ACT_DATE)
    values (gen_id(INVHISTORY_ID_GEN,1), :InvId, :AmsId, 4, 'TODAY');
end
^

ALTER PROCEDURE ACC_INV_MAKE_REMAINS (
    INVID INTEGER,
    PERIODID INTEGER)
AS
 declare variable Acc integer;
 declare variable Anl1 integer;  
 declare variable Anl2 integer;  
 declare variable Anl3 integer;  
 declare variable Anl4 integer;  
 declare variable Anl5 integer;  
 declare variable CatId integer;
 declare variable contr integer;
 declare variable BalCost double precision;
 declare variable RemnValId integer; 
 declare variable RemnAccId integer; 
 declare variable RemnDbKtId integer; 
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Измененено: 11.02.2002, 14.03.2002
 Назначение: создание остатков по инвентарным карточкам
 Используется: сервером приложения
**********************************************************/
  select i.contractor_id, i.BALANCECOST, i.ACC_id,
     i.ANL1_ID, i.ANL2_ID, i.ANL3_ID, i.ANL4_ID, i.ANL5_ID, i.CATALOG_ID
    from inventory i where i.id = :InvId
    into :contr, :BalCost, :Acc, :Anl1, :Anl2,
       :Anl3, :Anl4, :Anl5, :CatId;
  /* process remnval */
  select r.id from remnval r
    where (r.acc_id = :Acc) and (r.period_id = :PeriodId) and
      ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and 
      ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and 
      ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and 
      ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and 
      ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null))) and
      (r.catalog_id = :CatId) and
      ((r.contractor_id = :contr)or(r.contractor_id is null))
    into :RemnValId;
  if (RemnValId is null) then begin
    RemnValId = gen_id(remnval_id_gen,1);
    insert into remnval(ID,PERIOD_ID,ACC_ID,CATALOG_ID,ANLPLAN1_ID,ANLPLAN2_ID,ANLPLAN3_ID,ANLPLAN4_ID,ANLPLAN5_ID,
      BEGINQUAN,REMNBEGINNAT,CONTRACTOR_ID)
    values
    (:RemnValId,:PeriodId,:Acc,:CatId,:Anl1,:Anl2,:Anl3,:Anl4,:Anl5,
     1,:BalCost,:contr);
  end
  else begin
    update remnval set 
      BEGINQUAN = BEGINQUAN + 1, REMNBEGINNAT = REMNBEGINNAT + :BalCost
      where id = :RemnValId;
  end
  /* process remnacc */
  select r.id from remnacc r
    where (r.acc_id = :Acc) and (r.period_id = :PeriodId)
    into :RemnAccId;
  if (RemnAccId is null) then begin
    RemnAccId = gen_id(remnacc_id_gen,1);
    insert into remnacc (id, PERIOD_ID, ACC_ID, REMNBEGINNATDB, REMNBEGINNATKT)
      values (:RemnAccId,:PeriodId,:Acc,:BalCost,0);
  end
  else begin
    update remnacc set
      REMNBEGINNATDB = REMNBEGINNATDB + :BalCost
      where id = :RemnAccId;  
  end  
  /* process remndbkt */
  select r.id from remndbkt r
    where (r.acc_id = :Acc) and (r.period_id = :PeriodId) and
      ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and 
      ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and 
      ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and 
      ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and 
      ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null))) and
      ((r.contractor_id = :contr)or(r.contractor_id is null))
    into :RemnDbKtId;
  if (RemnDbKtId is null) then begin
    RemnDbKtId = gen_id(remndbkt_id_gen,1);
    insert into remndbkt (ID,PERIOD_ID,ACC_ID,ANLPLAN1_ID,ANLPLAN2_ID,ANLPLAN3_ID,ANLPLAN4_ID,ANLPLAN5_ID,
      REMNBEGINNATDB,REMNBEGINNATKT,contractor_id)
    values
    (:RemnDbKtId,:PeriodId,:Acc,:Anl1,:Anl2,:Anl3,:Anl4,:Anl5,
     :BalCost,0,:contr);
  end
  else begin
    update remndbkt set 
      REMNBEGINNATDB = REMNBEGINNATDB + :BalCost
      where id = :RemnDbKtId;
  end
end
^

ALTER PROCEDURE ACC_INV_MOVE (
    INVID INTEGER,
    EOFOLDERID INTEGER,
    REGDATE TIMESTAMP,
    DOCTYPE CHAR(15),
    DOCNUMBER CHAR(20),
    DOCDATE TIMESTAMP,
    CONTR INTEGER,
    ACCINV INTEGER,
    ANLINV1ID INTEGER,
    ANLINV2ID INTEGER,
    ANLINV3ID INTEGER,
    ANLINV4ID INTEGER,
    ANLINV5ID INTEGER,
    ACCAM INTEGER,
    ANLAM1ID INTEGER,
    ANLAM2ID INTEGER,
    ANLAM3ID INTEGER,
    ANLAM4ID INTEGER,
    ANLAM5ID INTEGER,
    IL_ID INTEGER,
    USER_ID INTEGER,
    INOPERDOCNUM CHAR(20),
    INOPERDATE TIMESTAMP)
AS
DECLARE VARIABLE OPERID INTEGER;
DECLARE VARIABLE SPECID INTEGER;
DECLARE VARIABLE SAVESPECID INTEGER;
declare variable old_il_id varchar(20);
declare variable old_inoperdocnum char(20);
declare variable old_inoperdate timestamp;
declare variable tmp_il_id varchar(20);
declare variable tmp_inoperdocnum char(20);
declare variable tmp_inoperdate timestamp;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: перемещение инв.объекта
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 11.02.2002 Кривопустов 
 28.05.2002 Кривопустов [+] Код местонахождения
 03.10.2002 Кривопустов [+] Акт ввода в эксплуатацию
**********************************************************/
  /**/
  OperId = gen_id(economicoper_id_gen, 1);
  if (Contr is not null) then
    insert into economicoper (id, folder_id, comment, keepdate, insertsign,
       doctype, docnumber, docdate,
       from_id, to_id, user_id)
      select :OperId, :EOFolderId, 'Перемещение ОС', :RegDate, 1,
        :DocType, :DocNumber, :DocDate,
        i.contractor_id, :Contr, :user_id
        from inventory i where i.id = :InvId;
  else
    insert into economicoper (id, folder_id, comment, keepdate, insertsign,
       doctype, docnumber, docdate, user_id, contractdate)
      values (:OperId, :EOFolderId, 'Перемещение ОС', :RegDate, 1,
        :DocType, :DocNumber, :DocDate, :user_id, '30.12.1899');
  /**/
  if (AccInv is not null) then begin
    /* write off balance cost */
    SpecId = gen_id(ECONOMICSPEC_ID_GEN,1);
    SaveSpecId = SpecId;
    insert into economicspec (id, ECONOMICOPER_ID, 
       ACCDB_ID, ANLDB1_ID, ANLDB2_ID, ANLDB3_ID, ANLDB4_ID, ANLDB5_ID,
       ACCKT_ID, ANLKT1_ID, ANLKT2_ID, ANLKT3_ID, ANLKT4_ID, ANLKT5_ID,
       CATALOG_ID, QUANTITY, SUMMANAT)
      select :SpecId, :OperId,
          :AccInv, :AnlInv1Id, :AnlInv2Id, :AnlInv3Id, :AnlInv4Id, :AnlInv5Id,
          i.acc_id, i.ANL1_ID, i.ANL2_ID, i.ANL3_ID, i.ANL4_ID, i.ANL5_ID,
          i.CATALOG_ID, 1, i.BALANCECOST
        from inventory i
        where i.id = :InvId;
    if (AccAm is not null) then begin
      /* write off depreciation */
      SpecId = gen_id(ECONOMICSPEC_ID_GEN,1);
      insert into economicspec (id, ECONOMICOPER_ID, 
         ACCDB_id, ANLDB1_ID, ANLDB2_ID, ANLDB3_ID, ANLDB4_ID, ANLDB5_ID,
         ACCKT_id, ANLKT1_ID, ANLKT2_ID, ANLKT3_ID, ANLKT4_ID, ANLKT5_ID,
         CATALOG_ID, QUANTITY, SUMMANAT)
        select :SpecId, :OperId,
            i.acckt_id, i.ANLKT1_ID, i.ANLKT2_ID, i.ANLKT3_ID, i.ANLKT4_ID, i.ANLKT5_ID,
            :AccAm, :AnlAm1Id, :AnlAm2Id, :AnlAm3Id, :AnlAm4Id, :AnlAm5Id,
            i.CATALOG_ID, 1, i.AMORT
          from inventory i
          where i.id = :InvId;
    end
  end
  else begin
    SpecId = gen_id(ECONOMICSPEC_ID_GEN,1);
    SaveSpecId = SpecId;
    insert into economicspec (id, ECONOMICOPER_ID, 
       ACCDB_id, ANLDB1_ID, ANLDB2_ID, ANLDB3_ID, ANLDB4_ID, ANLDB5_ID,
       ACCKT_id, ANLKT1_ID, ANLKT2_ID, ANLKT3_ID, ANLKT4_ID, ANLKT5_ID,
       CATALOG_ID, QUANTITY, SUMMANAT)
      select :SpecId, :OperId,
          i.ACC_id, i.ANL1_ID, i.ANL2_ID, i.ANL3_ID, i.ANL4_ID, i.ANL5_ID,
          i.ACC_id, i.ANL1_ID, i.ANL2_ID, i.ANL3_ID, i.ANL4_ID, i.ANL5_ID,
          i.CATALOG_ID, 1, i.BALANCECOST
        from inventory i
        where i.id = :InvId;
  end 
  /* update inventory card */
  /*update поля contractor_id происходит при срабатывании триггера
    на таблице acc_invhead, здесь оставлено на всякий случай*/
  if (Contr is not null) then
    update INVENTORY set
      contractor_id = :Contr
      where id = :InvId;
  if (AccInv is not null) then
    update INVENTORY set
      acc_id = :AccInv, anl1_id = :AnlInv1Id, anl2_id = :AnlInv2Id,
      anl3_id = :AnlInv3Id, anl4_id = :AnlInv4Id, anl5_id = :AnlInv5Id
      where id = :InvId;
  /**/
  select i.invlocation_id, i.inoperdocnum, i.inoperdate
  from inventory i where i.id = :invid
  into :tmp_il_id, :tmp_inoperdocnum, :tmp_inoperdate;

  if (il_id is not null) then old_il_id = tmp_il_id;
  else old_il_id = null;

  if (inoperdocnum is not null) then begin
    old_inoperdocnum = tmp_inoperdocnum;
    old_inoperdate = tmp_inoperdate;
  end
  else begin
    old_inoperdocnum = null;
    old_inoperdate = null;
  end

  insert into invhistory (ID, INVENTORY_ID, EO_ID, ES_ID, KIND, ACT_DATE, old_invlocation_id, old_inoperdocnum, old_inoperdate)
    values (gen_id(INVHISTORY_ID_GEN,1), :InvId, :OperId, :SaveSpecId, 2, :RegDate, :old_il_id, :old_inoperdocnum, :old_inoperdate);
  /**/
  update ECONOMICOPER set INSERTSIGN = 0, user_id = :user_id where ID = :OperId;
end
^

ALTER PROCEDURE ACC_INV_RETIRE (
    INVID INTEGER,
    EOFOLDERID INTEGER,
    REGDATE TIMESTAMP,
    DOCTYPE CHAR(15),
    DOCNUMBER CHAR(20),
    DOCDATE TIMESTAMP,
    DOCBASETYPE CHAR(15),
    DOCBASENUMBER CHAR(20),
    DOCBASEDATE TIMESTAMP,
    ACC INTEGER,
    ANL1ID INTEGER,
    ANL2ID INTEGER,
    ANL3ID INTEGER,
    ANL4ID INTEGER,
    ANL5ID INTEGER,
    USER_ID INTEGER)
AS
DECLARE VARIABLE OPERID INTEGER;
DECLARE VARIABLE SPECID INTEGER;
DECLARE VARIABLE SAVESPECID INTEGER;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Измененено: 11.02.2002
 Назначение: списание инв.объекта
 Используется: сервером приложения
**********************************************************/
  /**/
  OperId = gen_id(economicoper_id_gen, 1);
  insert into economicoper (id, folder_id, comment, keepdate, insertsign,
     doctype, docnumber, docdate, docbasetype, docbasenumber, docbasedate, from_id, user_id, contractdate)
    select :OperId, :EOFolderId, 'Списание ОС', :RegDate, 1,
      :DocType, :DocNumber, :DocDate, :docbasetype, :docbasenumber, :docbasedate, i.contractor_id, :user_id, '30.12.1899'
      from inventory i where i.id = :InvId;
  /* write off balance cost */
  SpecId = gen_id(ECONOMICSPEC_ID_GEN,1);
  SaveSpecId = SpecId;
  insert into economicspec (id, ECONOMICOPER_ID, 
     ACCDB_ID, ANLDB1_ID, ANLDB2_ID, ANLDB3_ID, ANLDB4_ID, ANLDB5_ID,
     ACCKT_ID, ANLKT1_ID, ANLKT2_ID, ANLKT3_ID, ANLKT4_ID, ANLKT5_ID,
     CATALOG_ID, QUANTITY, SUMMANAT)
    select :SpecId, :OperId,
        :Acc, :Anl1Id, :Anl2Id, :Anl3Id, :Anl4Id, :Anl5Id,
        i.ACC_ID, i.ANL1_ID, i.ANL2_ID, i.ANL3_ID, i.ANL4_ID, i.ANL5_ID,
        i.CATALOG_ID, 1, i.BALANCECOST
      from inventory i
      where i.id = :InvId;
  /* write off depreciation */
  SpecId = gen_id(ECONOMICSPEC_ID_GEN,1);
  insert into economicspec (id, ECONOMICOPER_ID, 
     ACCDB_ID, ANLDB1_ID, ANLDB2_ID, ANLDB3_ID, ANLDB4_ID, ANLDB5_ID,
     ACCKT_ID, ANLKT1_ID, ANLKT2_ID, ANLKT3_ID, ANLKT4_ID, ANLKT5_ID,
     CATALOG_ID, QUANTITY, SUMMANAT)
    select :SpecId, :OperId,
        i.acckt_id, i.ANLKT1_ID, i.ANLKT2_ID, i.ANLKT3_ID, i.ANLKT4_ID, i.ANLKT5_ID,
        :Acc, :Anl1Id, :Anl2Id, :Anl3Id, :Anl4Id, :Anl5Id,
        i.CATALOG_ID, 1, i.AMORT+i.BEGINLOSS
      from inventory i
      where i.id = :InvId;
  /* update inventory card */
  /*update полей outoperdocnum, otoperdate происходит при срабатывании триггера
    на таблице acc_invhead, здесь оставлено на всякий случай*/
  update INVENTORY set
    outoperdocnum = :DocNumber, outoperdate = :DocDate
    where id = :InvId;
  /**/
  insert into invhistory (ID, INVENTORY_ID, EO_ID, ES_ID, KIND, ACT_DATE)
    values (gen_id(INVHISTORY_ID_GEN,1), :InvId, :OperId, :SaveSpecId, 3, :RegDate);
  /**/
  update ECONOMICOPER set INSERTSIGN = 0, user_id = :user_id where ID = :OperId;
end
^

ALTER PROCEDURE ACC_INV_REVALUATE (
    INVID INTEGER,
    EOFOLDERID INTEGER,
    REVALDATE TIMESTAMP,
    KIND SMALLINT,
    FACTOR DOUBLE PRECISION,
    AMOUNT NUMERIC(15,4),
    DELTA NUMERIC(15,4),
    DOCTYPE CHAR(15),
    DOCNUMBER CHAR(20),
    DOCDATE TIMESTAMP,
    BASEDOCTYPE CHAR(15),
    BASEDOCNUMBER CHAR(20),
    BASEDOCDATE TIMESTAMP,
    ACC INTEGER,
    ANL1ID INTEGER,
    ANL2ID INTEGER,
    ANL3ID INTEGER,
    ANL4ID INTEGER,
    ANL5ID INTEGER,
    USER_ID INTEGER,
    IS_OVERESTIMATE SMALLINT)
AS
declare variable OldAmount numeric(15,4);
declare variable NewAmount numeric(15,4);
declare variable OldAmort numeric(15,4);
declare variable NewAmort numeric(15,4);
declare variable BeginLoss numeric(15,4);
declare variable NewBeginLoss numeric(15,4);
declare variable InitialLoss numeric(15,4);
declare variable NewInitialLoss numeric(15,4);
declare variable OperId integer;
declare variable SpecId integer;
declare variable Contr integer;
declare variable caption varchar(80);
declare variable hist_kind smallint;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Измененено: 11.02.2002, 22.02.2002, 03.04.2002
 Назначение: переоценка/дооценка инв.объекта
 Используется: сервером приложения
**********************************************************/
  /**/
  select i.balancecost, i.amort, i.beginloss, i.initialloss, i.contractor_id
  from inventory i
  where i.id = :InvId
  into :OldAmount, :oldamort, :beginloss, :initialloss, :Contr;

  if (Kind = 0) then NewAmount = OldAmount * Factor;
  else if (Kind = 1) then begin
    NewAmount = Amount;
    Factor = NewAmount / OldAmount;
  end
  else if (Kind = 2) then begin
    NewAmount = OldAmount + Delta;
    Factor = NewAmount / OldAmount;
  end

  if (is_overestimate = 0) then begin
    NewAmort = oldamort * Factor;
    NewBeginLoss = BeginLoss * Factor;
    NewInitialLoss = InitialLoss * Factor;
  end

  /**/
  if (is_overestimate = 0) then caption = 'Переоценка';
  else caption = 'Дооценка';

  OperId = gen_id(economicoper_id_gen, 1);
  insert into economicoper (id, folder_id, comment, keepdate,
      doctype, docnumber, docdate, docbasetype, docbasenumber, docbasedate,
      insertsign, from_id, to_id, user_id, contractdate)
    values (:OperId, :EOFolderId, :caption, :RevalDate,
      :doctype, :docnumber, :docdate, :basedoctype, :basedocnumber, :basedocdate,
      1, :Contr, :Contr, :user_id, '30.12.1899');
  /* update inventory card */
  if (is_overestimate = 0) then 
    update INVENTORY set
      balancecost = :NewAmount,
      beginloss = :newbeginloss,
      initialloss = :newinitialloss,
      amort = :newamort,
      endcost = :NewAmount - initialloss - beginloss - amort
      where id = :InvId;
  else
    update INVENTORY set
      balancecost = :NewAmount,
      endcost = :NewAmount - initialloss - beginloss - amort
      where id = :InvId;
  /**/
  SpecId = gen_id(ECONOMICSPEC_ID_GEN,1);
  if (NewAmount >= OldAmount) then begin
    insert into economicspec (id, ECONOMICOPER_ID, 
       ACCDB_ID, ANLDB1_ID, ANLDB2_ID, ANLDB3_ID, ANLDB4_ID, ANLDB5_ID,
       ACCKT_ID, ANLKT1_ID, ANLKT2_ID, ANLKT3_ID, ANLKT4_ID, ANLKT5_ID,
       CATALOG_ID, QUANTITY, SUMMANAT)
      select :SpecId, :OperId,
          i.ACC_ID, i.ANL1_ID, i.ANL2_ID, i.ANL3_ID, i.ANL4_ID, i.ANL5_ID,
          :Acc, :Anl1Id, :Anl2Id, :Anl3Id, :Anl4Id, :Anl5Id,
          i.CATALOG_ID, 0, :NewAmount - :OldAmount
        from inventory i
        where i.id = :InvId;
    if (is_overestimate = 0) then begin
      SpecId = gen_id(ECONOMICSPEC_ID_GEN,1);
      insert into economicspec (id, ECONOMICOPER_ID, 
         ACCDB_ID, ANLDB1_ID, ANLDB2_ID, ANLDB3_ID, ANLDB4_ID, ANLDB5_ID,
         ACCKT_ID, ANLKT1_ID, ANLKT2_ID, ANLKT3_ID, ANLKT4_ID, ANLKT5_ID,
         CATALOG_ID, QUANTITY, SUMMANAT)
        select :SpecId, :OperId,
            :Acc, :Anl1Id, :Anl2Id, :Anl3Id, :Anl4Id, :Anl5Id,
            i.acckt_id, i.anlkt1_id, i.ANLkt2_ID, i.ANLkt3_ID, i.ANLkt4_ID, i.ANLkt5_ID,
            i.CATALOG_ID, 0, :NewAmort+:newbeginloss+:newinitialloss-:OldAmort-:beginloss-:initialloss
          from inventory i
          where i.id = :InvId;
      SpecId = null;
    end
  end
  else begin
    insert into economicspec (id, ECONOMICOPER_ID, 
       ACCDB_ID, ANLDB1_ID, ANLDB2_ID, ANLDB3_ID, ANLDB4_ID, ANLDB5_ID,
       ACCKT_ID, ANLKT1_ID, ANLKT2_ID, ANLKT3_ID, ANLKT4_ID, ANLKT5_ID,
       CATALOG_ID, QUANTITY, SUMMANAT)
      select :SpecId, :OperId,
          :Acc, :Anl1Id, :Anl2Id, :Anl3Id, :Anl4Id, :Anl5Id,
          i.ACC_ID, i.ANL1_ID, i.ANL2_ID, i.ANL3_ID, i.ANL4_ID, i.ANL5_ID,
          i.CATALOG_ID, 0, :OldAmount - :NewAmount
        from inventory i
        where i.id = :InvId;
    if (is_overestimate = 0) then begin
      SpecId = gen_id(ECONOMICSPEC_ID_GEN,1);
      insert into economicspec (id, ECONOMICOPER_ID, 
         ACCDB_ID, ANLDB1_ID, ANLDB2_ID, ANLDB3_ID, ANLDB4_ID, ANLDB5_ID,
         ACCKT_ID, ANLKT1_ID, ANLKT2_ID, ANLKT3_ID, ANLKT4_ID, ANLKT5_ID,
         CATALOG_ID, QUANTITY, SUMMANAT)
        select :SpecId, :OperId,
            i.acckt_id, i.anlkt1_id, i.ANLkt2_ID, i.ANLkt3_ID, i.ANLkt4_ID, i.ANLkt5_ID,
            :Acc, :Anl1Id, :Anl2Id, :Anl3Id, :Anl4Id, :Anl5Id,
            i.CATALOG_ID, 0, :OldAmort+:beginloss+:initialloss-:NewAmort-:newbeginloss-:newinitialloss
          from inventory i
          where i.id = :InvId;
      SpecId = null;
    end
  end
  /**/
  if (is_overestimate = 0) then hist_kind = 1;
  else hist_kind = 5;

  insert into invhistory (ID, INVENTORY_ID, 
     EO_ID, ES_ID, KIND, DELTA_BALCOST,
     delta_deprval, delta_beginloss, delta_initialloss,
     REVAL_FACTOR, REVAL_SUM, ACT_DATE)
    values (gen_id(INVHISTORY_ID_GEN,1), :InvId, 
     :OperId, :SpecId, :hist_kind, :NewAmount - :OldAmount,
     :NewAmort-:OldAmort, :newbeginloss-:beginloss, :newinitialloss-:initialloss,
     :Factor, :Amount, :RevalDate);
  /**/
  update ECONOMICOPER set INSERTSIGN = 0, user_id = :user_id where ID = :OperId;
end
^

ALTER PROCEDURE ACC_OSDB (
    ACC INTEGER,
    ADATE TIMESTAMP,
    ONBEGINDAY SMALLINT)
RETURNS (
    REMN NUMERIC(15,4))
AS
declare variable RemnNatKt numeric(15,4);
declare variable RemnCurDb numeric(15,4);
declare variable RemnCurKt numeric(15,4);
begin
  execute procedure F_CALC_REMANTACC_ONDATE(:ACC, :ADATE, :ONBEGINDAY)
    returning_values :Remn, :RemnCurDb, :RemnNatKt, :RemnCurKt;
  suspend;
end
^

ALTER PROCEDURE ACC_OSKT (
    ACC INTEGER,
    ADATE TIMESTAMP,
    ONBEGINDAY SMALLINT)
RETURNS (
    REMN NUMERIC(15,4))
AS
declare variable RemnNatDb numeric(15,4);
declare variable RemnCurDb numeric(15,4);
declare variable RemnCurKt numeric(15,4);
begin
  execute procedure F_CALC_REMANTACC_ONDATE(:ACC, :ADATE, :ONBEGINDAY)
    returning_values :RemnNatDb, :RemnCurDb, :Remn, :RemnCurKt;
  suspend;
end
^

ALTER PROCEDURE ACC_REMNACC_LOAD (
    REMN_ID INTEGER)
RETURNS (
    ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    ACC_ID INTEGER,
    ACC CHAR(20),
    ACCFOLDER_ID INTEGER,
    CURCODE CHAR(5),
    REMNBEGINNATDB NUMERIC(15,4),
    REMNBEGINNATKT NUMERIC(15,4),
    REMNBEGINCURDB NUMERIC(15,4),
    REMNBEGINCURKT NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    REMNENDNATDB NUMERIC(15,4),
    REMNENDNATKT NUMERIC(15,4),
    REMNENDCURDB NUMERIC(15,4),
    REMNENDCURKT NUMERIC(15,4))
AS
begin
  select r.id, r.period_id, p.pname, a.acc, c.code, r.acc_id, a.folder_id,
    r.remnbeginnatdb, r.remnbeginnatkt, r.remnbegincurdb, r.remnbegincurkt
  from remnacc r left join period p on p.id = r.period_id
    left join accplan a on r.acc_id = a.id
    left join currency c on c.upcode = a.currency_code
  where (r.id = :remn_id)
  into :id, :period_id, :pname, :acc, :curcode, :acc_id, :accfolder_id,
    :remnbeginnatdb, :remnbeginnatkt, :remnbegincurdb, :remnbegincurkt;

  select sum(es.summacur), sum(es.summanat)
  from economicspec es
  where (es.remnaccdb_id = :id)
  into :turncurdb, :turnnatdb;

  if (turncurdb is null) then turncurdb = 0;
  if (turnnatdb is null) then turnnatdb = 0;

  select sum(es.summacur), sum(es.summanat)
  from economicspec es
  where (es.remnacckt_id = :id)
  into :turncurkt, :turnnatkt;

  if (turncurkt is null) then turncurkt = 0;
  if (turnnatkt is null) then turnnatkt = 0;

  execute procedure acc_calc_remnacc_end(:acc_id, :period_id,
    :remnbeginnatdb, :remnbeginnatkt,
    :remnbegincurdb, :remnbegincurkt,
    :TurnnatDb, :TurnnatKt,
    :TurnCurDb, :TurnCurKt)
  returning_values :remnendnatdb, :remnendnatkt, :remnendcurdb, :remnendcurkt;

  suspend;
end
^

ALTER PROCEDURE ACC_REMNACC_SELECT (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER,
    ACCOUNT INTEGER,
    ACCOUNT_FOLDER_ID INTEGER)
RETURNS (
    ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    DATEFROM TIMESTAMP,
    DATETO TIMESTAMP,
    ACC_ID INTEGER,
    ACC CHAR(20),
    ACCFOLDER_ID INTEGER,
    CURCODE CHAR(5),
    REMNBEGINNATDB NUMERIC(15,4),
    REMNBEGINNATKT NUMERIC(15,4),
    REMNBEGINCURDB NUMERIC(15,4),
    REMNBEGINCURKT NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    REMNENDNATDB NUMERIC(15,4),
    REMNENDNATKT NUMERIC(15,4),
    REMNENDCURDB NUMERIC(15,4),
    REMNENDCURKT NUMERIC(15,4))
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
BEGIN
  if (period1 is not null) then
    select p.datefrom from period p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from period p where p.id = :period2 into :date2;

  for
    select r.id, r.period_id, p.datefrom, p.dateto, p.pname, a.acc, a.folder_id, c.code,
      r.acc_id, r.remnbeginnatdb, r.remnbeginnatkt, r.remnbegincurdb, r.remnbegincurkt
    from remnacc r left join period p on p.id = r.period_id
      left join accplan a on r.acc_id = a.id
      left join currency c on c.upcode = a.currency_code
    where
      ((p.datefrom >= :date1) or (:period1 is null) or (:period1 = 0)) and
      ((p.datefrom <= :date2) or (:period2 is null) or (:period2 = 0)) and
      ((a.folder_id = :account_folder_id) or (:account_folder_id is null) or (:account_folder_id = 0)) and
      ((r.acc_id = :account) or (:account is null) or (:account = 0))
    into :id, :period_id, :datefrom, :dateto, :pname, :acc, :accfolder_id, :curcode,
      :acc_id, :remnbeginnatdb, :remnbeginnatkt, :remnbegincurdb, :remnbegincurkt
  do
  begin
    select sum(es.summacur), sum(es.summanat)
    from economicspec es
    where (es.remnaccdb_id = :id)
    into :turncurdb, :turnnatdb;
  
    if (turncurdb is null) then turncurdb = 0;
    if (turnnatdb is null) then turnnatdb = 0;
  
    select sum(es.summacur), sum(es.summanat)
    from economicspec es
    where (es.remnacckt_id = :id)
    into :turncurkt, :turnnatkt;
  
    if (turncurkt is null) then turncurkt = 0;
    if (turnnatkt is null) then turnnatkt = 0;
  
    execute procedure acc_calc_remnacc_end(:Acc_id, :period_id,
      :remnbeginnatdb, :remnbeginnatkt,
      :remnbegincurdb, :remnbegincurkt,
      :TurnnatDb, :TurnnatKt,
      :TurnCurDb, :TurnCurKt)
    returning_values :remnendnatdb, :remnendnatkt, :remnendcurdb, :remnendcurkt;
  
    suspend;
  end
end
^

ALTER PROCEDURE ACC_REMNANL_LOAD (
    REMN_ID INTEGER)
RETURNS (
    ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    ACC_ID INTEGER,
    ACC CHAR(20),
    ACCFOLDER_ID INTEGER,
    CURCODE CHAR(5),
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(10),
    ANL2_CODE CHAR(10),
    ANL3_CODE CHAR(10),
    ANL4_CODE CHAR(10),
    ANL5_CODE CHAR(10),
    REMNBEGINNATDB NUMERIC(15,4),
    REMNBEGINNATKT NUMERIC(15,4),
    REMNBEGINCURDB NUMERIC(15,4),
    REMNBEGINCURKT NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    REMNENDNATDB NUMERIC(15,4),
    REMNENDNATKT NUMERIC(15,4),
    REMNENDCURDB NUMERIC(15,4),
    REMNENDCURKT NUMERIC(15,4))
AS
begin
  select r.id, r.period_id, p.pname, a.acc, c.code, r.acc_id, a.folder_id,
    r.anlplan1_id, r.anlplan2_id, r.anlplan3_id, r.anlplan4_id, r.anlplan5_id,
    a1.code, a2.code, a3.code, a4.code, a5.code,
    r.remnbeginnatdb, r.remnbeginnatkt, r.remnbegincurdb, r.remnbegincurkt
  from remnanl r left join period p on p.id = r.period_id
    left join accplan a on r.acc_id = a.id
    left join currency c on c.upcode = a.currency_code
    left join anlplan a1 on a1.id = r.anlplan1_id
    left join anlplan a2 on a2.id = r.anlplan2_id
    left join anlplan a3 on a3.id = r.anlplan3_id
    left join anlplan a4 on a4.id = r.anlplan4_id
    left join anlplan a5 on a5.id = r.anlplan5_id
  where (r.id = :remn_id)
  into :id, :period_id, :pname, :acc, :curcode, :acc_id, :accfolder_id,
    :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
    :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code,
    :remnbeginnatdb, :remnbeginnatkt, :remnbegincurdb, :remnbegincurkt;

  select sum(es.summacur), sum(es.summanat)
  from economicspec es
  where (es.remnanldb_id = :id)
  into :turncurdb, :turnnatdb;

  if (turncurdb is null) then turncurdb = 0;
  if (turnnatdb is null) then turnnatdb = 0;

  select sum(es.summacur), sum(es.summanat)
  from economicspec es
  where (es.remnanlkt_id = :id)
  into :turncurkt, :turnnatkt;

  if (turncurkt is null) then turncurkt = 0;
  if (turnnatkt is null) then turnnatkt = 0;

  execute procedure acc_calc_remnanl_end(:acc_id, :period_id,
    :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
    :remnbeginnatdb, :remnbeginnatkt,
    :remnbegincurdb, :remnbegincurkt,
    :TurnnatDb, :TurnnatKt,
    :TurnCurDb, :TurnCurKt)
  returning_values :remnendnatdb, :remnendnatkt, :remnendcurdb, :remnendcurkt;

  suspend;
end
^

ALTER PROCEDURE ACC_REMNANL_SELECT (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER,
    ACCOUNT INTEGER,
    ACCOUNT_FOLDER_ID INTEGER)
RETURNS (
    ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    DATEFROM TIMESTAMP,
    DATETO TIMESTAMP,
    ACC_ID INTEGER,
    ACC CHAR(20),
    ACCFOLDER_ID INTEGER,
    CURCODE CHAR(5),
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(10),
    ANL2_CODE CHAR(10),
    ANL3_CODE CHAR(10),
    ANL4_CODE CHAR(10),
    ANL5_CODE CHAR(10),
    REMNBEGINNATDB NUMERIC(15,4),
    REMNBEGINNATKT NUMERIC(15,4),
    REMNBEGINCURDB NUMERIC(15,4),
    REMNBEGINCURKT NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    REMNENDNATDB NUMERIC(15,4),
    REMNENDNATKT NUMERIC(15,4),
    REMNENDCURDB NUMERIC(15,4),
    REMNENDCURKT NUMERIC(15,4))
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
BEGIN
  if (period1 is not null) then
    select p.datefrom from period p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from period p where p.id = :period2 into :date2;

  for
    select r.id, r.period_id, p.datefrom, p.dateto, p.pname, a.acc, a.folder_id, c.code,
      r.acc_id, r.remnbeginnatdb, r.remnbeginnatkt, r.remnbegincurdb, r.remnbegincurkt,
      r.anlplan1_id, r.anlplan2_id, r.anlplan3_id, r.anlplan4_id, r.anlplan5_id,
      a1.code, a2.code, a3.code, a4.code, a5.code
    from remnanl r left join period p on p.id = r.period_id
      left join accplan a on r.acc_id = a.id
      left join currency c on c.upcode = a.currency_code
      left join anlplan a1 on a1.id = r.anlplan1_id
      left join anlplan a2 on a2.id = r.anlplan2_id
      left join anlplan a3 on a3.id = r.anlplan3_id
      left join anlplan a4 on a4.id = r.anlplan4_id
      left join anlplan a5 on a5.id = r.anlplan5_id
    where
      ((p.datefrom >= :date1) or (:period1 is null) or (:period1 = 0)) and
      ((p.datefrom <= :date2) or (:period2 is null) or (:period2 = 0)) and
      ((a.folder_id = :account_folder_id) or (:account_folder_id is null) or (:account_folder_id = 0)) and
      ((r.acc_id = :account) or (:account is null) or (:account = 0))
    into :id, :period_id, :datefrom, :dateto, :pname, :acc, :accfolder_id, :curcode,
      :acc_id, :remnbeginnatdb, :remnbeginnatkt, :remnbegincurdb, :remnbegincurkt,
      :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
      :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code
  do begin
    select sum(es.summacur), sum(es.summanat)
    from economicspec es
    where (es.remnanldb_id = :id)
    into :turncurdb, :turnnatdb;
  
    if (turncurdb is null) then turncurdb = 0;
    if (turnnatdb is null) then turnnatdb = 0;
  
    select sum(es.summacur), sum(es.summanat)
    from economicspec es
    where (es.remnanlkt_id = :id)
    into :turncurkt, :turnnatkt;
  
    if (turncurkt is null) then turncurkt = 0;
    if (turnnatkt is null) then turnnatkt = 0;
  
    execute procedure acc_calc_remnanl_end(:acc_id, :period_id,
      :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
      :remnbeginnatdb, :remnbeginnatkt,
      :remnbegincurdb, :remnbegincurkt,
      :TurnnatDb, :TurnnatKt,
      :TurnCurDb, :TurnCurKt)
    returning_values :remnendnatdb, :remnendnatkt, :remnendcurdb, :remnendcurkt;

    suspend;
  end
end
^

ALTER PROCEDURE ACC_REMNDBKT_LOAD (
    REMN_ID INTEGER)
RETURNS (
    ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    ACC_ID INTEGER,
    ACC CHAR(20),
    ACCFOLDER_ID INTEGER,
    CURCODE CHAR(5),
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(10),
    ANL2_CODE CHAR(10),
    ANL3_CODE CHAR(10),
    ANL4_CODE CHAR(10),
    ANL5_CODE CHAR(10),
    CONTRACTOR_ID INTEGER,
    CONTRACTORCODE CHAR(20),
    CONTRACTORFOLDERID INTEGER,
    CONTRACTORKIND SMALLINT,
    RISEDEBTS TIMESTAMP,
    CLEARDEBTS TIMESTAMP,
    COMMENT VARCHAR(256),
    DOCBASETYPE CHAR(15),
    DOCBASENUMBER CHAR(20),
    DOCBASEDATE TIMESTAMP,
    DOCTYPE CHAR(15),
    DOCNUMBER CHAR(20),
    DOCDATE TIMESTAMP,
    REMNBEGINNATDB NUMERIC(15,4),
    REMNBEGINNATKT NUMERIC(15,4),
    REMNBEGINCURDB NUMERIC(15,4),
    REMNBEGINCURKT NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    REMNENDNATDB NUMERIC(15,4),
    REMNENDNATKT NUMERIC(15,4),
    REMNENDCURDB NUMERIC(15,4),
    REMNENDCURKT NUMERIC(15,4))
AS
begin
  select r.id, r.period_id, p.pname, a.acc, c.code, r.acc_id, a.folder_id,
    r.anlplan1_id, r.anlplan2_id, r.anlplan3_id, r.anlplan4_id, r.anlplan5_id,
    a1.code, a2.code, a3.code, a4.code, a5.code,
    cn.id, cn.code contractorcode, cn.folder_id contractorfolderid, cn.kind contractorkind,
    r.risedebts, r.cleardebts, r.comment,
    r.docbasetype, r.docbasenumber, r.docbasedate, r.doctype, r.docnumber, r.docdate,
    r.remnbeginnatdb, r.remnbeginnatkt, r.remnbegincurdb, r.remnbegincurkt
  from remndbkt r left join period p on p.id = r.period_id
    left join accplan a on r.acc_id = a.id
    left join currency c on c.upcode = a.currency_code
    left join anlplan a1 on a1.id = r.anlplan1_id
    left join anlplan a2 on a2.id = r.anlplan2_id
    left join anlplan a3 on a3.id = r.anlplan3_id
    left join anlplan a4 on a4.id = r.anlplan4_id
    left join anlplan a5 on a5.id = r.anlplan5_id
    left join contractor cn on cn.id = r.contractor_id
  where (r.id = :remn_id)
  into :id, :period_id, :pname, :acc, :curcode, :acc_id, :accfolder_id,
    :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
    :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code,
    :contractor_id, :contractorcode, :contractorfolderid, :contractorkind,
    :risedebts, :cleardebts, :comment,
    :docbasetype, :docbasenumber, :docbasedate, :doctype, :docnumber, :docdate,
    :remnbeginnatdb, :remnbeginnatkt, :remnbegincurdb, :remnbegincurkt;

  select sum(es.summacur), sum(es.summanat)
  from economicspec es
  where (es.remndb_id = :id)
  into :turncurdb, :turnnatdb;

  if (turncurdb is null) then turncurdb = 0;
  if (turnnatdb is null) then turnnatdb = 0;

  select sum(es.summacur), sum(es.summanat)
  from economicspec es
  where (es.remnkt_id = :id)
  into :turncurkt, :turnnatkt;

  if (turncurkt is null) then turncurkt = 0;
  if (turnnatkt is null) then turnnatkt = 0;

  execute procedure acc_calc_remndbkt_end(
    :remnbeginnatdb, :remnbeginnatkt,
    :TurnnatDb, :TurnnatKt,
    :remnbegincurdb, :remnbegincurkt,
    :TurnCurDb, :TurnCurKt)
  returning_values :remnendnatdb, :remnendnatkt, :remnendcurdb, :remnendcurkt;

  suspend;
end
^

ALTER PROCEDURE ACC_REMNDBKT_SELECT (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER,
    ACCOUNT INTEGER)
RETURNS (
    ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    DATEFROM TIMESTAMP,
    DATETO TIMESTAMP,
    ACC_ID INTEGER,
    ACC CHAR(20),
    ACCFOLDER_ID INTEGER,
    CURCODE CHAR(5),
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(10),
    ANL2_CODE CHAR(10),
    ANL3_CODE CHAR(10),
    ANL4_CODE CHAR(10),
    ANL5_CODE CHAR(10),
    CONTRACTOR_ID INTEGER,
    CONTRACTORCODE CHAR(20),
    CONTRACTORUPCODE CHAR(20),
    CONTRACTORFOLDERID INTEGER,
    DOCBASETYPE CHAR(15),
    DOCBASENUMBER CHAR(20),
    DOCBASEDATE TIMESTAMP,
    DOCTYPE CHAR(15),
    DOCNUMBER CHAR(20),
    DOCDATE TIMESTAMP,
    REMNBEGINNATDB NUMERIC(15,4),
    REMNBEGINNATKT NUMERIC(15,4),
    REMNBEGINCURDB NUMERIC(15,4),
    REMNBEGINCURKT NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    REMNENDNATDB NUMERIC(15,4),
    REMNENDNATKT NUMERIC(15,4),
    REMNENDCURDB NUMERIC(15,4),
    REMNENDCURKT NUMERIC(15,4))
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
BEGIN
  if (period1 is not null) then
    select p.datefrom from period p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from period p where p.id = :period2 into :date2;

  for
    select r.id, r.period_id, p.datefrom, p.dateto, p.pname, a.acc, a.folder_id, c.code,
      r.acc_id, r.remnbeginnatdb, r.remnbeginnatkt, r.remnbegincurdb, r.remnbegincurkt,
      r.anlplan1_id, r.anlplan2_id, r.anlplan3_id, r.anlplan4_id, r.anlplan5_id,
      a1.code, a2.code, a3.code, a4.code, a5.code,
      r.contractor_id, cn.code, cn.upcode, cn.folder_id,
      r.docbasedate, r.docbasenumber, r.docbasetype, r.docdate, r.docnumber, r.doctype
    from remndbkt r left join period p on p.id = r.period_id
      left join contractor cn on cn.id = r.contractor_id
      left join accplan a on r.acc_id = a.id
      left join currency c on c.upcode = a.currency_code
      left join anlplan a1 on a1.id = r.anlplan1_id
      left join anlplan a2 on a2.id = r.anlplan2_id
      left join anlplan a3 on a3.id = r.anlplan3_id
      left join anlplan a4 on a4.id = r.anlplan4_id
      left join anlplan a5 on a5.id = r.anlplan5_id
    where
      ((p.datefrom >= :date1) or (:period1 is null) or (:period1 = 0)) and
      ((p.datefrom <= :date2) or (:period2 is null) or (:period2 = 0)) and
      ((r.acc_id = :account) or (:account is null) or (:account = 0))
    into :id, :period_id, :datefrom, :dateto, :pname, :acc, :accfolder_id, :curcode,
      :acc_id, :remnbeginnatdb, :remnbeginnatkt, :remnbegincurdb, :remnbegincurkt,
      :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
      :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code,
      :contractor_id, :contractorcode, :contractorupcode, :contractorfolderid,
      :docbasedate, :docbasenumber, :docbasetype, :docdate, :docnumber, :doctype
  do begin
    select sum(es.summacur), sum(es.summanat)
    from economicspec es
    where (es.remndb_id = :id)
    into :turncurdb, :turnnatdb;
  
    if (turncurdb is null) then turncurdb = 0;
    if (turnnatdb is null) then turnnatdb = 0;
  
    select sum(es.summacur), sum(es.summanat)
    from economicspec es
    where (es.remnkt_id = :id)
    into :turncurkt, :turnnatkt;
  
    if (turncurkt is null) then turncurkt = 0;
    if (turnnatkt is null) then turnnatkt = 0;
  
    execute procedure acc_calc_remndbkt_end(
      :remnbeginnatdb, :remnbeginnatkt,
      :turnnatdb, :turnnatkt,
      :remnbegincurdb, :remnbegincurkt,
      :turncurdb, :turncurkt)
    returning_values :remnendnatdb, :remnendnatkt, :remnendcurdb, :remnendcurkt;
  
    suspend;
  end
end
^

ALTER PROCEDURE ACC_REMNDBKT_SELECT_BY_CONTR (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER,
    CONTR_ID INTEGER)
RETURNS (
    ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    DATEFROM TIMESTAMP,
    DATETO TIMESTAMP,
    ACC_ID INTEGER,
    ACC CHAR(20),
    ACCFOLDER_ID INTEGER,
    CURCODE CHAR(5),
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(10),
    ANL2_CODE CHAR(10),
    ANL3_CODE CHAR(10),
    ANL4_CODE CHAR(10),
    ANL5_CODE CHAR(10),
    CONTRACTOR_ID INTEGER,
    CONTRACTORCODE CHAR(20),
    CONTRACTORUPCODE CHAR(20),
    CONTRACTORFOLDERID INTEGER,
    DOCBASETYPE CHAR(15),
    DOCBASENUMBER CHAR(20),
    DOCBASEDATE TIMESTAMP,
    DOCTYPE CHAR(15),
    DOCNUMBER CHAR(20),
    DOCDATE TIMESTAMP,
    REMNBEGINNATDB NUMERIC(15,4),
    REMNBEGINNATKT NUMERIC(15,4),
    REMNBEGINCURDB NUMERIC(15,4),
    REMNBEGINCURKT NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    REMNENDNATDB NUMERIC(15,4),
    REMNENDNATKT NUMERIC(15,4),
    REMNENDCURDB NUMERIC(15,4),
    REMNENDCURKT NUMERIC(15,4))
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
BEGIN
  if (period1 is not null) then
    select p.datefrom from period p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from period p where p.id = :period2 into :date2;

  for
    select r.id, r.period_id, p.datefrom, p.dateto, p.pname, a.acc, a.folder_id, c.code,
      r.acc_id, r.remnbeginnatdb, r.remnbeginnatkt, r.remnbegincurdb, r.remnbegincurkt,
      r.anlplan1_id, r.anlplan2_id, r.anlplan3_id, r.anlplan4_id, r.anlplan5_id,
      a1.code, a2.code, a3.code, a4.code, a5.code,
      r.contractor_id, cn.code, cn.upcode, cn.folder_id,
      r.docbasedate, r.docbasenumber, r.docbasetype, r.docdate, r.docnumber, r.doctype
    from remndbkt r left join period p on p.id = r.period_id
      left join contractor cn on cn.id = r.contractor_id
      left join accplan a on r.acc_id = a.id
      left join currency c on c.upcode = a.currency_code
      left join anlplan a1 on a1.id = r.anlplan1_id
      left join anlplan a2 on a2.id = r.anlplan2_id
      left join anlplan a3 on a3.id = r.anlplan3_id
      left join anlplan a4 on a4.id = r.anlplan4_id
      left join anlplan a5 on a5.id = r.anlplan5_id
    where
      ((p.datefrom >= :date1) or (:period1 is null) or (:period1 = 0)) and
      ((p.datefrom <= :date2) or (:period2 is null) or (:period2 = 0)) and
      (r.contractor_id = :contr_id)
    into :id, :period_id, :datefrom, :dateto, :pname, :acc, :accfolder_id, :curcode,
      :acc_id, :remnbeginnatdb, :remnbeginnatkt, :remnbegincurdb, :remnbegincurkt,
      :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
      :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code,
      :contractor_id, :contractorcode, :contractorupcode, :contractorfolderid,
      :docbasedate, :docbasenumber, :docbasetype, :docdate, :docnumber, :doctype
  do begin
    select sum(es.summacur), sum(es.summanat)
    from economicspec es
    where (es.remndb_id = :id)
    into :turncurdb, :turnnatdb;
  
    if (turncurdb is null) then turncurdb = 0;
    if (turnnatdb is null) then turnnatdb = 0;
  
    select sum(es.summacur), sum(es.summanat)
    from economicspec es
    where (es.remnkt_id = :id)
    into :turncurkt, :turnnatkt;
  
    if (turncurkt is null) then turncurkt = 0;
    if (turnnatkt is null) then turnnatkt = 0;
  
    execute procedure acc_calc_remndbkt_end(
      :remnbeginnatdb, :remnbeginnatkt,
      :turnnatdb, :turnnatkt,
      :remnbegincurdb, :remnbegincurkt,
      :turncurdb, :turncurkt)
    returning_values :remnendnatdb, :remnendnatkt, :remnendcurdb, :remnendcurkt;
  
    suspend;
  end
end
^

ALTER PROCEDURE ACC_REMNDBKT_SELECT_CONTR (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER,
    INCONTRACTOR_ID INTEGER)
RETURNS (
    ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    DATEFROM TIMESTAMP,
    DATETO TIMESTAMP,
    ACC_ID INTEGER,
    ACC CHAR(20),
    ACCFOLDER_ID INTEGER,
    CURCODE CHAR(5),
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(10),
    ANL2_CODE CHAR(10),
    ANL3_CODE CHAR(10),
    ANL4_CODE CHAR(10),
    ANL5_CODE CHAR(10),
    CONTRACTOR_ID INTEGER,
    CONTRACTORCODE CHAR(20),
    CONTRACTORUPCODE CHAR(20),
    CONTRACTORFOLDERID INTEGER,
    DOCBASETYPE CHAR(15),
    DOCBASENUMBER CHAR(20),
    DOCBASEDATE TIMESTAMP,
    DOCTYPE CHAR(15),
    DOCNUMBER CHAR(20),
    DOCDATE TIMESTAMP,
    REMNBEGINNATDB NUMERIC(15,4),
    REMNBEGINNATKT NUMERIC(15,4),
    REMNBEGINCURDB NUMERIC(15,4),
    REMNBEGINCURKT NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    REMNENDNATDB NUMERIC(15,4),
    REMNENDNATKT NUMERIC(15,4),
    REMNENDCURDB NUMERIC(15,4),
    REMNENDCURKT NUMERIC(15,4))
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
BEGIN
  if (period1 is not null) then
    select p.datefrom from period p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from period p where p.id = :period2 into :date2;

  for
    select r.id, r.period_id, p.datefrom, p.dateto, p.pname, a.acc, a.folder_id, c.code,
      r.acc_id, r.remnbeginnatdb, r.remnbeginnatkt, r.remnbegincurdb, r.remnbegincurkt,
      r.anlplan1_id, r.anlplan2_id, r.anlplan3_id, r.anlplan4_id, r.anlplan5_id,
      a1.code, a2.code, a3.code, a4.code, a5.code,
      r.contractor_id, cn.code, cn.upcode, cn.folder_id,
      r.docbasedate, r.docbasenumber, r.docbasetype, r.docdate, r.docnumber, r.doctype
    from remndbkt r left join period p on p.id = r.period_id
      left join contractor cn on cn.id = r.contractor_id
      left join accplan a on r.acc_id = a.id
      left join currency c on c.upcode = a.currency_code
      left join anlplan a1 on a1.id = r.anlplan1_id
      left join anlplan a2 on a2.id = r.anlplan2_id
      left join anlplan a3 on a3.id = r.anlplan3_id
      left join anlplan a4 on a4.id = r.anlplan4_id
      left join anlplan a5 on a5.id = r.anlplan5_id
    where
      ((p.datefrom >= :date1) or (:period1 is null) or (:period1 = 0)) and
      ((p.datefrom <= :date2) or (:period2 is null) or (:period2 = 0)) and
      (r.contractor_id = :incontractor_id)
    into :id, :period_id, :datefrom, :dateto, :pname, :acc, :accfolder_id, :curcode,
      :acc_id, :remnbeginnatdb, :remnbeginnatkt, :remnbegincurdb, :remnbegincurkt,
      :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
      :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code,
      :contractor_id, :contractorcode, :contractorupcode, :contractorfolderid,
      :docbasedate, :docbasenumber, :docbasetype, :docdate, :docnumber, :doctype
  do begin
    select sum(es.summacur), sum(es.summanat)
    from economicspec es
    where (es.remndb_id = :id)
    into :turncurdb, :turnnatdb;
  
    if (turncurdb is null) then turncurdb = 0;
    if (turnnatdb is null) then turnnatdb = 0;
  
    select sum(es.summacur), sum(es.summanat)
    from economicspec es
    where (es.remnkt_id = :id)
    into :turncurkt, :turnnatkt;
  
    if (turncurkt is null) then turncurkt = 0;
    if (turnnatkt is null) then turnnatkt = 0;
  
    execute procedure acc_calc_remndbkt_end(
      :remnbeginnatdb, :remnbeginnatkt,
      :turnnatdb, :turnnatkt,
      :remnbegincurdb, :remnbegincurkt,
      :turncurdb, :turncurkt)
    returning_values :remnendnatdb, :remnendnatkt, :remnendcurdb, :remnendcurkt;
  
    suspend;
  end
end
^

ALTER PROCEDURE ACC_REMNDBKT_SELECT_CONTR_ACC (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER,
    INCONTRACTOR_ID INTEGER,
    INACC_ID INTEGER)
RETURNS (
    ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    DATEFROM TIMESTAMP,
    DATETO TIMESTAMP,
    ACC_ID INTEGER,
    ACC CHAR(20),
    ACCFOLDER_ID INTEGER,
    CURCODE CHAR(5),
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(10),
    ANL2_CODE CHAR(10),
    ANL3_CODE CHAR(10),
    ANL4_CODE CHAR(10),
    ANL5_CODE CHAR(10),
    CONTRACTOR_ID INTEGER,
    CONTRACTORCODE CHAR(20),
    CONTRACTORUPCODE CHAR(20),
    CONTRACTORFOLDERID INTEGER,
    DOCBASETYPE CHAR(15),
    DOCBASENUMBER CHAR(20),
    DOCBASEDATE TIMESTAMP,
    DOCTYPE CHAR(15),
    DOCNUMBER CHAR(20),
    DOCDATE TIMESTAMP,
    REMNBEGINNATDB NUMERIC(15,4),
    REMNBEGINNATKT NUMERIC(15,4),
    REMNBEGINCURDB NUMERIC(15,4),
    REMNBEGINCURKT NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    REMNENDNATDB NUMERIC(15,4),
    REMNENDNATKT NUMERIC(15,4),
    REMNENDCURDB NUMERIC(15,4),
    REMNENDCURKT NUMERIC(15,4))
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
BEGIN
  if (period1 is not null) then
    select p.datefrom from period p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from period p where p.id = :period2 into :date2;

  for
    select r.id, r.period_id, p.datefrom, p.dateto, p.pname, a.acc, a.folder_id, c.code,
      r.acc_id, r.remnbeginnatdb, r.remnbeginnatkt, r.remnbegincurdb, r.remnbegincurkt,
      r.anlplan1_id, r.anlplan2_id, r.anlplan3_id, r.anlplan4_id, r.anlplan5_id,
      a1.code, a2.code, a3.code, a4.code, a5.code,
      r.contractor_id, cn.code, cn.upcode, cn.folder_id,
      r.docbasedate, r.docbasenumber, r.docbasetype, r.docdate, r.docnumber, r.doctype
    from remndbkt r left join period p on p.id = r.period_id
      left join contractor cn on cn.id = r.contractor_id
      left join accplan a on r.acc_id = a.id
      left join currency c on c.upcode = a.currency_code
      left join anlplan a1 on a1.id = r.anlplan1_id
      left join anlplan a2 on a2.id = r.anlplan2_id
      left join anlplan a3 on a3.id = r.anlplan3_id
      left join anlplan a4 on a4.id = r.anlplan4_id
      left join anlplan a5 on a5.id = r.anlplan5_id
    where
      ((p.datefrom >= :date1) or (:period1 is null) or (:period1 = 0)) and
      ((p.datefrom <= :date2) or (:period2 is null) or (:period2 = 0)) and
      (r.contractor_id = :incontractor_id) and
      (r.acc_id = :inacc_id)
    into :id, :period_id, :datefrom, :dateto, :pname, :acc, :accfolder_id, :curcode,
      :acc_id, :remnbeginnatdb, :remnbeginnatkt, :remnbegincurdb, :remnbegincurkt,
      :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
      :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code,
      :contractor_id, :contractorcode, :contractorupcode, :contractorfolderid,
      :docbasedate, :docbasenumber, :docbasetype, :docdate, :docnumber, :doctype
  do begin
    select sum(es.summacur), sum(es.summanat)
    from economicspec es
    where (es.remndb_id = :id)
    into :turncurdb, :turnnatdb;
  
    if (turncurdb is null) then turncurdb = 0;
    if (turnnatdb is null) then turnnatdb = 0;
  
    select sum(es.summacur), sum(es.summanat)
    from economicspec es
    where (es.remnkt_id = :id)
    into :turncurkt, :turnnatkt;
  
    if (turncurkt is null) then turncurkt = 0;
    if (turnnatkt is null) then turnnatkt = 0;
  
    execute procedure acc_calc_remndbkt_end(
      :remnbeginnatdb, :remnbeginnatkt,
      :turnnatdb, :turnnatkt,
      :remnbegincurdb, :remnbegincurkt,
      :turncurdb, :turncurkt)
    returning_values :remnendnatdb, :remnendnatkt, :remnendcurdb, :remnendcurkt;
  
    suspend;
  end
end
^

ALTER PROCEDURE ACC_REMNDBKT_SELECT_CONTR_BD (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER,
    INCONTRACTOR_ID INTEGER,
    BASEDOCTYPE CHAR(15),
    BASEDOCNUMBER CHAR(20),
    BASEDOCDATE TIMESTAMP)
RETURNS (
    ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    DATEFROM TIMESTAMP,
    DATETO TIMESTAMP,
    ACC_ID INTEGER,
    ACC CHAR(20),
    ACCFOLDER_ID INTEGER,
    CURCODE CHAR(5),
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(10),
    ANL2_CODE CHAR(10),
    ANL3_CODE CHAR(10),
    ANL4_CODE CHAR(10),
    ANL5_CODE CHAR(10),
    CONTRACTOR_ID INTEGER,
    CONTRACTORCODE CHAR(20),
    CONTRACTORUPCODE CHAR(20),
    CONTRACTORFOLDERID INTEGER,
    DOCBASETYPE CHAR(15),
    DOCBASENUMBER CHAR(20),
    DOCBASEDATE TIMESTAMP,
    DOCTYPE CHAR(15),
    DOCNUMBER CHAR(20),
    DOCDATE TIMESTAMP,
    REMNBEGINNATDB NUMERIC(15,4),
    REMNBEGINNATKT NUMERIC(15,4),
    REMNBEGINCURDB NUMERIC(15,4),
    REMNBEGINCURKT NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    REMNENDNATDB NUMERIC(15,4),
    REMNENDNATKT NUMERIC(15,4),
    REMNENDCURDB NUMERIC(15,4),
    REMNENDCURKT NUMERIC(15,4))
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
BEGIN
  if (period1 is not null) then
    select p.datefrom from period p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from period p where p.id = :period2 into :date2;

  for
    select r.id, r.period_id, p.datefrom, p.dateto, p.pname, a.acc, a.folder_id, c.code,
      r.acc_id, r.remnbeginnatdb, r.remnbeginnatkt, r.remnbegincurdb, r.remnbegincurkt,
      r.anlplan1_id, r.anlplan2_id, r.anlplan3_id, r.anlplan4_id, r.anlplan5_id,
      a1.code, a2.code, a3.code, a4.code, a5.code,
      r.contractor_id, cn.code, cn.upcode, cn.folder_id,
      r.docbasedate, r.docbasenumber, r.docbasetype, r.docdate, r.docnumber, r.doctype
    from remndbkt r left join period p on p.id = r.period_id
      left join contractor cn on cn.id = r.contractor_id
      left join accplan a on r.acc_id = a.id
      left join currency c on c.upcode = a.currency_code
      left join anlplan a1 on a1.id = r.anlplan1_id
      left join anlplan a2 on a2.id = r.anlplan2_id
      left join anlplan a3 on a3.id = r.anlplan3_id
      left join anlplan a4 on a4.id = r.anlplan4_id
      left join anlplan a5 on a5.id = r.anlplan5_id
    where
      ((p.datefrom >= :date1) or (:period1 is null) or (:period1 = 0)) and
      ((p.datefrom <= :date2) or (:period2 is null) or (:period2 = 0)) and
      (r.contractor_id = :incontractor_id) and
      ((r.docbasetype = :basedoctype) and (DelSpace(r.docbasenumber) = DelSpace(:basedocnumber)) and (r.docbasedate = :basedocdate))
    into :id, :period_id, :datefrom, :dateto, :pname, :acc, :accfolder_id, :curcode,
      :acc_id, :remnbeginnatdb, :remnbeginnatkt, :remnbegincurdb, :remnbegincurkt,
      :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
      :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code,
      :contractor_id, :contractorcode, :contractorupcode, :contractorfolderid,
      :docbasedate, :docbasenumber, :docbasetype, :docdate, :docnumber, :doctype
  do begin
    select sum(es.summacur), sum(es.summanat)
    from economicspec es
    where (es.remndb_id = :id)
    into :turncurdb, :turnnatdb;
  
    if (turncurdb is null) then turncurdb = 0;
    if (turnnatdb is null) then turnnatdb = 0;
  
    select sum(es.summacur), sum(es.summanat)
    from economicspec es
    where (es.remnkt_id = :id)
    into :turncurkt, :turnnatkt;
  
    if (turncurkt is null) then turncurkt = 0;
    if (turnnatkt is null) then turnnatkt = 0;
  
    execute procedure acc_calc_remndbkt_end(
      :remnbeginnatdb, :remnbeginnatkt,
      :turnnatdb, :turnnatkt,
      :remnbegincurdb, :remnbegincurkt,
      :turncurdb, :turncurkt)
    returning_values :remnendnatdb, :remnendnatkt, :remnendcurdb, :remnendcurkt;
  
    suspend;
  end
end
^

ALTER PROCEDURE ACC_REMNVAL_CALC_ROW (
    ID INTEGER,
    BEGINQUAN NUMERIC(15,4),
    REMNBEGINNAT NUMERIC(15,4),
    REMNBEGINCUR NUMERIC(15,4))
RETURNS (
    ENDQUAN NUMERIC(15,3),
    REMNENDNAT NUMERIC(15,4),
    REMNENDCUR NUMERIC(15,4),
    QUANTITYDB NUMERIC(15,3),
    QUANTITYKT NUMERIC(15,3),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4))
AS
begin
/* Вспомогательная для процедуры rpt_remnval_select_universal */
    select sum(es.summacur), sum(es.summanat), sum(es.quantity)
    from economicspec es
    where (es.remnvaldb_id = :id)
    into :turncurdb, :turnnatdb, :quantitydb;
  
    if (turncurdb is null) then turncurdb = 0;
    if (turnnatdb is null) then turnnatdb = 0;
    if (quantitydb is null) then quantitydb = 0;
  
    select sum(es.summacur), sum(es.summanat), sum(es.quantity)
    from economicspec es
    where (es.remnvalkt_id = :id)
    into :turncurkt, :turnnatkt, :quantitykt;
  
    if (turncurkt is null) then turncurkt = 0;
    if (turnnatkt is null) then turnnatkt = 0;
    if (quantitykt is null) then quantitykt = 0;
  
    execute procedure acc_calc_remnval_end(:beginquan, :remnbeginnat, :remnbegincur,
      :quantitydb, :quantitykt, :turnnatdb, :turnnatkt, :TurnCurDb, :TurnCurKt)
    returning_values :endquan, :remnendnat, :remnendcur;
  suspend;
end
^

ALTER PROCEDURE ACC_REMNVAL_LOAD (
    REMN_ID INTEGER)
RETURNS (
    ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    ACC_ID INTEGER,
    ACC CHAR(20),
    ACCFOLDER_ID INTEGER,
    CURCODE CHAR(5),
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(10),
    ANL2_CODE CHAR(10),
    ANL3_CODE CHAR(10),
    ANL4_CODE CHAR(10),
    ANL5_CODE CHAR(10),
    CONTRACTOR_ID INTEGER,
    CONTRACTORCODE CHAR(20),
    CONTRACTORFOLDERID INTEGER,
    CONTRACTORKIND SMALLINT,
    CATALOG_ID INTEGER,
    CATALOGFOLDERID INTEGER,
    CATALOGCODE CHAR(20),
    CATALOGNAME VARCHAR(80),
    CATALOGMEASURE1 CHAR(5),
    BATCH_ID INTEGER,
    REMNBEGINNAT NUMERIC(15,4),
    REMNBEGINCUR NUMERIC(15,4),
    BEGINQUAN NUMERIC(15,3),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    QUANTITYDB NUMERIC(15,3),
    QUANTITYKT NUMERIC(15,3),
    REMNENDNAT NUMERIC(15,4),
    REMNENDCUR NUMERIC(15,4),
    ENDQUAN NUMERIC(15,3))
AS
begin
  select r.id, r.period_id, p.pname, a.acc, cur.code, r.acc_id, a.folder_id,
    r.anlplan1_id, r.anlplan2_id, r.anlplan3_id, r.anlplan4_id, r.anlplan5_id,
    a1.code, a2.code, a3.code, a4.code, a5.code,
    cn.id, cn.code, cn.folder_id, cn.kind,
    r.catalog_id, c.folder_id, c.code, c.cname, m.code, r.batch_id,
    r.remnbeginnat, r.remnbegincur, r.beginquan
  from remnval r left join period p on p.id = r.period_id
    left join accplan a on r.acc_id = a.id
    left join currency cur on cur.upcode = a.currency_code
    left join anlplan a1 on a1.id = r.anlplan1_id
    left join anlplan a2 on a2.id = r.anlplan2_id
    left join anlplan a3 on a3.id = r.anlplan3_id
    left join anlplan a4 on a4.id = r.anlplan4_id
    left join anlplan a5 on a5.id = r.anlplan5_id
    left join contractor cn on cn.id = r.contractor_id
    left join catalog c on r.catalog_id = c.id
    left join measure m on m.id = c.measure1_id
  where (r.id = :remn_id)
  into :id, :period_id, :pname, :acc, :curcode, :acc_id, :accfolder_id,
    :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
    :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code,
    :contractor_id, :contractorcode, :contractorfolderid, :contractorkind,
    :catalog_id, :catalogfolderid, :catalogcode, :catalogname, :catalogmeasure1, :batch_id,
    :remnbeginnat, :remnbegincur, :beginquan;

  select sum(es.summacur), sum(es.summanat), sum(es.quantity)
  from economicspec es
  where (es.remnvaldb_id = :id)
  into :turncurdb, :turnnatdb, :quantitydb;

  if (turncurdb is null) then turncurdb = 0;
  if (turnnatdb is null) then turnnatdb = 0;
  if (quantitydb is null) then quantitydb = 0;

  select sum(es.summacur), sum(es.summanat), sum(es.quantity)
  from economicspec es
  where (es.remnvalkt_id = :id)
  into :turncurkt, :turnnatkt, :quantitykt;

  if (turncurkt is null) then turncurkt = 0;
  if (turnnatkt is null) then turnnatkt = 0;
  if (quantitykt is null) then quantitykt = 0;

  execute procedure acc_calc_remnval_end(
    :beginquan, :remnbeginnat, :remnbegincur,
    :quantitydb, :quantitykt, :TurnnatDb, :TurnnatKt,
    :TurnCurDb, :TurnCurKt)
  returning_values :endquan, :remnendnat, :remnendcur;

  suspend;
end
^

ALTER PROCEDURE ACC_REMNVAL_SELECT (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER,
    ACCOUNT INTEGER)
RETURNS (
    ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    DATEFROM TIMESTAMP,
    DATETO TIMESTAMP,
    ACC_ID INTEGER,
    ACC CHAR(20),
    ACCFOLDER_ID INTEGER,
    CURCODE CHAR(5),
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(10),
    ANL2_CODE CHAR(10),
    ANL3_CODE CHAR(10),
    ANL4_CODE CHAR(10),
    ANL5_CODE CHAR(10),
    CONTRACTOR_ID INTEGER,
    CONTRACTORCODE CHAR(20),
    CONTRACTORUPCODE CHAR(20),
    CONTRACTORFOLDERID INTEGER,
    CATALOG_ID INTEGER,
    CATALOGFOLDER_ID INTEGER,
    CATCODE CHAR(20),
    CATNAME VARCHAR(80),
    CATMEAS CHAR(5),
    REMNBEGINNAT NUMERIC(15,4),
    REMNBEGINCUR NUMERIC(15,4),
    BEGINQUAN NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    QUANTITYDB NUMERIC(15,3),
    QUANTITYKT NUMERIC(15,3),
    REMNENDNAT NUMERIC(15,4),
    REMNENDCUR NUMERIC(15,4),
    ENDQUAN NUMERIC(15,3))
AS
DECLARE VARIABLE DATE1 TIMESTAMP;
DECLARE VARIABLE DATE2 TIMESTAMP;
BEGIN
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: используется сервером приложения для вывода
   бух.оборотки по ТМЦ
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 26.12.2002 Кривопустов Заменен LEFT JOIN на JOIN для
      исключения вывода пустых строк.
**********************************************************/
  if (period1 is not null) then
    select p.datefrom from period p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from period p where p.id = :period2 into :date2;

  for
    select r.id, r.period_id, p.datefrom, p.dateto, p.pname, a.acc, a.folder_id, c.code,
      r.acc_id, r.remnbeginnat, r.remnbegincur, r.beginquan,
      r.anlplan1_id, r.anlplan2_id, r.anlplan3_id, r.anlplan4_id, r.anlplan5_id,
      a1.code, a2.code, a3.code, a4.code, a5.code,
      r.contractor_id, cn.code, cn.upcode, cn.folder_id,
      r.catalog_id, cat.code, cat.cname, m.code, cat.folder_id
    from period p join remnval r on p.id = r.period_id
      left join contractor cn on cn.id = r.contractor_id
      left join catalog cat on cat.id = r.catalog_id
      left join measure m on m.id = cat.measure1_id
      left join accplan a on r.acc_id = a.id
      left join currency c on c.upcode = a.currency_code
      left join anlplan a1 on a1.id = r.anlplan1_id
      left join anlplan a2 on a2.id = r.anlplan2_id
      left join anlplan a3 on a3.id = r.anlplan3_id
      left join anlplan a4 on a4.id = r.anlplan4_id
      left join anlplan a5 on a5.id = r.anlplan5_id
    where
      ((p.datefrom >= :date1) or (:period1 is null) or (:period1 = 0)) and
      ((p.datefrom <= :date2) or (:period2 is null) or (:period2 = 0)) and
      ((r.acc_id = :account) or (:account is null) or (:account = 0))
    into :id, :period_id, :datefrom, :dateto, :pname, :acc, :accfolder_id, :curcode,
      :acc_id, :remnbeginnat, :remnbegincur, :beginquan,
      :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
      :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code,
      :contractor_id, :contractorcode, :contractorupcode, :contractorfolderid,
      :catalog_id, :catcode, :catname, :catmeas, :catalogfolder_id
  do begin
    select sum(es.summacur), sum(es.summanat), sum(es.quantity)
    from economicspec es
    where (es.remnvaldb_id = :id)
    into :turncurdb, :turnnatdb, :quantitydb;
  
    if (turncurdb is null) then turncurdb = 0;
    if (turnnatdb is null) then turnnatdb = 0;
    if (quantitydb is null) then quantitydb = 0;
  
    select sum(es.summacur), sum(es.summanat), sum(es.quantity)
    from economicspec es
    where (es.remnvalkt_id = :id)
    into :turncurkt, :turnnatkt, :quantitykt;
  
    if (turncurkt is null) then turncurkt = 0;
    if (turnnatkt is null) then turnnatkt = 0;
    if (quantitykt is null) then quantitykt = 0;
  
    execute procedure acc_calc_remnval_end(:beginquan, :remnbeginnat, :remnbegincur,
      :quantitydb, :quantitykt, :turnnatdb, :turnnatkt, :TurnCurDb, :TurnCurKt)
    returning_values :endquan, :remnendnat, :remnendcur;
  
    suspend;
  end
end
^

ALTER PROCEDURE ACC_REMNVAL_SELECT_BY_CONTR (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER,
    CONTR_ID INTEGER)
RETURNS (
    ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    DATEFROM TIMESTAMP,
    DATETO TIMESTAMP,
    ACC_ID INTEGER,
    ACC CHAR(20),
    ACCFOLDER_ID INTEGER,
    CURCODE CHAR(5),
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(10),
    ANL2_CODE CHAR(10),
    ANL3_CODE CHAR(10),
    ANL4_CODE CHAR(10),
    ANL5_CODE CHAR(10),
    CONTRACTOR_ID INTEGER,
    CONTRACTORCODE CHAR(20),
    CONTRACTORUPCODE CHAR(20),
    CONTRACTORFOLDERID INTEGER,
    CATALOG_ID INTEGER,
    CATALOGFOLDER_ID INTEGER,
    CATCODE CHAR(20),
    CATNAME VARCHAR(80),
    CATMEAS CHAR(5),
    REMNBEGINNAT NUMERIC(15,4),
    REMNBEGINCUR NUMERIC(15,4),
    BEGINQUAN NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    QUANTITYDB NUMERIC(15,3),
    QUANTITYKT NUMERIC(15,3),
    REMNENDNAT NUMERIC(15,4),
    REMNENDCUR NUMERIC(15,4),
    ENDQUAN NUMERIC(15,3))
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
BEGIN
  if (period1 is not null) then
    select p.datefrom from period p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from period p where p.id = :period2 into :date2;

  for
    select r.id, r.period_id, p.datefrom, p.dateto, p.pname, a.acc, a.folder_id, c.code,
      r.acc_id, r.remnbeginnat, r.remnbegincur, r.beginquan,
      r.anlplan1_id, r.anlplan2_id, r.anlplan3_id, r.anlplan4_id, r.anlplan5_id,
      a1.code, a2.code, a3.code, a4.code, a5.code,
      r.contractor_id, cn.code, cn.upcode, cn.folder_id,
      r.catalog_id, cat.code, cat.cname, m.code, cat.folder_id
    from remnval r left join period p on p.id = r.period_id
      left join contractor cn on cn.id = r.contractor_id
      left join catalog cat on cat.id = r.catalog_id
      left join measure m on m.id = cat.measure1_id
      left join accplan a on r.acc_id = a.id
      left join currency c on c.upcode = a.currency_code
      left join anlplan a1 on a1.id = r.anlplan1_id
      left join anlplan a2 on a2.id = r.anlplan2_id
      left join anlplan a3 on a3.id = r.anlplan3_id
      left join anlplan a4 on a4.id = r.anlplan4_id
      left join anlplan a5 on a5.id = r.anlplan5_id
    where
      ((p.datefrom >= :date1) or (:period1 is null) or (:period1 = 0)) and
      ((p.datefrom <= :date2) or (:period2 is null) or (:period2 = 0)) and
      (r.contractor_id = :contr_id)
    into :id, :period_id, :datefrom, :dateto, :pname, :acc, :accfolder_id, :curcode,
      :acc_id, :remnbeginnat, :remnbegincur, :beginquan,
      :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
      :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code,
      :contractor_id, :contractorcode, :contractorupcode, :contractorfolderid,
      :catalog_id, :catcode, :catname, :catmeas, :catalogfolder_id
  do begin
    select sum(es.summacur), sum(es.summanat), sum(es.quantity)
    from economicspec es
    where (es.remnvaldb_id = :id)
    into :turncurdb, :turnnatdb, :quantitydb;
  
    if (turncurdb is null) then turncurdb = 0;
    if (turnnatdb is null) then turnnatdb = 0;
    if (quantitydb is null) then quantitydb = 0;
  
    select sum(es.summacur), sum(es.summanat), sum(es.quantity)
    from economicspec es
    where (es.remnvalkt_id = :id)
    into :turncurkt, :turnnatkt, :quantitykt;
  
    if (turncurkt is null) then turncurkt = 0;
    if (turnnatkt is null) then turnnatkt = 0;
    if (quantitykt is null) then quantitykt = 0;
  
    execute procedure acc_calc_remnval_end(:beginquan, :remnbeginnat, :remnbegincur,
      :quantitydb, :quantitykt, :turnnatdb, :turnnatkt, :TurnCurDb, :TurnCurKt)
    returning_values :endquan, :remnendnat, :remnendcur;
  
    suspend;
  end
end
^

ALTER PROCEDURE ACC_REMNVAL_SELECT_BY_CONTR_CAT (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER,
    CONTR INTEGER,
    CATT INTEGER)
RETURNS (
    ID INTEGER,
    BATCH_ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    DATEFROM TIMESTAMP,
    DATETO TIMESTAMP,
    ACC_ID INTEGER,
    ACC CHAR(20),
    ACCFOLDER_ID INTEGER,
    CURCODE CHAR(5),
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(10),
    ANL2_CODE CHAR(10),
    ANL3_CODE CHAR(10),
    ANL4_CODE CHAR(10),
    ANL5_CODE CHAR(10),
    CONTRACTOR_ID INTEGER,
    CONTRACTORCODE CHAR(20),
    CONTRACTORUPCODE CHAR(20),
    CONTRACTORFOLDERID INTEGER,
    CATALOG_ID INTEGER,
    CATALOGFOLDER_ID INTEGER,
    CATCODE CHAR(20),
    CATNAME VARCHAR(80),
    CATMEAS CHAR(5),
    REMNBEGINNAT NUMERIC(15,4),
    REMNBEGINCUR NUMERIC(15,4),
    BEGINQUAN NUMERIC(15,4),
    TURNNATDB NUMERIC(15,4),
    TURNNATKT NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4),
    QUANTITYDB NUMERIC(15,3),
    QUANTITYKT NUMERIC(15,3),
    REMNENDNAT NUMERIC(15,4),
    REMNENDCUR NUMERIC(15,4),
    ENDQUAN NUMERIC(15,3))
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
BEGIN
  if (period1 is not null) then
    select p.datefrom from period p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from period p where p.id = :period2 into :date2;

  for
    select r.id, r.batch_id, r.period_id, p.datefrom, p.dateto, p.pname, a.acc, a.folder_id, c.code,
      r.acc_id, r.remnbeginnat, r.remnbegincur, r.beginquan,
      r.anlplan1_id, r.anlplan2_id, r.anlplan3_id, r.anlplan4_id, r.anlplan5_id,
      a1.code, a2.code, a3.code, a4.code, a5.code,
      r.contractor_id, cn.code, cn.upcode, cn.folder_id,
      r.catalog_id, cat.code, cat.cname, m.code, cat.folder_id
    from remnval r left join period p on p.id = r.period_id
      left join contractor cn on cn.id = r.contractor_id
      left join catalog cat on cat.id = r.catalog_id
      left join measure m on m.id = cat.measure1_id
      left join accplan a on r.acc_id = a.id
      left join currency c on c.upcode = a.currency_code
      left join anlplan a1 on a1.id = r.anlplan1_id
      left join anlplan a2 on a2.id = r.anlplan2_id
      left join anlplan a3 on a3.id = r.anlplan3_id
      left join anlplan a4 on a4.id = r.anlplan4_id
      left join anlplan a5 on a5.id = r.anlplan5_id
    where
      ((p.datefrom >= :date1) or (:period1 is null) or (:period1 = 0)) and
      ((p.datefrom <= :date2) or (:period2 is null) or (:period2 = 0)) and
      (cn.id = :contr) and (cat.id = :catt)
    into :id, :batch_id, :period_id, :datefrom, :dateto, :pname, :acc, :accfolder_id, :curcode,
      :acc_id, :remnbeginnat, :remnbegincur, :beginquan,
      :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
      :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code,
      :contractor_id, :contractorcode, :contractorupcode, :contractorfolderid,
      :catalog_id, :catcode, :catname, :catmeas, :catalogfolder_id
  do begin
    select sum(es.summacur), sum(es.summanat), sum(es.quantity)
    from economicspec es
    where (es.remnvaldb_id = :id)
    into :turncurdb, :turnnatdb, :quantitydb;
  
    if (turncurdb is null) then turncurdb = 0;
    if (turnnatdb is null) then turnnatdb = 0;
    if (quantitydb is null) then quantitydb = 0;
  
    select sum(es.summacur), sum(es.summanat), sum(es.quantity)
    from economicspec es
    where (es.remnvalkt_id = :id)
    into :turncurkt, :turnnatkt, :quantitykt;
  
    if (turncurkt is null) then turncurkt = 0;
    if (turnnatkt is null) then turnnatkt = 0;
    if (quantitykt is null) then quantitykt = 0;
  
    execute procedure acc_calc_remnval_end(:beginquan, :remnbeginnat, :remnbegincur,
      :quantitydb, :quantitykt, :turnnatdb, :turnnatkt, :TurnCurDb, :TurnCurKt)
    returning_values :endquan, :remnendnat, :remnendcur;
  
    suspend;
  end
end
^

ALTER PROCEDURE ACC_ROLLBACK_INVHISTORY (
    HISTORY_ID INTEGER,
    USER_ID INTEGER)
AS
DECLARE VARIABLE INVID INTEGER;
DECLARE VARIABLE AMSID INTEGER;
DECLARE VARIABLE OPERID INTEGER;
DECLARE VARIABLE SPECID INTEGER;
DECLARE VARIABLE KIND SMALLINT;
DECLARE VARIABLE DBALCOST numeric(15,4);
DECLARE VARIABLE DDEPRVAL numeric(15,4);
declare variable dbeginloss numeric(15,4);
declare variable dinitialloss numeric(15,4);
declare variable dendcost numeric(15,4);
DECLARE VARIABLE REVALFACT DOUBLE PRECISION;
DECLARE VARIABLE REVALSUM numeric(15,4);
DECLARE VARIABLE PREVAMORTDATE TIMESTAMP;
DECLARE VARIABLE OLDCONTR INTEGER;
DECLARE VARIABLE OLDACC INTEGER;
DECLARE VARIABLE OLDANL1 INTEGER;
DECLARE VARIABLE OLDANL2 INTEGER;
DECLARE VARIABLE OLDANL3 INTEGER;
DECLARE VARIABLE OLDANL4 INTEGER;
DECLARE VARIABLE OLDANL5 INTEGER;
DECLARE VARIABLE INVHEADID INTEGER;
declare variable old_il_id integer;
declare variable old_inoperdocnum char(20);
declare variable old_inoperdate timestamp;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: откат истории инв.объекта
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 11.02.2002 Кривопустов 
 28.05.2002 Кривопустов [+] Код местонахождения
 03.10.2002 Кривопустов [+] Акт ввода в эксплуатацию
**********************************************************/
  select h.INVENTORY_ID, h.AMORTIZATION_ID, h.EO_ID, h.ES_ID, h.KIND,
   h.DELTA_BALCOST, h.DELTA_DEPRVAL, h.delta_beginloss, h.delta_initialloss,
   h.REVAL_FACTOR, h.REVAL_SUM, h.old_invlocation_id, h.old_inoperdocnum, h.old_inoperdate
  from invhistory h
  where h.id = :history_id
  into :INVID, :AMSID, :OPERID, :SPECID, :KIND,
    :DBALCOST, :DDEPRVAL, :dbeginloss, :dinitialloss,
    :REVALFACT, :REVALSUM, :old_il_id, :old_inoperdocnum, :old_inoperdate;

  if (exists(select h.id from invhistory h
             where (h.inventory_id = :invid) and
                   (h.id > :history_id))) then
    exception E_INVHISTORY_NOT_LAST;

  delete from invhistory h where h.id = :history_id;

  if ((Kind = 0) or (Kind = 5)) then begin
    /* delete specification */
    update ECONOMICOPER set
      INSERTSIGN = 2,
      user_id = :user_id
      where ID = :OperId;
    delete from ECONOMICSPEC where ID = :SpecId;
    update ECONOMICOPER set
      INSERTSIGN = 0,
      user_id = :user_id
      where ID = :OperId;
    if (not exists(select s.id from economicspec s 
                  where s.ECONOMICOPER_ID = :OperId)) then
      delete from ECONOMICOPER where id = :OperId;
    if (Kind = 0) then begin
      /* rollback depreciation */
      delete from ACC_AMORTIZATION where id = :AmsId;
      select h.act_date from invhistory h
        where h.id = (select max(h1.id) from invhistory h1
                      where (h1.inventory_id = :InvId) and
                            (h1.kind = 0))
        into :PrevAmortDate;
      update inventory set
        amort = amort + :DDeprVal,
        ENDCOST = ENDCOST - :DDeprVal,
        AMORTDATE = :PrevAmortDate
      where id = :InvId;
    end
    else begin
      /* rollback overestimation */
      update inventory set
        ENDCOST = ENDCOST - :DBalCost,
        BALANCECOST = BALANCECOST - :DBalCost
      where id = :InvId;
    end
  end
  else if (Kind = 1) then begin
    /* delete entire operation */
    delete from ECONOMICOPER where id = :OperId;
    dendcost = DBalCost - dbeginloss - dinitialloss - ddeprval;
    update inventory set
        BALANCECOST = BALANCECOST - :DBalCost,
        beginloss = beginloss - :dbeginloss,
        initialloss = initialloss - :dinitialloss,
        amort = amort - :ddeprval,
        ENDCOST = ENDCOST - :dendcost
      where id = :InvId;
  end
  else if ((Kind = 2) or (Kind = 3)) then begin
      select i.invhead_id
        from inventory i where i.id = :InvId
        into :InvHeadId;
      if (Kind = 2) then begin
        /* rollback moving */
        select o.from_id
          from economicoper o where id = :OperId
          into :OldContr;
        select s.acckt_id, anlkt1_id, anlkt2_id, anlkt3_id, anlkt4_id, anlkt5_id
          from economicspec s where id = :SpecId
          into :OldAcc, :OldAnl1, :OldAnl2, :OldAnl3, :OldAnl4, :OldAnl5;
        update inventory set
          acc_id = :OldAcc,
          anl1_id = :OldAnl1,
          anl2_id = :OldAnl2,
          anl3_id = :OldAnl3,
          anl4_id = :OldAnl4,
          anl5_id = :OldAnl5
          where id = :InvId;
        update acc_invhead set
          contractor_id = :OldContr
          where id = :InvHeadId;
        if (old_il_id is not null) then
          update acc_invhead set
            invlocation_id = :old_il_id
            where id = :InvHeadId;
        if (old_inoperdocnum is not null) then
          update acc_invhead set
            inoperdocnum = :old_inoperdocnum,
            inoperdate = :old_inoperdate
            where id = :InvHeadId;
      end
      else begin
        /* rollback retiring */
        update acc_invhead set
          outoperdocnum = null,
          outoperdate = null
          where id = :InvHeadId;
      end
      /* delete entire operation */ 
      delete from ECONOMICOPER where id = :OperId;             
  end
  else if (Kind = 4) then begin
        /* rollback freezing */
        delete from ACC_AMORTIZATION where id = :AmsId;
  end
end
^

ALTER PROCEDURE ACC_UPDATE_ALL_REMN (
    ECONOMICOPER_ID INTEGER,
    OPERDATE TIMESTAMP,
    ISDELETE SMALLINT,
    FROM_ID INTEGER,
    TO_ID INTEGER,
    DOCBASETYPE VARCHAR(15),
    DOCBASENUMBER VARCHAR(20),
    DOCBASEDATE TIMESTAMP,
    DOCTYPE VARCHAR(15),
    DOCNUMBER VARCHAR(20),
    DOCDATE TIMESTAMP,
    CONTRACTTYPE VARCHAR(15),
    CONTRACTNUMBER VARCHAR(20),
    CONTRACTDATE TIMESTAMP,
    SPECMARK CHAR(20))
AS
DECLARE VARIABLE PERIOD_ID INTEGER;
DECLARE VARIABLE ESPEC_ID INTEGER;
DECLARE VARIABLE NEWESPEC_ID INTEGER;
DECLARE VARIABLE ACCDB INTEGER;
DECLARE VARIABLE ACCKT INTEGER;
DECLARE VARIABLE ANLDB1 INTEGER;
DECLARE VARIABLE ANLDB2 INTEGER;
DECLARE VARIABLE ANLDB3 INTEGER;
DECLARE VARIABLE ANLDB4 INTEGER;
DECLARE VARIABLE ANLDB5 INTEGER;
DECLARE VARIABLE ANLKT1 INTEGER;
DECLARE VARIABLE ANLKT2 INTEGER;
DECLARE VARIABLE ANLKT3 INTEGER;
DECLARE VARIABLE ANLKT4 INTEGER;
DECLARE VARIABLE ANLKT5 INTEGER;
DECLARE VARIABLE SUMMANAT NUMERIC(15,4);
DECLARE VARIABLE SUMMACUR NUMERIC(15,4);
DECLARE VARIABLE REMNSUMMANAT NUMERIC(15,4);
DECLARE VARIABLE REMNSUMMACUR NUMERIC(15,4);
DECLARE VARIABLE QUANTITY NUMERIC(15,3);
DECLARE VARIABLE REMNQUAN NUMERIC(15,3);
DECLARE VARIABLE TMPSUMNAT NUMERIC(15,4);
DECLARE VARIABLE TMPSUMCUR NUMERIC(15,4);
DECLARE VARIABLE TMPQUAN NUMERIC(15,3);
DECLARE VARIABLE REMNACCDB_ID INTEGER;
DECLARE VARIABLE REMNACCKT_ID INTEGER;
DECLARE VARIABLE REMNANLDB_ID INTEGER;
DECLARE VARIABLE REMNANLKT_ID INTEGER;
DECLARE VARIABLE REMNVALDB_ID INTEGER;
DECLARE VARIABLE REMNVALKT_ID INTEGER;
DECLARE VARIABLE REMNDB_ID INTEGER;
DECLARE VARIABLE REMNKT_ID INTEGER;
DECLARE VARIABLE CATALOG_ID INTEGER;
DECLARE VARIABLE ACCTYPE SMALLINT;
DECLARE VARIABLE ANLFORM SMALLINT;
DECLARE VARIABLE ACCBATCH_ID INTEGER;
DECLARE VARIABLE ACCBATCHHISTORY_ID INTEGER;
DECLARE VARIABLE ACCBATCHDB_ID INTEGER;
DECLARE VARIABLE ACCBATCHKT_ID INTEGER;
DECLARE VARIABLE ACCBATCHHISDB_ID INTEGER;
DECLARE VARIABLE ACCBATCHHISKT_ID INTEGER;
DECLARE VARIABLE NEW_SPEC_SIGN SMALLINT;
DECLARE VARIABLE DBTYPE VARCHAR(15);
DECLARE VARIABLE DBNUMBER VARCHAR(20);
DECLARE VARIABLE DBDATE TIMESTAMP;
DECLARE VARIABLE CTYPE VARCHAR(15);
DECLARE VARIABLE CNUMBER VARCHAR(20);
DECLARE VARIABLE CDATE TIMESTAMP;
DECLARE VARIABLE RACCDB_ID INTEGER;
DECLARE VARIABLE RACCKT_ID INTEGER;
DECLARE VARIABLE RANLDB_ID INTEGER;
DECLARE VARIABLE RANLKT_ID INTEGER;
DECLARE VARIABLE RVALDB_ID INTEGER;
DECLARE VARIABLE RVALKT_ID INTEGER;
DECLARE VARIABLE RDB_ID INTEGER;
DECLARE VARIABLE RKT_ID INTEGER;
begin
/**********************************************************
 Автор: Олег Сафонов, Константин Кривопустов
 Создано: давно
 Назначение: 
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 18.11.2003 Кривопустов Bug#2092
**********************************************************/
  /* find period */
  select p.id from period p
  where ( :operdate between p.datefrom and p.dateto )
  into :period_id;
  if ( period_id is null ) then exception E_Invalid_Period;
  /* get economicspec */
  for 
    select e.id, e.accdb_id, e.acckt_id, e.summanat, e.summacur, e.quantity,
      anldb1_id, anldb2_id, anldb3_id, anldb4_id, anldb5_id,
      anlkt1_id, anlkt2_id, anlkt3_id, anlkt4_id, anlkt5_id,
      e.catalog_id, e.accbatchdb_id, e.accbatchkt_id,
      e.accbatchhistorydb_id, e.accbatchhistorykt_id,
      e.remnaccdb_id, e.remnacckt_id,
      e.remnanldb_id, e.remnanlkt_id,
      e.remndb_id, e.remnkt_id,
      e.remnvaldb_id, e.remnvalkt_id
    from economicspec e
    where (e.economicoper_id = :economicoper_id)
    into :espec_id,:accdb,:acckt,:summanat,:summacur,:quantity,
      :anldb1,:anldb2,:anldb3,:anldb4,:anldb5,
      :anlkt1,:anlkt2,:anlkt3,:anlkt4,:anlkt5,
      :catalog_id, :accbatchdb_id, :accbatchkt_id, :accbatchhisdb_id, :accbatchhiskt_id,
      :raccdb_id, :racckt_id,
      :ranldb_id, :ranlkt_id,
      :rdb_id, :rkt_id,
      :rvaldb_id, :rvalkt_id
  do begin
    new_spec_sign = 1;
    /*Bug#2092*/
    remnquan = quantity;
    remnsummanat = summanat;
    remnsummacur = summacur;
    /*Bug#2092*/
    while (new_spec_sign = 1) do begin /* may be create economicspec */
      new_spec_sign = 0;
      
      if (isdelete <> 1) then begin
        if (acckt is not null) then begin
          select a.acctype,a.anlform from accplan a
          where (a.id = :acckt)
          into :acctype,:anlform;
          /* check possibility out good */
          if ((catalog_id is not null) and (anlform between 3 and 7) and (anlform <> 5) and
            (quantity<>0) and ((summanat<>0)or(summacur<>0))) then begin
            /*Bug#2092 remnquan = quantity;
            remnsummanat = summanat;
            remnsummacur = summacur; Bug#2092*/
            execute procedure F_GET_ACCBATCH_ACCBATCHHIST(:operdate,:acckt,:anlkt1,:anlkt2,:anlkt3,:anlkt4,:anlkt5,:accbatchkt_id,
              :catalog_id,:from_id,:quantity)
            returning_values :accbatchkt_id, :accbatchhiskt_id, :quantity, :summanat, :summacur;
            if ((quantity is null) or (quantity = 0)) then begin
              delete from economicspec where id = :newespec_id;
              new_spec_sign = 0;
            end
            else begin
              if (quantity <> remnquan) then begin
                new_spec_sign = 1;
                remnquan = remnquan - quantity;
                remnsummanat = remnsummanat - summanat;
                remnsummacur = remnsummacur - summacur;
              end
            end
          end
        end
        update economicspec set
          remnaccdb_id = null,
          remnacckt_id = null,
          remnanldb_id = null,
          remnanlkt_id = null,
          remnvaldb_id = null,
          remnvalkt_id = null,
          remndb_id = null,
          remnkt_id = null
        where (id = :espec_id);
      end /*if*/

      /* debet */
      if (accdb is not null) then begin
        select a.acctype,a.anlform from accplan a
        where (a.id = :accdb)
        into :acctype,:anlform;
        /* remndbkt */
        if ((anlform between 11 and 15)) then begin
          if (isdelete <> 1) then begin
            ctype = contracttype;
            cnumber = contractnumber;
            cdate = contractdate;
            dbtype = docbasetype;
            dbnumber = docbasenumber;
            dbdate = docbasedate;
            if ((anlform = 14)or(anlform = 15)or(anlform = 11)) then begin
              ctype = '';
              cnumber = '';
              cdate = '30.12.1899';
            end
            if ((anlform = 14)or(anlform = 15)or(anlform = 12)) then begin
              dbtype = '';
              dbnumber = '';
              dbdate = '30.12.1899';
            end
            execute procedure acc_include_turn_in_remndbkt(:accdb,:anlform,:anldb1,:anldb2,:anldb3,:anldb4,:anldb5,:period_id,
              :summanat,0,:summacur,0,:to_id,:dbtype,:dbnumber,:dbdate,
              :ctype,:cnumber,:cdate,:specmark)
            returning_values remndb_id;
            update economicspec
            set
              remndb_id = :remndb_id
            where (id = :espec_id);
          end
        end
        /* remnval */
        if ((catalog_id is not null) and (acctype = 0) and ((anlform = 1)or(anlform between 3 and 9))) then begin
          if (isdelete <> 1) then begin
            /* if batch calculation */
            if ((anlform = 4) and ((quantity <> 0)and((summanat <> 0)or(summacur <> 0)))) then begin
              execute procedure f_create_accbatch(
                :operdate, :accdb,:anldb1,:anldb2,:anldb3,:anldb4,:anldb5,
                :catalog_id, 0, 0, :doctype,:docnumber,:docdate, :quantity, :summanat, :summacur,
                :to_id,:accbatchdb_id)
              returning_values accbatch_id, accbatchhistory_id;
            end
            else begin 
              accbatch_id = null;
              accbatchhistory_id = null;
            end
            execute procedure acc_include_turn_in_remnval(
              :accdb,:anldb1,:anldb2,:anldb3,:anldb4,:anldb5,:period_id,:catalog_id,
              :summanat,0,:summacur,0,:quantity,0,:to_id,
              :specmark, :accbatch_id)
            returning_values remnvaldb_id;
            /* 3, 6, 7 anl form */
            if (((anlform = 6) or (anlform = 7)) and
               ((quantity <> 0)and((summanat <> 0)or(summacur <> 0)))) then begin
              update economicspec
              set
                accbatchdb_id = null,
                accbatchhistorydb_id = null
              where (id = :espec_id);
              execute procedure f_create_accbatch(
                :operdate, :accdb,:anldb1,:anldb2,:anldb3,:anldb4,:anldb5,
                :catalog_id, 0, 0, :doctype,:docnumber,:docdate, :quantity, :summanat, :summacur,
                :to_id,:accbatchdb_id)
              returning_values accbatch_id, accbatchhistory_id;
              accbatchhistory_id = null;
            end
            /* revalue */
            if (((anlform = 4)or(anlform = 6)or(anlform = 7))and
               ((quantity = 0)or((summanat = 0)and(summacur = 0)))) then begin
              execute procedure f_revalue_remnval(
                :operdate, :accbatchdb_id, :accdb,:anldb1,:anldb2,:anldb3,:anldb4,:anldb5,
                :catalog_id, 0, 0, :doctype,:docnumber,:docdate, :quantity, :summanat, :summacur,
                :to_id, :accbatchhisdb_id)
              returning_values accbatchhistory_id;   
            end
            update economicspec
            set
              remnvaldb_id = :remnvaldb_id,
              accbatchdb_id = :accbatch_id,
              accbatchhistorydb_id = :accbatchhistory_id
            where (id = :espec_id);
          end
        end
        else begin
          if (IsDelete <> 1) then
            if (accbatchdb_id is not null) then begin
              update economicspec
              set
                accbatchdb_id = null
              where (id = :espec_id);
              delete from accbatch where id = :accbatch_id;
            end
        end
        /* remnacc */
        if (isdelete <> 1) then begin
          execute procedure acc_include_turn_in_remnacc(:accdb,:period_id)
          returning_values :remnaccdb_id;
          update economicspec
          set
            remnaccdb_id = :remnaccdb_id
          where (id = :espec_id);
        end
        /* remnanl */
        if ((anldb1 is not null)or(anldb2 is not null)or(anldb3 is not null)or(anldb4 is not null)or(anldb5 is not null)) then begin
          if (isdelete <> 1) then begin
            execute procedure acc_include_turn_in_remnanl(
              :accdb,:anldb1,:anldb2,:anldb3,:anldb4,:anldb5,:period_id)
            returning_values remnanldb_id;
            update economicspec
            set
              remnanldb_id = :remnanldb_id
            where (id = :espec_id);
          end
        end
      end
      /* kredit */
      if (acckt is not null) then begin
        select a.acctype,a.anlform from accplan a
        where (a.id = :acckt)
        into :acctype,:anlform;
        /* remndbkt */
        if ((anlform between 11 and 15)) then begin
          if (isdelete <> 1) then begin
            ctype = contracttype;
            cnumber = contractnumber;
            cdate = contractdate;
            dbtype = docbasetype;
            dbnumber = docbasenumber;
            dbdate = docbasedate;
            if ((anlform = 14)or(anlform = 15)or(anlform = 11)) then begin
              ctype = '';
              cnumber = '';
              cdate = '30.12.1899';
            end
            if ((anlform = 14)or(anlform = 15)or(anlform = 12)) then begin
              dbtype = '';
              dbnumber = '';
              dbdate = '30.12.1899';
            end
            execute procedure acc_include_turn_in_remndbkt(:acckt,:anlform,:anlkt1,:anlkt2,:anlkt3,:anlkt4,:anlkt5,:period_id,
              0,:summanat,0,:summacur,:from_id,:dbtype,:dbnumber,:dbdate,
              :ctype,:cnumber,:cdate,:specmark)
            returning_values remnkt_id;
            update economicspec
            set
              remnkt_id = :remnkt_id
            where (id = :espec_id);
          end
        end
        /* remnval */
        if ((catalog_id is not null) and (acctype = 0) and ((anlform = 1)or(anlform between 3 and 9))) then begin
          if (isdelete <> 1) then begin
            execute procedure acc_include_turn_in_remnval(
              :acckt,:anlkt1,:anlkt2,:anlkt3,:anlkt4,:anlkt5,:period_id,:catalog_id,
              0,:summanat,0,:summacur,0,:quantity,:from_id,
              :specmark,:accbatchkt_id)
            returning_values remnvalkt_id;
            accbatchhistory_id = accbatchhiskt_id;
            /* revalue */
            if (((anlform = 4)or(anlform = 6)or(anlform = 7))and
               ((quantity = 0)or((summanat = 0)and(summacur = 0)))) then begin
              /* reduce the cost */
              tmpquan = -quantity; tmpsumnat = -summanat; tmpsumcur = -summacur; 
              execute procedure f_revalue_remnval(
                :operdate,  :accbatchkt_id, :accdb,:anldb1,:anldb2,:anldb3,:anldb4,:anldb5,
                :catalog_id, 0, 0, :doctype,:docnumber,:docdate, :tmpquan, :tmpsumnat, :tmpsumcur,
                :from_id, :accbatchhiskt_id)
              returning_values accbatchhistory_id;
            end
            /* out good */
            if (((anlform = 4)or(anlform = 6)or(anlform = 7))and
              (quantity<>0)and((summanat<>0)or(summacur<>0))) then begin
              execute procedure p_out_good_with_accbatch(:accbatchkt_id, :accbatchhiskt_id, :quantity);
            end
            if (IsDelete = 0) then
              update economicspec
              set
                quantity = :quantity,
                summanat = :summanat,
                summacur = :summacur,
                remnvalkt_id = :remnvalkt_id,
                accbatchkt_id = null,
                accbatchhistorykt_id = :accbatchhistory_id
              where (id = :espec_id);
          end
        end
        /* remnacc */
        if (isdelete <> 1) then begin
          execute procedure acc_include_turn_in_remnacc(:acckt,:period_id)
          returning_values :remnacckt_id;
          update economicspec
          set
            remnacckt_id = :remnacckt_id
          where (id = :espec_id);
        end
        /* remnanl */
        if ((anlkt1 is not null)or(anlkt2 is not null)or(anlkt3 is not null)or(anlkt4 is not null)or(anlkt5 is not null)) then begin
          if (isdelete <> 1) then begin
            execute procedure acc_include_turn_in_remnanl(
              :acckt,:anlkt1,:anlkt2,:anlkt3,:anlkt4,:anlkt5,:period_id)
            returning_values :remnanlkt_id;
            update economicspec
            set
              remnanlkt_id = :remnanlkt_id
            where (id = :espec_id);
          end
        end
      end
      /* one more economicspec */
      if (new_spec_sign = 1) then begin
        quantity = remnquan;
        summanat = remnsummanat;
        summacur = remnsummacur;
        execute procedure F_GET_ACCBATCH_ACCBATCHHIST(:operdate,:acckt,:anlkt1,:anlkt2,:anlkt3,:anlkt4,:anlkt5,null,
          :catalog_id,:from_id,:quantity)
        returning_values :accbatchkt_id, :accbatchhiskt_id, :quantity, :summanat, :summacur;
        if ((quantity is null) or (quantity = 0)) then
          new_spec_sign = 0;
        else begin
          /* create economicspec */
          newespec_id = gen_id(economicspec_id_gen, 1);
          insert into economicspec(id, economicoper_id, accdb_id, acckt_id, anldb1_id, anldb2_id, anldb3_id, anldb4_id, anldb5_id, anlkt1_id, anlkt2_id, anlkt3_id, anlkt4_id, anlkt5_id, catalog_id, quantity, summanat, summacur, curcource, accbatchdb_id, accbatchhistorydb_id, accbatchkt_id, accbatchhistorykt_id, feature1_id, feature2_id, feature3_id, feature4_id, feature5_id)
          select :newespec_id, economicoper_id, accdb_id, acckt_id, anldb1_id, anldb2_id, anldb3_id, anldb4_id, anldb5_id, anlkt1_id, anlkt2_id, anlkt3_id, anlkt4_id, anlkt5_id, catalog_id, :quantity, :summanat, :summacur, curcource, accbatchdb_id, accbatchhistorydb_id, accbatchkt_id, accbatchhistorykt_id, feature1_id, feature2_id, feature3_id, feature4_id, feature5_id
          from economicspec where id = :espec_id;
          espec_id = newespec_id;
        end
      end 
    end /* while */
  end /* for */
end
^

ALTER PROCEDURE C_F_CONTRACT_FACT_PLAN_SUM (
    DOCHEAD_ID INTEGER)
RETURNS (
    PLANSUM NUMERIC(15,3),
    FACTSUM NUMERIC(15,3),
    SHIPPEDPLAN NUMERIC(15,3),
    RECEIVEPLAN NUMERIC(15,3),
    SHIPPEDGOODPLAN NUMERIC(15,3),
    RECEIVEGOODPLAN NUMERIC(15,3),
    SHIPPEDFACT NUMERIC(15,3),
    RECEIVEFACT NUMERIC(15,3),
    SHIPPEDGOODFACT NUMERIC(15,3),
    RECEIVEGOODFACT NUMERIC(15,3))
AS
begin
  select sum(pip.plansum)
  from phaseitemplan pip, contractphase cp
  where (cp.dochead_id = :dochead_id) and (cp.id = pip.phase_id)
  into :plansum;
  if (plansum is null) then
    plansum = 0;

  select sum(pif.factsum)
  from phaseitemfact pif
  where (pif.dochead_id = :dochead_id)
  into :factsum;
  if (factsum is null) then
    factsum = 0;

  select sum(pip.plansum)
  from phaseitemplan pip, contractphase cp
  where (cp.dochead_id = :dochead_id) and (cp.id = pip.phase_id) and (pip.kind = 0)
  into :shippedPlan;
  if (shippedPlan is null) then
    shippedPlan = 0;

  select sum(pif.factsum)
  from phaseitemfact pif
  where (pif.dochead_id = :dochead_id) and (pif.kind = 0)
  into :shippedFact;
  if (shippedFact is null) then
    shippedFact = 0;

  select sum(pip.plansum)
  from phaseitemplan pip, contractphase cp
  where (cp.dochead_id = :dochead_id) and (cp.id = pip.phase_id) and (pip.kind = 1)
  into :receivePlan;
  if (receivePlan is null) then
    receivePlan = 0;

  select sum(pif.factsum)
  from phaseitemfact pif
  where (pif.dochead_id = :dochead_id) and (pif.kind = 1)
  into :receiveFact;
  if (receiveFact is null) then
    receiveFact = 0;

  select sum(pip.plansum)
  from phaseitemplan pip, contractphase cp
  where (cp.dochead_id = :dochead_id) and (cp.id = pip.phase_id) and (pip.kind = 2)
  into :shippedGoodPlan;
  if (shippedGoodPlan is null) then
    shippedGoodPlan = 0;

  select sum(pif.factsum)
  from phaseitemfact pif
  where (pif.dochead_id = :dochead_id) and (pif.kind = 2)
  into :shippedGoodFact;
  if (shippedGoodFact is null) then
    shippedGoodFact = 0;

  select sum(pip.plansum)
  from phaseitemplan pip, contractphase cp
  where (cp.dochead_id = :dochead_id) and (cp.id = pip.phase_id) and (pip.kind = 3)
  into :receiveGoodPlan;
  if (receiveGoodPlan is null) then
    receiveGoodPlan = 0;

  select sum(pif.factsum)
  from phaseitemfact pif
  where (pif.dochead_id = :dochead_id) and (pif.kind = 3)
  into :receiveGoodFact;
  if (receiveGoodFact is null) then
    receiveGoodFact = 0;

  suspend;
end
^

ALTER PROCEDURE CASH_BOOK (
    ACC INTEGER,
    DATE1 TIMESTAMP,
    DATE2 TIMESTAMP)
RETURNS (
    DOCNUM CHAR(20),
    CONTR CHAR(20),
    CORRACC CHAR(20),
    DBSUM NUMERIC(15,4),
    KTSUM NUMERIC(15,4))
AS
declare variable HeadId integer;
declare variable ContrFrom char(20);
declare variable ContrTo char(20);
declare variable AccDb char(20);
declare variable AccKt char(20);
declare variable Summa numeric(15,4);
declare variable ACC_CODE char(20);
begin
  select a.acc from accplan a where (a.id = :ACC) into :Acc_code;
  for 
    select distinct h.id, h.docnumber, c1.code ContrFrom, c2.code ContrTo
      from economicspec s join economicoper h on h.id=s.economicoper_id
           left join contractor c1 on c1.id = h.from_id
           left join contractor c2 on c2.id = h.to_id
      where ((s.accdb_id=:Acc) or (s.acckt_id=:Acc)) and
            (h.keepdate between :Date1 and :Date2)
      into :HeadId, :DocNum, :ContrFrom, :ContrTo
  do begin
    for
      select a1.acc, a2.acc, s.summanat
        from economicspec s
          left join accplan a1 on a1.id = s.accdb_id
          left join accplan a2 on a2.id = s.acckt_id
        where s.economicoper_id=:HeadId
        into :AccDb, :AccKt, :Summa
    do begin
      if (AccDb = Acc_code) then begin
        Contr = ContrFrom;
        CorrAcc = AccKt;
        DbSum = Summa;
        KtSum = 0;
        suspend;
      end
      else if (AccKt = Acc_code) then begin
        Contr = ContrTo;
        CorrAcc = AccDb;
        KtSum = Summa;
        DbSum = 0;
        suspend;
      end
    end
  end
end
^

ALTER PROCEDURE CASH_BOOK_SORT (
    ACC_ID INTEGER,
    DATE1 TIMESTAMP,
    DATE2 TIMESTAMP)
RETURNS (
    DOCNUM CHAR(20),
    CONTR CHAR(20),
    CORRACC CHAR(20),
    DBSUM NUMERIC(15,4),
    KTSUM NUMERIC(15,4),
    KIND INTEGER)
AS
declare variable HeadId integer;
declare variable ContrFrom char(20);
declare variable ContrTo char(20);
declare variable AccDb char(20);
declare variable AccKt char(20);
declare variable AccDb_id integer;
declare variable AccKt_id integer;
declare variable Summa numeric(15,4);
begin
  for 
    select distinct h.id, h.docnumber, c1.code ContrFrom, c2.code ContrTo
      from economicspec s join economicoper h on h.id=s.economicoper_id
           left join contractor c1 on c1.id = h.from_id
           left join contractor c2 on c2.id = h.to_id
      where ((s.accdb_id=:Acc_id) or (s.acckt_id=:Acc_id)) and
            (h.keepdate between :Date1 and :Date2)
      into :HeadId, :DocNum, :ContrFrom, :ContrTo
  do begin
    for
      select a1.id, a1.acc, a2.id, a2.acc, s.summanat
        from economicspec s
          left join accplan a1 on a1.id = s.accdb_id
          left join accplan a2 on a2.id = s.acckt_id
        where s.economicoper_id=:HeadId
        into :AccDb_id, :AccDb, :AccKt_id, :AccKt, :Summa
    do begin
      if (AccDb_id = Acc_id) then begin
        Kind = 1;
        Contr = ContrFrom;
        CorrAcc = AccKt;
        DbSum = Summa;
        KtSum = 0;
        suspend;
        Kind = 0;
      end
      else if (AccKt_id = Acc_id) then begin
        Contr = ContrTo;
        CorrAcc = AccDb;
        KtSum = Summa;
        DbSum = 0;
        Kind = 2;
        suspend;
        Kind = 0;
      end
    end
  end
end
^

ALTER PROCEDURE ENCODEDATE (
    AYEAR INTEGER,
    AMONTH INTEGER,
    ADAY INTEGER)
RETURNS (
    RESULT TIMESTAMP)
AS
BEGIN
  Result = ADay || '.' || AMonth || '.' || AYear;
END
^

ALTER PROCEDURE F_ACCREMN_FOR_PERIOD (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER,
    SPECMARK CHAR(20),
    GETREMN SMALLINT,
    GETENDREMN SMALLINT,
    GETTURN SMALLINT)
RETURNS (
    ACC INTEGER,
    REMNDB NUMERIC(15,4),
    REMNKT NUMERIC(15,4),
    TURNDB NUMERIC(15,4),
    TURNKT NUMERIC(15,4))
AS
declare variable Date1 timestamp;
 declare variable Date2 timestamp;
 declare variable remn_id integer;
begin
  select p.datefrom from period p where p.id = :Period1
    into :Date1;
  select p.dateto from period p where p.id = :Period2
    into :Date2;
  for 
    select a.id from accplan a order by a.upacc
      into :Acc
  do begin   
    if (exists(
               select *
               from (economicoper eo join economicspec es on eo.id=es.economicoper_id)
               where (eo.keepdate between :Date1 and :Date2) and
                     ((eo.specmark = :Specmark) or (:Specmark is null)) and
                     ((es.accdb_id = :Acc) or (es.acckt_id = :Acc))
              )) then begin    
      if (GetRemn = 1) then begin
        select r.id, r.remnbeginnatdb, r.remnbeginnatkt
          from remnacc r 
          where (r.acc_id = :Acc) and
                (r.period_id = :Period1)
          into :remn_id, :RemnDb, :RemnKt;
        if (GetEndRemn = 1) then begin
          select r.remnendnatdb, r.remnendnatkt
            from acc_remnacc_load(:remn_id) r
            into :RemnDb, :RemnKt;
        end
      end
      if (GetTurn = 1) then begin
        select sum(es.summanat)
          from economicspec es join economicoper eo on eo.id=es.economicoper_id 
          where (es.accdb_id = :Acc) and
            (eo.keepdate between :Date1 and :Date2) and
            ((eo.specmark = :Specmark) or ((eo.specmark is null) and (:Specmark is null)))
          into :TurnDb;      
        select sum(es.summanat)
          from economicspec es join economicoper eo on eo.id=es.economicoper_id 
          where (es.acckt_id = :Acc) and
            (eo.keepdate between :Date1 and :Date2) and
            ((eo.specmark = :Specmark) or ((eo.specmark is null) and (:Specmark is null)))
          into :TurnKt;      
      end
      suspend;
    end
  end
end
^

ALTER PROCEDURE F_ADD_PERIOD_TO_DATE (
    DATE1 TIMESTAMP,
    PERIOD_QUAN INTEGER,
    PERIOD_KIND SMALLINT)
RETURNS (
    DATE2 TIMESTAMP)
AS
declare variable dp double precision;
begin
  if (period_kind = 1/*hour*/) then begin
    dp = period_quan / 24.0;
    date2 = date1 + dp;
  end
  else if (period_kind = 2/*day*/) then begin
    dp = period_quan;
    date2 = date1 + dp;
  end
  else if (period_kind = 3/*month*/) then begin
    execute procedure f_inc_date(date1, 0, period_quan, 0)
      returning_values :date2;
  end
  else if (period_kind = 4/*year*/) then begin
    execute procedure f_inc_date(date1, 0, 0, period_quan)
      returning_values :date2;
  end
  suspend;
end
^

ALTER PROCEDURE F_CALC_CATALOG_PRICE (
    CATALOG_ID INTEGER,
    OPERDATE DATE,
    CURCODE_IN VARCHAR(5))
RETURNS (
    RESULT DOUBLE PRECISION)
AS
begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 22.02.2001
 Измененено:
 Назначение: Возвращает учетную цену позиции КТУ
 Используется в формуле
**********************************************************/
    select f.costnat
      from F_GET_CATALOG_PRICE (:CATALOG_ID, :OPERDATE, :CURCODE_IN) f
      into :result;
    /**/
    if (result is null) then result = 0;
    result = round4(result);
  suspend;
end
^

ALTER PROCEDURE F_CALC_DEAD_DATE (
    ADATE TIMESTAMP,
    ASHELFLIFE INTEGER,
    ASHELFLIFE_MEAS INTEGER)
RETURNS (
    RESULT TIMESTAMP)
AS
DECLARE VARIABLE H INTEGER;
begin
  if (ASHELFLIFE_MEAS=0) then
    result = ADATE;
  else
  if (ASHELFLIFE_MEAS=1) then
  begin
    H = Div(ASHELFLIFE, 24);
    execute procedure f_inc_date(ADATE,H,0,0)
    returning_values :result;
  end
  else
  if (ASHELFLIFE_MEAS=2) then
    execute procedure f_inc_date(ADATE,ASHELFLIFE,0,0)
    returning_values :result;
  else
  if (ASHELFLIFE_MEAS=3) then
    execute procedure f_inc_date(ADATE,0,ASHELFLIFE,0)
    returning_values :result;
  else
  if (ASHELFLIFE_MEAS=4) then
    execute procedure f_inc_date(ADATE,0,0,ASHELFLIFE)
    returning_values :result;
  suspend;
end
^

ALTER PROCEDURE F_CALC_REMANTACC_ONDATE (
    ACC INTEGER,
    ADATE TIMESTAMP,
    ONBEGINDAY SMALLINT)
RETURNS (
    REMANT_NAT_DB NUMERIC(15,4),
    REMANT_CUR_DB NUMERIC(15,4),
    REMANT_NAT_KT NUMERIC(15,4),
    REMANT_CUR_KT NUMERIC(15,4))
AS
declare variable remn_id integer;
declare variable period_id integer;
declare variable PeriodBegin timestamp; 
declare variable PeriodEnd timestamp; 
declare variable turn_nat_db numeric(15,4);
declare variable turn_cur_db numeric(15,4);
declare variable turn_nat_kt numeric(15,4);
declare variable turn_cur_kt numeric(15,4);
declare variable AccType smallint;
declare variable RemnDbKtId integer;
declare variable TurnTmpNatDb numeric(15,4); 
declare variable TurnTmpCurDb numeric(15,4); 
declare variable TurnTmpNatKt numeric(15,4); 
declare variable TurnTmpCurKt numeric(15,4); 
begin
    /* Get account type */
    select a.acctype from accplan a where a.id=:Acc into :AccType;
    if (AccType is null) then exception e_invalid_account;
    /**/
    if (onbeginday = 1) then begin /* on begin of day */
      /* Get remn from remnacc */
      select r.id, p.id, p.datefrom, p.dateto, 
             r.remnbeginnatdb, r.remnbegincurdb, r.remnbeginnatkt, r.remnbegincurkt 
        from remnacc r, period p
        where (:ADate between p.datefrom and p.dateto) and 
              (r.acc_id = :acc) and
              (r.period_id = p.id)
        into :remn_id, :period_id, :PeriodBegin, :PeriodEnd, 
             :remant_nat_db, :remant_cur_db, :remant_nat_kt, :remant_cur_kt;
      if (:period_id is null) then begin
        REMANT_NAT_DB = 0; REMANT_CUR_DB = 0;
        REMANT_NAT_KT = 0; REMANT_CUR_KT = 0;
        exit;
      end 
      /* Check if ADate is period begin - then just get remnacc */
      if (ADate = PeriodBegin) then exit;
      /* Get turns and calculate remnend */
      if (AccType = 2) then begin
        /* Account is active/passive unwrapped */
        for
          select r.id from remndbkt r
            where (r.acc_id=:Acc) and (r.period_id=:period_id)
            into :RemnDbKtId
        do begin
          select sum(s.summanat), sum(summacur)
            from (economicspec s join economicoper h on h.id=s.economicoper_id)
            where (s.remndb_id=:RemnDbKtId) and
                  (h.keepdate between :PeriodBegin and :ADate) and (h.keepdate <> :ADate)
            into :TurnTmpNatDb, :TurnTmpCurDb;
          /*if (TurnTmpNatDb is not null) then remant_nat_db = remant_nat_db+TurnTmpNatDb;
          if (TurnTmpCurDb is not null) then remant_cur_db = remant_cur_db+TurnTmpCurDb;*/
          if (TurnTmpNatDb is null) then TurnTmpNatDb = 0;
          if (TurnTmpCurDb is null) then TurnTmpCurDb = 0;
          select sum(s.summanat), sum(summacur)
            from (economicspec s join economicoper h on h.id=s.economicoper_id)
            where (s.remnkt_id=:RemnDbKtId) and
                  (h.keepdate between :PeriodBegin and :ADate) and (h.keepdate <> :ADate)
            into :TurnTmpNatKt, :TurnTmpCurKt;
          /*if (TurnTmpNatKt is not null) then remant_nat_kt = remant_nat_kt+TurnTmpNatKt;
          if (TurnTmpCurKt is not null) then remant_cur_kt = remant_cur_kt+TurnTmpCurKt;*/
          if (TurnTmpNatKt is null) then TurnTmpNatKt = 0;
          if (TurnTmpCurKt is null) then TurnTmpCurKt = 0;
          execute procedure acc_calc_remndbkt_end(
            :remant_nat_db, :remant_nat_kt,
            :TurnTmpNatDb, :TurnTmpNatKt,
            :remant_cur_db, :remant_cur_kt,
            :TurnTmpCurDb, :TurnTmpCurKt)
          returning_values :remant_nat_db, :remant_nat_kt, :remant_cur_db, :remant_cur_kt;
        end
      end
      else begin
        /* All other account types */
        select sum(summanat), sum(summacur) 
          from (economicspec s join economicoper h on h.id=s.economicoper_id)
          where (s.remnaccdb_id=:remn_id) and
                  (h.keepdate between :PeriodBegin and :ADate) and (h.keepdate <> :ADate)
          into :turn_nat_db, :turn_cur_db;
        if (turn_nat_db is null) then turn_nat_db = 0;
        if (turn_cur_db is null) then turn_cur_db = 0;
        select sum(summanat), sum(summacur) 
          from (economicspec s join economicoper h on h.id=s.economicoper_id)
          where (s.remnacckt_id=:remn_id) and
                  (h.keepdate between :PeriodBegin and :ADate) and (h.keepdate <> :ADate)
          into :turn_nat_kt, :turn_cur_kt;
        if (turn_nat_kt is null) then turn_nat_kt = 0;
        if (turn_cur_kt is null) then turn_cur_kt = 0;
        execute procedure acc_calc_remnacc_end(:Acc, :period_id,
          :remant_nat_db, :remant_nat_kt, :remant_cur_db, :remant_cur_kt,
          :turn_nat_db, :turn_nat_kt, :turn_cur_db, :turn_cur_kt)
        returning_values :remant_nat_db, :remant_nat_kt, :remant_cur_db, :remant_cur_kt;
      end
    end
    /**/
    else begin /* on end of day */
      /* Get remn from remnacc */
      select r.id, p.id, p.datefrom, p.dateto, 
             r.remnbeginnatdb, r.remnbegincurdb, r.remnbeginnatkt, r.remnbegincurkt 
      from remnacc r, period p
      where (:ADate between p.datefrom and p.dateto) and 
            (r.acc_id = :acc) and
            (r.period_id = p.id)
      into :remn_id, :period_id, :PeriodBegin, :PeriodEnd, 
           :remant_nat_db, :remant_cur_db, :remant_nat_kt, :remant_cur_kt;
      if (:period_id is null) then begin
        REMANT_NAT_DB = 0; REMANT_CUR_DB = 0;
        REMANT_NAT_KT = 0; REMANT_CUR_KT = 0;
        exit;
      end 
      /* Check if ADate is period end - then just get remnacc */
      if (ADate = PeriodEnd) then begin
        select r.remnendnatdb, r.remnendcurdb, r.remnendnatkt, r.remnendcurkt
        from acc_remnacc_load(:remn_id) r
        into :remant_nat_db, :remant_cur_db, :remant_nat_kt, :remant_cur_kt;
        exit;
      end
      /* Get turns and calculate remnend */
      if (AccType = 2) then begin
        /* Account is active/passive unwrapped */
        for
          select r.id from remndbkt r
            where (r.acc_id=:Acc) and (r.period_id=:period_id)
            into :RemnDbKtId
        do begin
          select sum(s.summanat), sum(summacur)
            from (economicspec s join economicoper h on h.id=s.economicoper_id)
            where (s.remndb_id=:RemnDbKtId) and
                  (h.keepdate between :PeriodBegin and :ADate)
            into :TurnTmpNatDb, :TurnTmpCurDb;
          /*if (TurnTmpNatDb is not null) then remant_nat_db = remant_nat_db+TurnTmpNatDb;
          if (TurnTmpCurDb is not null) then remant_cur_db = remant_cur_db+TurnTmpCurDb;*/
          if (TurnTmpNatDb is null) then TurnTmpNatDb = 0;
          if (TurnTmpCurDb is null) then TurnTmpCurDb = 0;
          select sum(s.summanat), sum(summacur)
            from (economicspec s join economicoper h on h.id=s.economicoper_id)
            where (s.remnkt_id=:RemnDbKtId) and
                  (h.keepdate between :PeriodBegin and :ADate)
            into :TurnTmpNatKt, :TurnTmpCurKt;
          /*if (TurnTmpNatKt is not null) then remant_nat_kt = remant_nat_kt+TurnTmpNatKt;
          if (TurnTmpCurKt is not null) then remant_cur_kt = remant_cur_kt+TurnTmpCurKt;*/
          if (TurnTmpNatKt is null) then TurnTmpNatKt = 0;
          if (TurnTmpCurKt is null) then TurnTmpCurKt = 0;
          execute procedure acc_calc_remndbkt_end(
            :remant_nat_db, :remant_nat_kt,
            :TurnTmpNatDb, :TurnTmpNatKt,
            :remant_cur_db, :remant_cur_kt,
            :TurnTmpCurDb, :TurnTmpCurKt)
          returning_values :remant_nat_db, :remant_nat_kt, :remant_cur_db, :remant_cur_kt;
        end
      end
      else begin
        /* All other account types */
        select sum(summanat), sum(summacur) 
          from (economicspec s join economicoper h on h.id=s.economicoper_id)
          where (s.remnaccdb_id=:remn_id) and
                  (h.keepdate between :PeriodBegin and :ADate)
          into :turn_nat_db, :turn_cur_db;
        if (turn_nat_db is null) then turn_nat_db = 0;
        if (turn_cur_db is null) then turn_cur_db = 0;
        select sum(summanat), sum(summacur) 
          from (economicspec s join economicoper h on h.id=s.economicoper_id)
          where (s.remnacckt_id=:remn_id) and
                  (h.keepdate between :PeriodBegin and :ADate)
          into :turn_nat_kt, :turn_cur_kt;
        if (turn_nat_kt is null) then turn_nat_kt = 0;
        if (turn_cur_kt is null) then turn_cur_kt = 0;
        execute procedure acc_calc_remnacc_end(:Acc, :period_id,
          :remant_nat_db, :remant_nat_kt, :remant_cur_db, :remant_cur_kt,
          :turn_nat_db, :turn_nat_kt, :turn_cur_db, :turn_cur_kt)
        returning_values :remant_nat_db, :remant_nat_kt, :remant_cur_db, :remant_cur_kt;
      end
    end
end
^

ALTER PROCEDURE F_CALCULATE_DOCSPEC_BY_CATALOG (
    DOCUMENT_ID INTEGER,
    OPERDATE DATE,
    KIND INTEGER)
RETURNS (
    SPEC_ID INTEGER,
    CATALOG_ID INTEGER,
    QUAN NUMERIC(15,3),
    DOCDATE DATE,
    CURCODE_OUT VARCHAR(5),
    COSTNAT NUMERIC(15,4),
    COSTCUR NUMERIC(15,4),
    SUMNAT NUMERIC(15,4),
    DOCSECTION INTEGER,
    TAXGROUP_ID INTEGER,
    CALCTAXESKIND_ID INTEGER,
    TAXES_SUM NUMERIC(15,4),
    SPEC_SUM NUMERIC(15,4),
    SPEC_PRICE NUMERIC(15,4))
AS
declare variable old_price1 numeric(15,4);
declare variable curcode_in varchar(5);
declare variable disc_percent numeric(15,4);
declare variable disc_value numeric(15,4);
declare variable disc_on_doc numeric(15,4);

begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 15.12.2000
 Измененено: 12.04.2002(KK)
 Назначение: Проставление цены в спецификацию документа
 из справочника цен позиции КТУ.
 !!! ДЕЙСТВУЕТ ТОЛЬКО ДЛЯ ДОКУМЕНТОВ В НДЕ !!!
**********************************************************/
  /**/
  for
  select ds.id, ds.catalog_id, ds.quantity,
         d.docdate, d.currency_code,
         ds.taxgroup_id, d.calctaxeskind_id,
         d.docsection
    from docspec ds
    join dochead d on d.id=ds.dochead_id
    where ds.dochead_id=:document_id
    order by ds.id
    into :spec_id, :catalog_id, :quan,
         :docdate, :curcode_in,
         :taxgroup_id, :calctaxeskind_id,
         :docsection
  do begin
    select f.curcode_out, f.costnat, f.costcur
      from F_GET_CATALOG_PRICE (:CATALOG_ID, :OPERDATE, :CURCODE_IN) f
      into :curcode_out, :costnat, :costcur;
      /**/
    if (costnat is null) then costnat = 0;
      /**/
    /*kind=1 - в спецификацию проставляется цена*/
    /*kind=2 - в спецификации цена обнуляется*/
    if (kind=2) then costnat = 0;
    old_price1 = costnat;
      /**/
    sumnat = Round4(costnat * quan);
    update docspec ds set
       ds.price1 = :costnat,
       ds.summa1 = :sumnat
    where ds.id=:spec_id;

    /*Пересчет позиции спецификации*/
    execute procedure P_Add_Taxes(:docsection, :spec_id, :taxgroup_id, :calctaxeskind_id);
    execute procedure p_update_taxes_summ(:docsection, :spec_id, :calctaxeskind_id, :costnat, :sumnat, :quan, 0);
    execute procedure f_taxes_sum(1, 0, 0, :calctaxeskind_id, :docsection, :spec_id)
        returning_values :taxes_sum;
      spec_sum = Round4(sumnat + taxes_sum);
      spec_price = Round4(costnat + taxes_sum / quan);
      update docspec s set
        s.summa = :spec_sum,
        s.price = :spec_price
      where s.id = :spec_id;

    /*Дополнительные параметры документа*/
    /*Расходные ордера*/
    if (docsection=17) then begin
      select sd.discount_on_doc from stockdocumenthead sd
        where sd.dochead_id = :document_id into :disc_on_doc;
      select ss.discount from stockdocumentspec ss
        where ss.docspec_id = :spec_id into :disc_percent;
      disc_value = disc_percent * old_price1 / 100.0;
      execute procedure p_save_stockdocument_discount (:spec_id, :disc_on_doc, :disc_value, :calctaxeskind_id);
    end
    /*Заказы покупателей*/
    if (docsection=22) then begin
      select sd.discount_on_doc from orderhead sd
        where sd.dochead_id = :document_id into :disc_on_doc;
      select ss.discount from orderspec ss
        where ss.docspec_id = :spec_id into :disc_percent;
      disc_value = disc_percent * old_price1 / 100.0;
      execute procedure p_save_stockdocument_discount (:spec_id, :disc_on_doc, :disc_value, :calctaxeskind_id);
    end
    /*Исходящие накладные*/
    if (docsection=24) then begin
      select sd.discount_on_doc from invoicehead sd
        where sd.dochead_id = :document_id into :disc_on_doc;
      select ss.discount from invoicespec ss
        where ss.docspec_id = :spec_id into :disc_percent;
      disc_value = disc_percent * old_price1 / 100.0;
      execute procedure p_save_stockdocument_discount (:spec_id, :disc_on_doc, :disc_value, :calctaxeskind_id);
    end
    /*Исходящие счета*/
    if (docsection=26) then begin
      select sd.discount_on_doc from billhead sd
        where sd.dochead_id = :document_id into :disc_on_doc;
      select ss.discount from billspec ss
        where ss.docspec_id = :spec_id into :disc_percent;
      disc_value = disc_percent * old_price1 / 100.0;
      execute procedure p_save_stockdocument_discount (:spec_id, :disc_on_doc, :disc_value, :calctaxeskind_id);
    end

    /*Пересчет сумм заголовка документа*/
    execute procedure p_calculate_docsum (:docsection, :document_id);
  /*suspend;*/
  end
end
^

ALTER PROCEDURE F_CALCULATE_OUT_COST (
    OPERDATE TIMESTAMP,
    ACCBATCH_ID INTEGER,
    ACC INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    CATALOG_ID INTEGER,
    CONTR INTEGER,
    QUANTITY NUMERIC(15,3))
RETURNS (
    COSTNAT NUMERIC(15,4),
    COSTCUR NUMERIC(15,4),
    SUMMANAT NUMERIC(15,4),
    SUMMACUR NUMERIC(15,4),
    REALQUAN NUMERIC(15,3),
    ACCBATCH INTEGER,
    ACCBATCHHIST INTEGER)
AS
declare variable acctype smallint;
declare variable ans integer;
declare variable curcode char(5);
declare variable prec integer;
begin
/**********************************************************
 Автор: 
 Создано: давно
 Назначение: расчет цены списания по бух.учету
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 06.05.2003 Кривопустов Точность из настройки модуля бух.учет
 15.12.2003 Кривопустов Bug#2084, введена процедура ACC_CHECK_LAST_BATCH
**********************************************************/
  /*ans = dm('Enter F_CALCULATE_OUT_COST');*/
  select c.currency_prec from acc_config c into :prec;

  select a.anlform, a.currency_code from accplan a
  where (a.id = :Acc)
  into :acctype, :curcode;
  ans=wi('acctype',acctype);
  /* calc cost */
  if (acctype = 3) then begin
    /* find catalogprice */
    if (curcode is not null) then begin
      select cp.price, cp.equivalent_price from catalogprice cp
      where (cp.catalog_id = :catalog_id) and (cp.in_action = (select max(cp1.in_action) from catalogprice cp1 where (cp1.catalog_id = :catalog_id) and (cp1.currency_code = :curcode) and (cp1.in_action <= :operdate)))
      into :costnat, :costcur;
    end
    else begin
      select cp.price from catalogprice cp
      where (cp.catalog_id = :catalog_id) and (cp.in_action = (select max(cp1.in_action) from catalogprice cp1 where (cp1.catalog_id = :catalog_id) and (cp1.in_action <= :operdate)))
      into :costnat;
      costcur = costnat;
    end
    if (costnat is null) then costnat = 0;
    if (costcur is null) then costcur = 0;
    realquan = quantity;
    summanat = Round(costnat * realquan, prec);
    summacur = Round(costcur * realquan, prec);
    suspend;
    /*ans = dm('Exit F_CALCULATE_OUT_COST');*/
    Exit;
  end
  else
  /* batch calc */
  if ((acctype = 4)and(accbatch_id is not null)) then begin
    select a.id, a.costnat, a.costcur from accbatchhistory a
    where (a.accbatch_id = :accbatch_id) and
      (((:operdate >= a.begindate)and(:operdate < a.enddate))or
       ((:operdate >= a.begindate)and(a.enddate is null)))
    into :accbatchhist, :costnat, :costcur;
    accbatch = accbatch_id;
    realquan = quantity;
    summanat = Round(costnat * realquan, prec);
    summacur = Round(costcur * realquan, prec);

    execute procedure ACC_CHECK_LAST_BATCH(:operdate, :accbatch, :realquan, :summanat, :summacur)
    returning_values(:summanat, :summacur);

    suspend;
    /*ans = dm('Exit F_CALCULATE_OUT_COST');*/
    Exit;
  end
  else
  /* average cost */
  if (acctype = 5) then begin
    execute procedure acc_calc_average_outcost(:operdate, :acc, :anl1, :anl2, :anl3, :anl4, :anl5, :catalog_id, :contr, :quantity)
    returning_values :costnat, :costcur, :summanat, :summacur, :realquan;
    suspend;
    /*ans = dm('Exit F_CALCULATE_OUT_COST');*/
    exit;
  end
  else
  /* FIFO */
  if ((acctype = 6)or((acctype = 4)and(accbatch_id is null))) then begin
    for
      select a.id, a.endquan from accbatch a
      where (a.incomedate <= :operdate) and (a.endquan > 0) and ( a.acc_id = :Acc ) and
        ((a.anlplan1_id = :Anl1)or((a.anlplan1_id is null)and(:Anl1 is null))) and
        ((a.anlplan2_id = :Anl2)or((a.anlplan2_id is null)and(:Anl2 is null))) and
        ((a.anlplan3_id = :Anl3)or((a.anlplan3_id is null)and(:Anl3 is null))) and
        ((a.anlplan4_id = :Anl4)or((a.anlplan4_id is null)and(:Anl4 is null))) and
        ((a.anlplan5_id = :Anl5)or((a.anlplan5_id is null)and(:Anl5 is null))) and
        ( a.catalog_id = :catalog_id ) and
        ((a.contractor_id = :contr)or(a.contractor_id is null))
      order by a.incomedate, a.id ascending /* fifo */
      into :accbatch, :realquan
    do begin
      ans=wi('batchid',accbatch_id);
      select a.id, a.costnat, a.costcur from accbatchhistory a
      where (a.accbatch_id = :accbatch) and (((:operdate >= a.begindate) and (:operdate < a.enddate))or((:operdate >= a.begindate)and(a.enddate is null)))
      into :accbatchhist, :costnat, :costcur;
      ans = wf('costnat',15,3,costnat);
      if ((realquan - quantity) > 0) then realquan = quantity;

      execute procedure ACC_CHECK_LAST_BATCH(:operdate, :accbatch, :realquan, :summanat, :summacur)
      returning_values(:summanat, :summacur);

      summanat = Round(costnat * realquan, prec);
      summacur = Round(costcur * realquan, prec);
      suspend;
      /*ans = dm('Exit F_CALCULATE_OUT_COST');*/
      Exit;
    end
  end
  else
  /* LIFO */
  if (acctype = 7) then begin
    for
      select a.id, a.endquan from accbatch a
      where (a.incomedate <= :operdate) and (a.endquan > 0) and ( a.acc_id = :Acc ) and
        ((a.anlplan1_id = :Anl1)or((a.anlplan1_id is null)and(:Anl1 is null))) and
        ((a.anlplan2_id = :Anl2)or((a.anlplan2_id is null)and(:Anl2 is null))) and
        ((a.anlplan3_id = :Anl3)or((a.anlplan3_id is null)and(:Anl3 is null))) and
        ((a.anlplan4_id = :Anl4)or((a.anlplan4_id is null)and(:Anl4 is null))) and
        ((a.anlplan5_id = :Anl5)or((a.anlplan5_id is null)and(:Anl5 is null))) and
        ( a.catalog_id = :catalog_id ) and
        ((a.contractor_id = :contr)or(a.contractor_id is null))
      order by a.incomedate descending /* lifo */
      into :accbatch, :realquan
    do begin
      select a.id, a.costnat, a.costcur from accbatchhistory a
      where (a.accbatch_id = :accbatch) and (((:operdate >= a.begindate) and (:operdate < a.enddate))or((:operdate >= a.begindate)and(a.enddate is null)))
      into :accbatchhist, :costnat, :costcur;
      if ((realquan - quantity) > 0) then realquan = quantity;
      summanat = Round(costnat * realquan, prec);
      summacur = Round(costcur * realquan, prec);

      execute procedure ACC_CHECK_LAST_BATCH(:operdate, :accbatch, :realquan, :summanat, :summacur)
      returning_values(:summanat, :summacur);

      suspend;
      /*ans = dm('Exit F_CALCULATE_OUT_COST');*/
      Exit;
    end
  end
  /*ans = dm('Exit F_CALCULATE_OUT_COST');*/
end
^

ALTER PROCEDURE F_CALCULATE_OUT_COST_ANL (
    OPERDATE DATE,
    ACC INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    CATALOG_ID INTEGER,
    CONTR INTEGER,
    QUANTITY NUMERIC(15,3))
RETURNS (
    RESULT NUMERIC(15,2))
AS
declare variable COSTNAT numeric(15,2);
declare variable COSTCUR numeric(15,2);
declare variable SUMMACUR numeric(15,2);
declare variable REALQUAN numeric(15,3);
declare variable ACCBATCH integer;
declare variable ACCBATCHHIST integer;
begin
  /*ACC = upper(:ACC collate pxw_cyrl);
  EMPLOYEES = upper(:EMPLOYEES collate pxw_cyrl);
  PARTNER = upper(:PARTNER collate pxw_cyrl);
  ORGUNIT = upper(:ORGUNIT collate pxw_cyrl);*/

  execute procedure f_calculate_out_cost (:operdate, null, :acc, :anl1, :anl2, :anl3, :anl4, anl5,
    :catalog_id, :contr, :quantity)
  returning_values (:COSTNAT,:COSTCUR,:result,:SUMMACUR,:REALQUAN,:ACCBATCH,:ACCBATCHHIST);
  suspend;
end
^

ALTER PROCEDURE F_CALCULATE_OUT_COST_FORM (
    OPERDATE TIMESTAMP,
    ACC INTEGER,
    CATALOG_ID INTEGER,
    CONTR INTEGER,
    QUANTITY NUMERIC(15,3))
RETURNS (
    RESULT NUMERIC(15,4))
AS
declare variable COSTNAT numeric(15,4);
declare variable COSTCUR numeric(15,4);
declare variable SUMMACUR numeric(15,4);
declare variable REALQUAN numeric(15,3);
declare variable ACCBATCH integer;
declare variable ACCBATCHHIST integer;
begin
  /*ACC = upper(:ACC collate pxw_cyrl);
  EMPLOYEES = upper(:EMPLOYEES collate pxw_cyrl);
  PARTNER = upper(:PARTNER collate pxw_cyrl);
  ORGUNIT = upper(:ORGUNIT collate pxw_cyrl);*/

  execute procedure f_calculate_out_cost (:operdate, null, :acc, null, null, null, null, null,
    :catalog_id, :contr, :quantity)
  returning_values (:COSTNAT,:COSTCUR,:result,:SUMMACUR,:REALQUAN,:ACCBATCH,:ACCBATCHHIST);
  suspend;
end
^

ALTER PROCEDURE F_CALCULATE_QUANTITY_END (
    OPERDATE DATE,
    ACCBATCH_ID INTEGER,
    ACC INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    CATALOG_ID INTEGER,
    CONTR INTEGER)
RETURNS (
    RESULT NUMERIC(15,3))
AS
declare variable ANL1_ID integer;
declare variable ANL2_ID integer;
declare variable ANL3_ID integer;
declare variable ANL4_ID integer;
declare variable ANL5_ID integer;
declare variable PERIOD_ID integer;
declare variable REMNVAL_ID integer;
declare variable BEGINQUAN numeric(15,3);
declare variable QUANDB numeric(15,3);
declare variable QUANKT numeric(15,3);
begin
/* Процедура рассчитывает количество ТМЦ на заданную дату по оборотке по ТМЦ */

    /* find period */
    execute procedure F_Find_Period :operdate
    returning_values :period_id;

    result = 0;
    /* find remn on begin period*/
    select r.id, r.beginquan from remnval r
    where ( r.acc_id = :Acc ) and ( r.period_id = :period_id ) and
      ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1_Id is null))) and
      ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2_Id is null))) and
      ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3_Id is null))) and
      ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4_Id is null))) and
      ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5_Id is null))) and
      ( r.batch_id is null ) and
      ( r.catalog_id = :catalog_id ) and
      ((r.contractor_id = :contr)or(r.contractor_id is null))
    into :remnval_id, :beginquan;
    
    if (remnval_id is null) then begin
      result = 0;
      suspend;
      exit;
    end
    
    /* calculate debet turn */
    select sum(e.quantity) 
    from economicspec e join economicoper eo on eo.id=e.economicoper_id
    where (e.remnvaldb_id = :remnval_id)and(eo.id = e.economicoper_id)and(eo.keepdate<=:operdate)
    PLAN JOIN (E INDEX (IDX_ECONSPEC_VALDB_ID),EO INDEX (IDX_ECONOMICOPER_ID_KEEPDATE))
    into :quandb;
    if (quandb is null) then quandb = 0;

    /* calculate kredit turn */
    select sum(e.quantity) 
    from economicspec e join economicoper eo on eo.id=e.economicoper_id
    where (e.remnvalkt_id = :remnval_id)and(eo.id = e.economicoper_id)and(eo.keepdate<=:operdate)
    PLAN JOIN (E INDEX (IDX_ECONSPEC_VALKT_ID),EO INDEX (IDX_ECONOMICOPER_ID_KEEPDATE))
    into :quankt;
    if (quankt is null) then quankt = 0;

    result = beginquan + quandb - quankt;

  suspend;
end
^

ALTER PROCEDURE F_CATFOLD_IS_BRANCH_MEMBER (
    FOLDER_ID INTEGER,
    BRANCHHEAD_ID INTEGER)
RETURNS (
    RESULT SMALLINT)
AS
declare variable foldparent_id integer;
begin
  if (folder_id = branchhead_id) then begin result = 1; Exit; end
  select c.parent_id from catalogfolder c where c.id = :folder_id
  into :foldparent_id;
  if (foldparent_id is null) then begin
    result = 0; Exit;
  end
  else
    execute procedure f_catfold_is_branch_member (:foldparent_id, branchhead_id)
    returning_values :result;
end
^

ALTER PROCEDURE F_CHECK_DOCUMENT_NUMBER (
    FOLDERTYPE SMALLINT,
    DOCTYPE CHAR(15),
    DOCNUMBER CHAR(20))
RETURNS (
    ISFIND SMALLINT)
AS
begin
  if ( exists (select docnumber from dochead d, folder f where ((f.id = d.folder_id) and (f.foldertype = :foldertype) and (d.doctype = :doctype) and (d.docnumber = :docnumber)))) then
    IsFind = 1;
  else IsFind = 0;
  suspend;
end
^

ALTER PROCEDURE F_CREATE_ACCBATCH (
    INCOMEDATE TIMESTAMP,
    ACC INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    CATALOG_ID INTEGER,
    DOCSECTION SMALLINT,
    DOC_ID INTEGER,
    DOCTYPE VARCHAR(15),
    DOCNUMBER VARCHAR(20),
    DOCDATE TIMESTAMP,
    BEGINQUAN NUMERIC(15,3),
    SUMMANAT NUMERIC(15,4),
    SUMMACUR NUMERIC(15,4),
    CONTR INTEGER,
    OLDACCBATCH_ID INTEGER)
RETURNS (
    ACCBATCH_ID INTEGER,
    ACCBATCHHISTORY_ID INTEGER)
AS
declare variable costnat numeric(18,6);
declare variable costcur numeric(18,6);
declare variable ans integer;
begin
/**********************************************************
 Автор: Олег Сафонов
 Создано: давно
 Назначение: создание партии ТМЦ в бух.учете
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 29.09.2003 Кривопустов Корректное округление
**********************************************************/
  /* if update economicspec */
  if (oldaccbatch_id is not null) then begin
    ans = wi('...oldaccbatch...', oldaccbatch_id);
    /* find accbatch */
    for /* maybe  several batch */
    select a.id from accbatch a
    where (a.acc_id=:acc)and
      ((a.anlplan1_id = :Anl1)or((a.anlplan1_id is null)and(:Anl1 is null))) and
      ((a.anlplan2_id = :Anl2)or((a.anlplan2_id is null)and(:Anl2 is null))) and
      ((a.anlplan3_id = :Anl3)or((a.anlplan3_id is null)and(:Anl3 is null))) and
      ((a.anlplan4_id = :Anl4)or((a.anlplan4_id is null)and(:Anl4 is null))) and
      ((a.anlplan5_id = :Anl5)or((a.anlplan5_id is null)and(:Anl5 is null))) and
      ( a.doc_id = :doc_id ) and ( a.doctype = :doctype ) and ( a.docnumber = :docnumber ) and ( a.docdate = :docdate ) and
      ( a.catalog_id = :catalog_id ) and
      ((a.contractor_id = :contr)or(a.contractor_id is null))
    into :accbatch_id
    do begin
      if (accbatch_id = oldaccbatch_id) then begin
        /* calculation cost */
        /*costnat = Round(summanat / beginquan, prec);
        costcur = Round(summacur / beginquan, prec);*/
        costnat = summanat / beginquan;
        costcur = summacur / beginquan;
        /* update old batch */
        update accbatch
        set
          incomedate = :incomedate,
          incomecostnat = :costnat,
          incomecostcur = :costcur,
          beginquan = :beginquan,
          endquan = :beginquan - beginquan + endquan
        where id = :oldaccbatch_id;
        /* update batch history */
        update accbatchhistory
        set
          begindate = :incomedate,
          costnat = :costnat,
          costcur = :costcur
        where ((accbatch_id = :oldaccbatch_id)and(actiontype = 0));
        Exit;
      end
    end
    if (not exists (select id from economicspec where accbatchdb_id = :oldaccbatch_id)) then 
      delete from accbatch where id = :oldaccbatch_id;
  end
  /* calculation cost */
  /*costnat = Round(summanat / beginquan, prec);
  costcur = Round(summacur / beginquan, prec);*/
  costnat = summanat / beginquan;
  costcur = summacur / beginquan;
  /* find accbatch */
  select a.id from accbatch a
  where (a.acc_id=:acc)and
    ((a.anlplan1_id = :Anl1)or((a.anlplan1_id is null)and(:Anl1 is null))) and
    ((a.anlplan2_id = :Anl2)or((a.anlplan2_id is null)and(:Anl2 is null))) and
    ((a.anlplan3_id = :Anl3)or((a.anlplan3_id is null)and(:Anl3 is null))) and
    ((a.anlplan4_id = :Anl4)or((a.anlplan4_id is null)and(:Anl4 is null))) and
    ((a.anlplan5_id = :Anl5)or((a.anlplan5_id is null)and(:Anl5 is null))) and
    ( a.doc_id = :doc_id ) and ( a.doctype = :doctype ) and ( a.docnumber = :docnumber ) and ( a.docdate = :docdate ) and
    ( a.incomecostnat = :costnat ) and 
    ( a.incomecostcur = :costcur ) and
    ( a.catalog_id = :catalog_id ) and
    ((a.contractor_id = :contr)or(a.contractor_id is null))
  into :accbatch_id;
  if (accbatch_id is null) then begin
    /* insert batch */
    accbatch_id = gen_id(accbatch_id_gen,1);
    insert into accbatch(id, incomedate, incomecostnat, incomecostcur, catalog_id, acc_id,
      anlplan1_id, anlplan2_id, anlplan3_id, anlplan4_id, anlplan5_id, docsection, doc_id, doctype, docnumber, docdate, beginquan, endquan,
      contractor_id)
    values
    (:accbatch_id, :incomedate, :costnat, :costcur, :catalog_id, :acc, :anl1, :anl2, :anl3, :anl4, :anl5,
     :docsection, :doc_id, :doctype, :docnumber, :docdate, :beginquan, :beginquan,
     :contr);
    /* insert first batch history (income) */
    accbatchhistory_id = gen_id(accbatchhistory_id_gen,1);
    insert into accbatchhistory(id, accbatch_id, begindate, costnat, costcur)
    values
    (:accbatchhistory_id, :accbatch_id, :incomedate, :costnat, :costcur);
   end
   else
     /* update accbatch */
     update accbatch
     set
       beginquan = beginquan + :beginquan,
       endquan = endquan + :beginquan
     where id = :accbatch_id;
end
^

ALTER PROCEDURE F_CREATE_STOCKBATCH (
    STOCKCARD_ID INTEGER,
    CREATEDATE TIMESTAMP,
    CONTRACTOR_ID INTEGER,
    DOC_ID INTEGER,
    DOCSPEC_ID INTEGER,
    DOCTYPE CHAR(15),
    DOCNUMBER CHAR(20),
    DOCDATE TIMESTAMP,
    QUAN NUMERIC(15,3),
    PRICENAT NUMERIC(15,4),
    PRICECUR NUMERIC(15,4),
    CURR_CODE CHAR(5),
    CATALOG_ID INTEGER,
    PREV_SBH INTEGER,
    BESTBEFORE TIMESTAMP)
RETURNS (
    STOCKBATCHIST_ID INTEGER)
AS
declare variable stockbatch_id integer;
begin
  /* Find stockbatch */
  select sb.id from stockbatch sb where
    (sb.stockcard_id = :stockcard_id) and
    (sb.contractor_id = :contractor_id) and
    (sb.doctype = :doctype) and (sb.docnumber = :docnumber) and (sb.docdate = :docdate) and
    (sb.pricenat = :pricenat) and (sb.pricecur = :pricecur) and
    (sb.currency_code = :curr_code) and
    ((sb.bestbefore = :bestbefore) or ((sb.bestbefore is null)and(:bestbefore is null)))
  into :stockbatch_id;
  if (stockbatch_id is null) then begin
    /* add stockbatch */
    stockbatch_id = gen_id(stockbatch_id_gen, 1);
    insert into stockbatch(id, stockcard_id, contractor_id,
      pricenat, pricecur, currency_code, doctype, docnumber, docdate, createdate, bestbefore)
    values(:stockbatch_id, :stockcard_id, :contractor_id,
      :pricenat, :pricecur, :curr_code, :doctype, :docnumber, :docdate, :createdate, :bestbefore);
  end
  /* add stockbatchhistory */
  stockbatchist_id = gen_id(stockbatchhistory_id_gen, 1);
  insert into stockbatchhistory(id, stockbatch_id, kind, document_id, documentspec_id, quantity, processdate, prev_stockbatchhist_id)
  values(:stockbatchist_id, :stockbatch_id, 0, :doc_id, :docspec_id, :quan, :createdate, :prev_sbh);
end
^

ALTER PROCEDURE F_DATE_SEQUENCE (
    DATE1 DATE,
    DATE2 DATE)
RETURNS (
    RESULT DATE)
AS
begin
     result = date1;
     while(result <= date2) do begin
       suspend;
       result = result + 1;
     end
end
^

ALTER PROCEDURE F_DOC_HEAD_STATE_BROWSE (
    DOCACTION_ID INTEGER)
RETURNS (
    ID INTEGER,
    READYSUMMA NUMERIC(15,4),
    DOCSECTIONNAME VARCHAR(80),
    DOCUMENT VARCHAR(256),
    DATETIME TIMESTAMP,
    USERNAME CHAR(31),
    CREATE_DOC SMALLINT,
    DATA1 INTEGER,
    DATA2 INTEGER)
AS
DECLARE VARIABLE DOCSEC INTEGER;
begin
  for
    select dhs.id, dhs.readysumma, dhs.data1, dhs.data2, dhs.datetime, su.name, sa.createdoc_flag
    from docheadstate dhs
      join docaction da on da.id = dhs.docaction_id
      left join docprocessstage dps on dps.id = da.stage_id
      left join dp_stage_action sa on sa.id = dps.stage
      left join sec_users su on su.id = dhs.user_id
    where (dhs.docaction_id = :docaction_id)
    into :id, :readysumma, :data1, :data2, :datetime, :username, :create_doc
  do begin
    if (create_doc = 1) then begin
      docsec = data1;
      select dsname from docsection where id = :docsec into :docsectionname;
      execute procedure F_DocumentCaption :docsec, :data2
      returning_values :document;
    end
    suspend;
  end
end
^

ALTER PROCEDURE F_DOCUMENTCAPTION (
    DOCSECTION INTEGER,
    DOC_ID INTEGER)
RETURNS (
    RESULT VARCHAR(300))
AS
begin
  result = '';
  /* Economic Oper */
  if (docsection = 1) then begin
    select RaiseEconOperCaption(eo.keepdate, eo.comment) || '; ' || FormatNum(eo.summa, '') || ' ' || c.base_currency
    from economicoper eo, acc_config c
    where eo.id = :doc_id
    into :result; 
  end
  /* Finance Oper */
  else if (docsection = 20) then begin
    select RaiseEconOperCaption(fo.keepdate, fo.comment) || '; ' || FormatNum(fo.sumcur, '') || ' ' || RTrim(fo.curcode) || '; ' || FormatNum(fo.sumnat, '') || ' ' || c.base_currency
    from finoper fo, fin_config c
    where fo.id = :doc_id
    into :result; 
  end
  else begin
    /* the rest */
    if (doc_id <> 0) then
      select RaiseDocumentCaption(dh.doctype, dh.docnumber, dh.docdate) || '; ' || FormatNum(dh.summacur, '') || ' '  || dh.currency_code
      from dochead dh
      where dh.id = :doc_id
      into :result;
  end
  suspend;
end
^

ALTER PROCEDURE F_FIND_PERIOD (
    OPERDATE TIMESTAMP)
RETURNS (
    PERIOD_ID INTEGER)
AS
begin
  select p.id from period p
  where ( :operdate between p.datefrom and p.dateto )
  into :period_id;
  if ( period_id is null ) then exception E_Invalid_Period;
end
^

ALTER PROCEDURE F_FIND_PERMISSIBLEACCOUNTS (
    ACCDB INTEGER,
    ANLDB1 INTEGER,
    ANLDB2 INTEGER,
    ANLDB3 INTEGER,
    ANLDB4 INTEGER,
    ANLDB5 INTEGER,
    ACCKT INTEGER,
    ANLKT1 INTEGER,
    ANLKT2 INTEGER,
    ANLKT3 INTEGER,
    ANLKT4 INTEGER,
    ANLKT5 INTEGER)
RETURNS (
    RESULT INTEGER)
AS
begin
  if (exists(
       select * from permissibleaccounts p
       where
        ( p.accdb_id = :AccDb ) and
        ((p.anldb1_id = :Anldb1)or((p.anldb1_id is null)and(:Anldb1 is null))) and 
        ((p.anldb2_id = :Anldb2)or((p.anldb2_id is null)and(:Anldb2 is null))) and 
        ((p.anldb3_id = :Anldb3)or((p.anldb3_id is null)and(:Anldb3 is null))) and
        ((p.anldb4_id = :Anldb4)or((p.anldb4_id is null)and(:Anldb4 is null))) and 
        ((p.anldb5_id = :Anldb5)or((p.anldb5_id is null)and(:Anldb5 is null))) and
        ( p.acckt_id = :AccKt ) and
        ((p.anlkt1_id = :Anlkt1)or((p.anlkt1_id is null)and(:Anlkt1 is null))) and 
        ((p.anlkt2_id = :Anlkt2)or((p.anlkt2_id is null)and(:Anlkt2 is null))) and 
        ((p.anlkt3_id = :Anlkt3)or((p.anlkt3_id is null)and(:Anlkt3 is null))) and 
        ((p.anlkt4_id = :Anlkt4)or((p.anlkt4_id is null)and(:Anlkt4 is null))) and 
        ((p.anlkt5_id = :Anlkt5)or((p.anlkt5_id is null)and(:Anlkt5 is null)))
    )) then result = 1;
  else result = 0;
end
^

ALTER PROCEDURE F_FIND_PREV_PERIOD (
    OPERDATE TIMESTAMP)
RETURNS (
    PERIOD_ID INTEGER)
AS
begin
  for
    select p.id from period p where p.dateto <= :operdate
    order by p.datefrom desc /* !!! */
    into :period_id
  do Exit;
end
^

ALTER PROCEDURE F_FOLD_IS_BRANCH_MEMBER (
    FOLDER_ID INTEGER,
    BRANCHHEAD_ID INTEGER,
    AFOLDERTYPE SMALLINT)
RETURNS (
    RESULT SMALLINT)
AS
declare variable foldparent_id integer;
begin
  if (folder_id = branchhead_id) then begin 
    result = 1; Exit; 
  end
  select f.parent_id from folder f 
    where (f.foldertype = :AFolderType)and(f.id = :folder_id)
    into :foldparent_id;
  if (foldparent_id is null) then begin
    result = 0; Exit;
  end
  else
    execute procedure f_fold_is_branch_member (:foldparent_id, :branchhead_id, AFolderType)
    returning_values :result;
end
^

ALTER PROCEDURE F_GET_ACC_LIST (
    ACC1 INTEGER,
    ACC2 INTEGER,
    ACC3 INTEGER,
    ACC4 INTEGER,
    ACC5 INTEGER)
RETURNS (
    ACC INTEGER)
AS
begin
/**********************************************************
 Автор: Кривопустов Константин
 Создано: 25.11.00
 Измененено: Константин Кривопустов, 02.08.2001
 Назначение: Возвращает список счетов, строго соответствующих
 задаваемым счетам.
**********************************************************/

 for
  select a.id from accplan a
   where (a.id=:acc1)or(a.id=:acc2)or(a.id=:Acc3)or
         (a.id=:acc4)or(a.id=:acc5)
 into :Acc
 do   
  suspend;
end
^

ALTER PROCEDURE F_GET_ACC_PARAMS (
    ACC INTEGER,
    TYPE_PARAMS VARCHAR(20))
RETURNS (
    RESULT VARCHAR(80))
AS
begin
  /* Procedure Text */
  TYPE_PARAMS = upper(TYPE_PARAMS collate pxw_cyrl);
  if (type_params = upper('code' collate pxw_cyrl)) then
     select a.acc
     from accplan a
     where a.id=:acc
     into :result;
  if (type_params = upper('name' collate pxw_cyrl)) then
     select a.accname
     from accplan a
     where a.id=:acc
     into :result;
  suspend;
end
^

ALTER PROCEDURE F_GET_ACC_SUB_LIST (
    ACC1 VARCHAR(20),
    ACC2 VARCHAR(20),
    ACC3 VARCHAR(20),
    ACC4 VARCHAR(20),
    ACC5 VARCHAR(20))
RETURNS (
    UPACC CHAR(20))
AS
declare variable StrAcc1 varchar (21);
declare variable StrAcc2 varchar (21);
declare variable StrAcc3 varchar (21);
declare variable StrAcc4 varchar (21);
declare variable StrAcc5 varchar (21);

begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 25.11.00
 Измененено:
 Назначение: Возвращает список счетов и субсчетов, не строго
 соответствующих задаваемым счетам. Например - входной счет
 '76', а выходные - '76/01', '76/02', '76/03' и т.д.
**********************************************************/

 StrAcc1 = '';
 StrAcc2 = '';
 StrAcc3 = '';
 StrAcc4 = '';
 StrAcc5 = '';
 if (Acc1<>'') then StrAcc1 = ACC1||'%';
 if (Acc2<>'') then StrAcc2 = ACC2||'%';
 if (Acc3<>'') then StrAcc3 = ACC3||'%';
 if (Acc4<>'') then StrAcc4 = ACC4||'%';
 if (Acc5<>'') then StrAcc5 = ACC5||'%';
 for
  select a.upacc from accplan a
   where ((a.upacc=:acc1)or(a.upacc like :StrAcc1)) or
         ((a.upacc=:acc2)or(a.upacc like :StrAcc2)) or
         ((a.upacc=:acc3)or(a.upacc like :StrAcc3)) or
         ((a.upacc=:acc4)or(a.upacc like :StrAcc4)) or
         ((a.upacc=:acc5)or(a.upacc like :StrAcc5))
 into :UpAcc
 do   
  suspend;
end
^

ALTER PROCEDURE F_GET_ACCANALITICFORM (
    ACC CHAR(20))
RETURNS (
    RESULT SMALLINT)
AS
begin
  select a.anlform from accplan a
  where ( a.upacc = :Acc )
  into :result;
end
^

ALTER PROCEDURE F_GET_ACCBATCH_ACCBATCHHIST (
    OPERDATE TIMESTAMP,
    ACC INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    ACCOUNTBATCH_ID INTEGER,
    CAT_ID INTEGER,
    CONTR INTEGER,
    QUAN NUMERIC(15,3))
RETURNS (
    ACCBATCH_ID INTEGER,
    ACCBATCHHIST_ID INTEGER,
    QUANTITY NUMERIC(15,3),
    SUMMANAT NUMERIC(15,4),
    SUMMACUR NUMERIC(15,4))
AS
DECLARE VARIABLE COSTNAT NUMERIC(15,3);
DECLARE VARIABLE COSTCUR NUMERIC(15,3);
begin
  execute procedure F_CALCULATE_OUT_COST :operdate, :accountbatch_id,
    :acc,  :anl1, :anl2, :anl3, :anl4, :anl5, :cat_id, :contr, :quan
  returning_values :COSTNAT, :COSTCUR, :SUMMANAT, :SUMMACUR, :QUANTITY, :ACCBATCH_id, :ACCBATCHHIST_id;
end
^

ALTER PROCEDURE F_GET_ACCTYPE (
    ACC CHAR(20))
RETURNS (
    RESULT SMALLINT)
AS
begin
  select a.acctype from accplan a
  where ( a.upacc = :Acc )
  into :result;
end
^

ALTER PROCEDURE F_GET_AMORTRATE (
    AMCODE_ID INTEGER,
    AMONTH INTEGER)
RETURNS (
    RATE DOUBLE PRECISION)
AS
DECLARE VARIABLE ANS INTEGER;
DECLARE VARIABLE CURMONTH INTEGER;
DECLARE VARIABLE CURRATE DOUBLE PRECISION;
begin
  ans = dm('*** Start F_GET_AMORTRATE');
  ans = wi('ACodeId', amcode_id);
  ans = wi('AMonth', AMonth);
  select n.amrate from acc_amrate n
    where (n.amcode_id = :amcode_id) and 
           n.ACTMONTH in (select max(n1.ACTMONTH) from acc_amrate n1
                          where (n1.amcode_id = :amcode_id))
    into :Rate;
  ans = wf('MaxRate', 15, 4, Rate);
  if (Rate is null) then Rate = 0;
  for select n.ACTMONTH, n.amrate from acc_amrate n
        where (n.amcode_id = :amcode_id)
        order by n.ACTMONTH desc
        into :CurMonth, :CurRate
  do begin
    if (CurMonth >= AMonth) then Rate = CurRate;
    ans = wi('Month', CurMonth);
    ans = wf('Rate', 15, 4, Rate);
  end
  ans = dm('*** Finish F_GET_AMORTRATE');
end
^

ALTER PROCEDURE F_GET_ANLPLAN_PARAMS (
    ANLPLAN_ID INTEGER,
    TYPE_PARAMS VARCHAR(20))
RETURNS (
    RESULT VARCHAR(80))
AS
begin
  /* Procedure Text */
  TYPE_PARAMS = upper(:TYPE_PARAMS collate pxw_cyrl);
  
  if (type_params='CODE') then
     select anl.code
     from anlplan anl
     where anl.id=:anlplan_id
     into :result;
  if (type_params='NAME') then
     select anl.anlname
     from anlplan anl
     where anl.id=:anlplan_id
     into :result;
  
  suspend;
end
^

ALTER PROCEDURE F_GET_CATALOG_PARAMS (
    CATALOG_ID INTEGER,
    TYPE_PARAMS VARCHAR(20))
RETURNS (
    RESULT VARCHAR(80))
AS
begin
  if (type_params='code') then 
     select cat.code
     from catalog cat
     where cat.id=:catalog_id
     into :result;
  if (type_params='name') then 
     select cat.cname
     from catalog cat
     where cat.id=:catalog_id
     into :result;
  if (type_params='meas') then 
     select m.code
     from catalog cat
          left join measure m on m.id=cat.measure1_id
     where cat.id=:catalog_id
     into :result;
  suspend;
end
^

ALTER PROCEDURE F_GET_CATALOG_PRICE (
    CATALOG_ID INTEGER,
    OPERDATE DATE,
    CURCODE_IN VARCHAR(5))
RETURNS (
    CURCODE_OUT VARCHAR(5),
    COSTNAT NUMERIC(15,4),
    COSTCUR NUMERIC(15,4))
AS
begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 14.12.2000
 Измененено:
 Назначение: Возвращает учетную цену позиции КТУ
**********************************************************/
  /**/
   curcode_in = upper(:curcode_in collate pxw_cyrl);
    if (curcode_in is not null) then begin
      select cp.price, cp.equivalent_price, cp.currency_code
      from catalogprice cp
      where (cp.catalog_id = :catalog_id) and (cp.in_action = (select max(cp1.in_action) from catalogprice cp1 where (cp1.catalog_id = :catalog_id) and (cp1.currency_code = :curcode_in) and (cp1.in_action <= :operdate)))
      into :costnat, :costcur, curcode_out;
    end
    else begin
      select cp.price from catalogprice cp
      where (cp.catalog_id = :catalog_id) and (cp.in_action = (select max(cp1.in_action) from catalogprice cp1 where (cp1.catalog_id = :catalog_id) and (cp1.in_action <= :operdate)))
      into :costnat;
      costcur = costnat;
    end
    if (costnat is null) then costnat = 0;
    if (costcur is null) then costcur = 0;
    suspend;
end
^

ALTER PROCEDURE F_GET_CATALOG_TAXRATE (
    TAXFORM SMALLINT,
    CATALOG_ID INTEGER)
RETURNS (
    RESULT NUMERIC(15,4))
AS
begin
  select t.directrate
    from (catalog c left join taxlink tl on tl.taxgroup_id=c.taxgroup_id)
         left join tax t on t.id=tl.tax_id
    where (c.id = :catalog_id) and
          (t.taxform = :taxform)
    into :result;
  if (result is null) then result = 0;      
  suspend;
end
^

ALTER PROCEDURE F_GET_CONTRACTOR_PARAMS (
    CONTRACTOR INTEGER,
    TYPE_PARAMS VARCHAR(20))
RETURNS (
    RESULT VARCHAR(80))
AS
begin
  /* Procedure Text */
  TYPE_PARAMS = upper(TYPE_PARAMS collate pxw_cyrl);
  if (type_params = upper('code' collate pxw_cyrl)) then
     select c.code
     from contractor c
     where c.id=:contractor
     into :result;
  if (type_params = upper('name' collate pxw_cyrl)) then
     select c.cname
     from contractor c
     where c.id=:contractor
     into :result;
  suspend;
end
^

ALTER PROCEDURE F_GET_CURRCOURCE_TO_CURRCOURCE (
    CURR_CODE_FROM VARCHAR(5),
    TYPE_COURCE_FROM SMALLINT,
    CURR_CODE_TO VARCHAR(5),
    TYPE_COURCE_TO SMALLINT,
    DATE_CONV DATE)
RETURNS (
    RESULT NUMERIC(15,4))
AS
DECLARE VARIABLE COURCE_FROM NUMERIC(15,4);
DECLARE VARIABLE COURCE_TO NUMERIC(15,4);
begin
  select cr.rate from ref_currency_rate cr
  where ((cr.currency_code1 = :CURR_CODE_FROM) and (cr.currency_code2 = :CURR_CODE_TO) and
    (cr.rate_authority_id = 1) and (cr.rate_type_id = :TYPE_COURCE_FROM) and
    (cr.effective_date =
    (select max(cr1.effective_date) from ref_currency_rate cr1 where ((cr1.effective_date <= :DATE_CONV) and
      (cr1.currency_code1 = :CURR_CODE_FROM) and (cr1.currency_code2 = :CURR_CODE_TO) and
      (cr1.rate_authority_id = 1) and (cr1.rate_type_id = :TYPE_COURCE_TO)))))
  into :result;
  suspend;
end
^

ALTER PROCEDURE F_GET_CURRENCYCOURCE (
    CURRENCYCODE CHAR(5),
    RATE_DATE TIMESTAMP,
    RATE_TYPE INTEGER,
    RATE_AUTHORITY INTEGER)
RETURNS (
    RESULT NUMERIC(15,5))
AS
declare variable nat_currency char(5);
begin
  select c.nat_currency
  from acc_config c
  into :nat_currency;

  select cr.rate from ref_currency_rate cr
  where ((cr.currency_code1 = :nat_currency) and (cr.currency_code2 = :CURRENCYCODE) and
    (cr.rate_authority_id = :rate_authority) and (cr.rate_type_id = :rate_type) and
    (cr.effective_date =
    (select max(cr1.effective_date) from ref_currency_rate cr1 where ((cr1.effective_date <= :rate_date) and
      (cr1.currency_code1 = :nat_currency) and (cr1.currency_code2 = :CURRENCYCODE) and
      (cr1.rate_authority_id = :rate_authority) and (cr1.rate_type_id = :rate_type)))))
  into :result;
  suspend;
end
^

ALTER PROCEDURE F_GET_CURRENCYCOURCEAVG (
    CURRENCYCODE CHAR(5),
    COURCEBEGINDATE TIMESTAMP,
    COURCEENDDATE TIMESTAMP,
    COURTYPE SMALLINT)
RETURNS (
    RESULT NUMERIC(15,5))
AS
begin
  select avg(cr.rate) from ref_currency_rate cr
  where ((cr.currency_code1 = :CURRENCYCODE) and (cr.currency_code2 = 'РУБ') and
    (cr.rate_authority_id = 1) and (cr.rate_type_id = :COURTYPE) and
    (cr.effective_date between :CourceBeginDate and :CourceEndDate))
  into :result;
  suspend;
end
^

ALTER PROCEDURE F_GET_DOCSUM (
    DOCTYPE CHAR(15),
    DOCNUMBER CHAR(20),
    DOCDATE TIMESTAMP)
RETURNS (
    SUMNAT NUMERIC(15,4),
    SUMCUR NUMERIC(15,4))
AS
begin
  select sum(d.summanat), sum(d.summacur)
  from dochead d
  where (d.doctype = :doctype) and
    (d.docnumber = :docnumber) and
    (d.docdate = :docdate)
  into :sumnat, :sumcur;
  suspend;
end
^

ALTER PROCEDURE F_GET_DOCSUM_WITHOUT_DISCOUNT (
    SECTIONDOC SMALLINT,
    DOCUMENT_ID INTEGER)
RETURNS (
    RESULT NUMERIC(15,4))
AS
begin
  /* StockDocument */
  if (sectiondoc between 16 and 17) then begin
    select sum(summa1) from docspec where dochead_id = :document_id
    into :result;
    suspend; Exit;
  end
  else begin
    result = 0;
    suspend;
  end
end
^

ALTER PROCEDURE F_GET_DOCUMENT_SUMMA (
    SECTIONDOC SMALLINT,
    DOCUMENT_ID INTEGER)
RETURNS (
    RESULT NUMERIC(15,4))
AS
begin
  select summanat from dochead where id = :document_id
  into :result;
  suspend;
end
^

ALTER PROCEDURE F_GET_DOCUMENT_SUMNDS (
    SECTIONDOC SMALLINT,
    DOCUMENT_ID INTEGER,
    NUM INTEGER)
RETURNS (
    RESULT NUMERIC(15,4))
AS
begin
  /* BankDocument */
  if (sectiondoc between 2 and 4) then begin
    if (Num = 1) then 
      select nds1summa from bankdocument where dochead_id = :document_id
      into :result;
    else
      select nds2summa from bankdocument where dochead_id = :document_id
      into :result;
    suspend;
  end
end
^

ALTER PROCEDURE F_GET_DOCUMENTMODEL (
    SECTIONDOC SMALLINT,
    DOCUMENTMODEL_ID INTEGER)
RETURNS (
    MODELNAME VARCHAR(80),
    FOLDER_ID INTEGER)
AS
begin
  /* EconomicOper */
  if (sectiondoc = 1) then begin
    select e.modelname, e.folder_id from economicopermodel e
    where (e.id = :documentmodel_id)
    into :modelname, :folder_id;
    suspend;
    Exit;
  end
  else if (sectiondoc = 20) then begin
    select fo.modelname, fo.folder_id from finopermodel fo
    where (fo.id = :documentmodel_id)
    into :modelname, :folder_id;
    suspend;
    Exit;
  end
  else if (sectiondoc = 13000) then begin
    select l.modelname, l.folder_id from pmc_liability l
    where (l.id = :documentmodel_id)
    into :modelname, :folder_id;
    suspend;
    Exit;
  end
  else begin
    select dm.modelname, dm.folder_id from docheadmodel dm
    where (dm.id = :documentmodel_id)
    into :modelname, :folder_id;
    suspend;
    Exit;
  end
end
^

ALTER PROCEDURE F_GET_EMPLOYEE_BY_ORGUNIT (
    ORGUNIT_ID INTEGER)
RETURNS (
    EMPL_ID INTEGER,
    EMPL_CODE CHAR(20))
AS
begin
  for
    select c.id, c.code
    from contractor c, employees e
    where (c.folder_id = :orgunit_id) and (c.id = e.contractor_id) and
      (e.is_default = 1)
    order by c.id desc
    into :empl_id, :empl_code
  do begin
    suspend;
    exit;
  end
end
^

ALTER PROCEDURE F_GET_IN_COST_CATALOG_ID (
    CATALOG_ID INTEGER,
    FEATURE_ID INTEGER)
RETURNS (
    RESULT NUMERIC(15,4))
AS
DECLARE VARIABLE STOCK_ID INTEGER;
DECLARE VARIABLE MOL_ID INTEGER;
DECLARE VARIABLE PRICE_CURR_CODE VARCHAR(5);
DECLARE VARIABLE TEMP_PRICE NUMERIC(15,4);
DECLARE VARIABLE CURR_CODE_INCOME VARCHAR(5);
DECLARE VARIABLE EQUALSUM NUMERIC(15,4);
DECLARE VARIABLE PRICE NUMERIC(15,4);
begin
    for
      select sds.cost/sb.beginquan
      from stockcard stc
           left join stockbatch sb on sb.stockcard_id=stc.id
           left join stockbatchhistory sbh on sbh.stockbatch_id = sb.id
           left join docspec ds on ds.id = sbh.documentspec_id
           left join stockdocumentspec sds on sds.docspec_id = sbh.documentspec_id
           left join dochead d on d.id = sbh.document_id
           left join featurelink fl on
             (fl.rec_id = d.id) and (fl.feature_id = :feature_id) and
             (fl.class_id = (select c.id from sys_class c where c.bean_name='WAREHOUSE/WAREHOUSEDOCUMENTHEADIN'))
      where (stc.catalog_id= :catalog_id) and
/*            (upper(sb.currency_code collate pxw_cyrl) = upper(:curr_code collate pxw_cyrl)) and**/
            (d.docsection = 16)
      order by sb.createdate desc
      into :result
    do begin
       if (result is null) then result = 0;
       suspend;
       exit;
    end
/*
    if (result is null) then begin
       result = 0;
       suspend;
    end*/
end
^

ALTER PROCEDURE F_GET_IN_COST_CATALOGID (
    CATALOGID INTEGER,
    FEATURE_ID INTEGER)
RETURNS (
    CENA_IN NUMERIC(15,4),
    CURR_CODE CHAR(5))
AS
DECLARE VARIABLE PRICE_COST NUMERIC(15,4);
DECLARE VARIABLE PRICE_BATCH NUMERIC(15,4);
DECLARE VARIABLE PRICE_DISC NUMERIC(15,4);
begin
    for
      select sds.cost/sb.beginquan, sb.pricecur, d.currency_code, sds.price_with_discount
      from stockcard stc
           left join stockbatch sb on sb.stockcard_id=stc.id 
           left join stockbatchhistory sbh on sbh.stockbatch_id = sb.id
           left join stockdocumentspec sds on sds.docspec_id = sbh.documentspec_id
           left join dochead d on d.id = sbh.document_id
           left join featurelink fl on
             (fl.rec_id = d.id) and (fl.feature_id = :feature_id) and
             (
               (fl.class_id = (select c.id from sys_class c where c.bean_name='WAREHOUSE/WAREHOUSEDOCUMENTHEADIN')) or
               (fl.class_id = (select c.id from sys_class c where c.bean_name='WAREHOUSE/WAREHOUSEDOCUMENTHEADOUT'))
             )
      where (stc.catalog_id = :catalogid) and (d.docsection in (16,17))
      order by sb.createdate desc
      into :price_cost, :price_batch, :curr_code, :price_disc
    do begin
       if (price_disc is null) then price_disc = 0;
       if (price_cost is null) then price_cost = 0;
       if (price_batch is null) then price_batch = 0;
       if ((price_cost <> 0) and (price_disc = 0)) then cena_in = price_cost;
       if ((price_cost = 0) and (price_batch <> 0)) then cena_in = price_batch;
       if ((price_cost <> 0) and (price_disc <> 0)) then cena_in = price_disc;
       if (cena_in is null) then cena_in = 0;
    suspend;
    exit;
  end
end
^

ALTER PROCEDURE F_GET_IN_COST_CATALOGID_RUB (
    CATALOGID INTEGER,
    FEATURE_ID INTEGER,
    DATE2 TIMESTAMP)
RETURNS (
    CENA_IN NUMERIC(15,4))
AS
DECLARE VARIABLE PRICE_COST NUMERIC(15,4);
DECLARE VARIABLE PRICE_BATCH NUMERIC(15,4);
DECLARE VARIABLE PRICE_DISC NUMERIC(15,4);
begin
    for
      select (sds.cost/sb.beginquan)*d.curcource, sb.pricecur*d.curcource, sds.price_with_discount*d.curcource
      from stockcard stc
           left join stockbatch sb on sb.stockcard_id=stc.id
           left join stockbatchhistory sbh on sbh.stockbatch_id = sb.id
           left join stockdocumentspec sds on sds.docspec_id = sbh.documentspec_id
           left join dochead d on d.id = sbh.document_id
           left join featurelink fl on
             (fl.rec_id = d.id) and (fl.feature_id = :feature_id) and
             (
               (fl.class_id = (select c.id from sys_class c where c.bean_name='WAREHOUSE/WAREHOUSEDOCUMENTHEADIN')) or
               (fl.class_id = (select c.id from sys_class c where c.bean_name='WAREHOUSE/WAREHOUSEDOCUMENTHEADOUT'))
             )
      where (stc.catalog_id = :catalogid) and
          (sbh.processdate <= :date2) and
          (d.docsection in (16,17))
      order by sb.createdate desc
      into :price_cost, :price_batch, :price_disc
    do begin
       if (price_cost is null) then price_cost = 0;
       if (price_batch is null) then price_batch = 0;
       if (price_disc is null) then price_disc = 0;
       if ((price_cost <> 0) and (price_disc = 0)) then cena_in = price_cost;
       if ((price_cost = 0) and (price_batch <> 0)) then cena_in = price_batch;
       if ((price_cost <> 0) and (price_disc <> 0)) then cena_in = price_disc;
       if (cena_in is null) then cena_in = 0;
    suspend;
    exit;
  end
end
^

ALTER PROCEDURE F_GET_LAST_CATALOGPRICE (
    CAT_ID INTEGER)
RETURNS (
    PRICE NUMERIC(15,4))
AS
begin
  for
    select p.price
    from catalogprice p
    where p.catalog_id = :cat_id
    order by p.in_action desc
    into :price
  do begin
    suspend;
    exit;
  end
end
^

ALTER PROCEDURE F_GET_LAST_CATALOGPRICEDATE (
    CAT_ID INTEGER)
RETURNS (
    IN_ACTION_DATE TIMESTAMP)
AS
begin
  for
    select p.in_action
    from catalogprice p
    where p.catalog_id = :cat_id
    order by p.in_action desc
    into :in_action_date
  do begin
    suspend;
    exit;
  end
end
^

ALTER PROCEDURE F_GET_LAST_DOC_NUMBER (
    DOCTYPE CHAR(15),
    DOCSECTION SMALLINT,
    DOCDATE TIMESTAMP)
RETURNS (
    DOCNUMBER VARCHAR(20))
AS
begin
  for
    select dh.docnumber from dochead dh
    where (dh.doctype = :DocType) and (dh.docsection = :docsection) and
      (extract(year from dh.docdate) = extract(year from :docdate))
    order by dh.docnumber desc /* !!! */
    into :DocNumber
  do begin
    suspend;
    Exit;
  end
end
^

ALTER PROCEDURE F_GET_NALOG_FROM_DOCUMENT (
    DOC_ID INTEGER)
RETURNS (
    STR VARCHAR(120),
    NAME_0 VARCHAR(20),
    NAME_1 VARCHAR(20),
    NAME_2 VARCHAR(20),
    NAME_3 VARCHAR(20),
    SUM_0 NUMERIC(15,4),
    SUM_1 NUMERIC(15,4),
    SUM_2 NUMERIC(15,4),
    SUM_3 NUMERIC(15,4))
AS
declare variable docsection integer;
declare variable val varchar(80);
declare variable nalog_name varchar(80);
declare variable nalog_summa numeric(15,4);
declare variable I integer;

begin
/**********************************************************
 Автор: Арычков Денис Владимирович
 Создано: 26.02.2001
 Назначение: Возвращает наименование и суммы налогов, указанных
 в дополнительных характеристиках документов.
 Используется: Печать документа, этап документооборота
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 31.01.2003 Кривопустов Поле FEATURE.NAME увеличено до 80 симв.
**********************************************************/
/* name_0, sum_0 - НДС
   name_0, sum_0 - налог на ГСМ
   name_0, sum_0 - Акциз
   name_0, sum_0 - НП*/

  name_0 = '';
  name_1 = '';
  name_2 = '';
  name_3 = '';
  sum_0 = 0;
  sum_1 = 0;
  sum_2 = 0;
  sum_3 = 0;
  str = '';
  I = 1;

  select dh.docsection
  from cashdocument d
        join dochead dh on dh.id = d.dochead_id
  where d.dochead_id = :doc_id
  into :docsection;

  for
  select f.name, fl.val
  from featurelink fl, feature f, docsection ds
  where (f.id = fl.feature_id) and
        (ds.id = :docsection) and
        (fl.class_id = ds.class_id) and
        (fl.rec_id = :doc_id) and
        ((f.name like '%НДС%') or (f.name like '%НСП%') or
        (f.name like '%ГСМ%') or (f.name like '%Акциз%'))
  into :nalog_name, :val
  do begin
      nalog_summa = round4(str2double(val,','));
      if (nalog_name like '%НДС%') then begin
        name_0 = nalog_name;
        sum_0 = round4(nalog_summa);
        if (I = 1)  then str = 'В том числе ' || Rtrim(name_0) || ' ' || val || ' р.; ';
        else str = str || Rtrim(name_0) || ' ' || val || ' р.; ';
      end
      if (nalog_name like '%ГСМ%') then begin
        name_1 = nalog_name;
        sum_1 = nalog_summa;
        if (I = 1)  then str = 'В том числе ' || Rtrim(name_1) || ' ' || val || ' р.; ';
        else str = str || Rtrim(name_1) || ' ' || val || ' р.; ';
      end
      if (nalog_name like '%Акциз%') then begin
        name_2 = nalog_name;
        sum_2 = nalog_summa;
        if (I = 1)  then str = 'В том числе ' || Rtrim(name_2) || ' ' || val || ' р.; ';
        else str = str || Rtrim(name_2) || ' ' || val || ' р.; ';
      end
      if (nalog_name like '%НСП%') then begin
        name_3 = nalog_name;
        sum_3 = nalog_summa;
        if (I = 1)  then str = 'В том числе ' || Rtrim(name_3) || ' ' || val || ' р.; ';
        else str = str || Rtrim(name_3) || ' ' || val || ' р.; ';
      end
      I = I + 1;
  end
  suspend;
end
^

ALTER PROCEDURE F_GET_NUM_MONTHS (
    INVENTORY_ID INTEGER,
    AMONTH INTEGER,
    INDATE TIMESTAMP,
    OUTDATE TIMESTAMP)
RETURNS (
    MONTHS INTEGER,
    NUMMONTHS INTEGER)
AS
declare variable ans integer;
declare variable LastMonth integer;
declare variable AbsInMonth integer;
declare variable InMonth integer;
declare variable InYear integer;
declare variable InDay integer;
declare variable OutMonth integer;
declare variable OutYear integer;
declare variable OutDay integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: расчет количества месяцев начисления амортизации
 Используется: процедурами начисления амортизации
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 21.03.2003 Кривопустов SQA 1491 (Система не отслеживает дату ввода в эксплуатацию при повторном начислении амортизации)
**********************************************************/
  ans = dm('*** Start F_GET_NUM_MONTHS');

  InYear = extract(year from InDate);
  InMonth = extract(month from InDate);
  InDay = extract(day from InDate);
  AbsInMonth = (InYear * 12) + InMonth;
  ans = wi('AMonth', AMonth);
  ans = wi('AbsInMonth', AbsInMonth);
  if (AbsInMonth >= AMonth) then begin
    Months = 0;
    NumMonths = 0;
    exit;
  end

  /* find last month */
  select max(a.imonth), sum(a.n_months) from ACC_AMORTIZATION a
    where (a.inventory_id = :inventory_id)
    into :LastMonth, :NumMonths;
  ans = wi('LastMonth', LastMonth);
  ans = wi('NumMonths', NumMonths);
  if ((LastMonth = 0) or (LastMonth is null)) then begin
    LastMonth = AbsInMonth;
    /*далее закомментировано 23.03.2001,
    начислять амортизацию со СЛЕДУЮЩЕГО месяца !!! */
    /*if (InDay <= 15) then LastMonth = LastMonth - 1;*/
  end
  if (AbsInMonth > LastMonth) then LastMonth = AbsInMonth;
  /**/
  if (OutDate is not null) then begin
    OutYear = extract(year from OutDate);
    OutMonth = extract(month from OutDate);
    OutDay = extract(day from OutDate);
    if (OutDay <= 15) then AMonth = (OutYear * 12) + OutMonth - 1;
    else AMonth = (OutYear * 12) + OutMonth; 
  end
  /* calculate months number for depreciation */
  if (NumMonths is null) then NumMonths = 0;
  Months = AMonth - LastMonth;
  ans = wi('Months', Months);
  if (Months < 0) then Months = 0;
  ans = dm('*** Finish F_GET_NUM_MONTHS');
end
^

ALTER PROCEDURE F_GET_OBDB (
    ACCDB VARCHAR(255),
    ACCKT CHAR(20),
    DATE1 DATE,
    DATE2 DATE,
    SPECMARK CHAR(20))
RETURNS (
    RESULT NUMERIC(15,4))
AS
begin
  select turn from obdb(:ACCDB, :ACCKT, :DATE1, :DATE2, :SPECMARK)
    into :RESULT; 
  suspend;
end
^

ALTER PROCEDURE F_GET_OBDB_DETAIL (
    ACC_DB INTEGER,
    ANL_DB1 INTEGER,
    ANL_DB2 INTEGER,
    ANL_DB3 INTEGER,
    ANL_DB4 INTEGER,
    ANL_DB5 INTEGER,
    ACC_KT INTEGER,
    ANL_KT1 INTEGER,
    ANL_KT2 INTEGER,
    ANL_KT3 INTEGER,
    ANL_KT4 INTEGER,
    ANL_KT5 INTEGER,
    CONTR_ID INTEGER,
    CATALOG_ID INTEGER,
    DATE1 TIMESTAMP,
    DATE2 TIMESTAMP)
RETURNS (
    RESULT NUMERIC(15,4))
AS
begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 22.12.00
 Измененено: Константин Кривопустов, 02.08.2001
 Назначение: Расчет оборота по дебету счета в корреспонденции
 с другим счетом за произвольный период
 со следующими дополнительными параметрами:
 - аналитика счета Дт
 - аналитика счета Кт
 - сотрудник, партнер, подразделение
 - ТМЦ
**********************************************************/
  /**/
  select turnnatdb
  from obdb_detail(:ACC_DB, :ANL_DB1, :ANL_DB2, :ANL_DB3, :ANL_DB4, :ANL_DB5,
                   :ACC_KT, :ANL_KT1, :ANL_KT2, :ANL_KT3, :ANL_KT4, :ANL_KT5,
                   :contr_id, :CATALOG_ID, :DATE1, :DATE2)
    into :RESULT; 
  suspend;
end
^

ALTER PROCEDURE F_GET_OBKT (
    ACCKT VARCHAR(255),
    ACCDB CHAR(20),
    DATE1 DATE,
    DATE2 DATE,
    SPECMARK CHAR(20))
RETURNS (
    RESULT NUMERIC(15,4))
AS
begin
  select turn from obkt(:ACCKT, :ACCDB, :DATE1, :DATE2, :SPECMARK)
    into :RESULT; 
  suspend;
end
^

ALTER PROCEDURE F_GET_OBKT_DETAIL (
    ACC_KT INTEGER,
    ANL_KT1 INTEGER,
    ANL_KT2 INTEGER,
    ANL_KT3 INTEGER,
    ANL_KT4 INTEGER,
    ANL_KT5 INTEGER,
    ACC_DB INTEGER,
    ANL_DB1 INTEGER,
    ANL_DB2 INTEGER,
    ANL_DB3 INTEGER,
    ANL_DB4 INTEGER,
    ANL_DB5 INTEGER,
    CONTR_ID INTEGER,
    CATALOG_ID INTEGER,
    DATE1 TIMESTAMP,
    DATE2 TIMESTAMP)
RETURNS (
    RESULT NUMERIC(15,4))
AS
begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 22.12.00
 Измененено: Константин Кривопустов, 06.12.2001
 Назначение: Расчет оборота по кредиту счета в корреспонденции
 с другим счетом за произвольный период
 со следующими дополнительными параметрами:
 - аналитика счета Дт
 - аналитика счета Кт
 - сотрудник, партнер, подразделение
 - ТМЦ
**********************************************************/
  /**/
  select turnnatkt
  from obkt_detail(:ACC_KT, :ANL_KT1, :ANL_KT2, :ANL_KT3, :ANL_KT4, :ANL_KT5,
                   :ACC_DB, :ANL_DB1, :ANL_DB2, :ANL_DB3, :ANL_DB4, :ANL_DB5,
                   :contr_id, :CATALOG_ID, :DATE1, :DATE2)
    into :RESULT; 
  suspend;
end
^

ALTER PROCEDURE F_GET_OSDB (
    ACC VARCHAR(255),
    ADATE DATE,
    ONBEGINDAY SMALLINT)
RETURNS (
    RESULT NUMERIC(15,4))
AS
begin
  select REMN from osdb(:ACC, :ADATE, :ONBEGINDAY)
    into :RESULT; 
  suspend;
end
^

ALTER PROCEDURE F_GET_OSDB_DETAIL (
    ACC INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    CONTR_ID INTEGER,
    CATALOG_ID INTEGER,
    ADATE TIMESTAMP,
    ONBEGINDAY SMALLINT)
RETURNS (
    RESULT NUMERIC(15,4))
AS
begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 22.12.00
 Измененено: Константин Кривопустов, 02.08.2001
 Назначение: Расчет дебетового остатка по счету
 со следующими дополнительными параметрами:
 - аналитика счета
 - сотрудник, партнер, подразделение
 - ТМЦ
 - на начало (1) и на конец дня (0)
**********************************************************/
  select REMNNATDB
  from osdb_detail(:ACC, :ANL1, :ANL2, :ANL3, :ANL4, :ANL5,
                   :contr_id, :CATALOG_ID, :ADATE, :ONBEGINDAY)
    into :RESULT; 
  suspend;
end
^

ALTER PROCEDURE F_GET_OSKT (
    ACC VARCHAR(255),
    ADATE DATE,
    ONBEGINDAY SMALLINT)
RETURNS (
    RESULT NUMERIC(15,4))
AS
begin
  select REMN from oskt(:ACC, :ADATE, :ONBEGINDAY)
    into :RESULT; 
  suspend;
end
^

ALTER PROCEDURE F_GET_OSKT_DETAIL (
    ACC INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    CONTR_ID INTEGER,
    CATALOG_ID INTEGER,
    ADATE TIMESTAMP,
    ONBEGINDAY SMALLINT)
RETURNS (
    RESULT NUMERIC(15,4))
AS
begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 22.12.00
 Измененено:
 Назначение: Расчет кредитового остатка по счету
 со следующими дополнительными параметрами:
 - аналитика счета
 - сотрудник, партнер, подразделение
 - ТМЦ
 - на начало (1) и на конец дня (0)
**********************************************************/
  select REMNNATKT
  from oskt_detail(:ACC, :ANL1, :ANL2, :ANL3, :ANL4, :ANL5,
                   :contr_id, :CATALOG_ID, :ADATE, :ONBEGINDAY)
    into :RESULT; 
  suspend;
end
^

ALTER PROCEDURE F_GET_OUT_COST_STOCKCARD_CURR (
    STOCK_CODE VARCHAR(20),
    MOL_CODE VARCHAR(20),
    CATALOG_ID INTEGER)
RETURNS (
    PRICE NUMERIC(15,2),
    CURRENCY_CODE VARCHAR(5))
AS
declare variable stock_id integer;
declare variable stock_policy integer;
declare variable mol_id integer;
begin
  select c.id, wh.stockpolicy
  from wh_warehouse wh, contractor c
  where (c.id = wh.contractor_id) and (c.upcode=upper(:STOCK_CODE collate pxw_cyrl))
  into :stock_id, :stock_policy;
    
  select e.id
  from contractor e
  where e.upcode=upper(:MOL_CODE collate pxw_cyrl)
  into :mol_id; 

  if (stock_policy=0) then begin 
    for 
      select sb.pricecur,
             upper (sb.currency_code collate pxw_cyrl) currency_code
      from stockcard stc
           left join stockbatch sb on sb.stockcard_id=stc.id 
      where (stc.stock_id= :stock_id) and
            (stc.mol_id= :mol_id) and 
            (stc.catalog_id= :catalog_id) and
            (sb.endquan<>0)     
      order by sb.createdate
      into :price, :currency_code
    do begin
      suspend;
      exit;
    end
  end

  if (stock_policy=1) then begin 
    for 
      select sb.pricecur,
             upper (sb.currency_code collate pxw_cyrl) currency_code
      from stockcard stc
           left join stockbatch sb on sb.stockcard_id=stc.id 
      where (stc.stock_id= :stock_id) and
            (stc.mol_id= :mol_id) and 
            (stc.catalog_id= :catalog_id) and
            (sb.endquan<>0)     
      order by sb.createdate desc
      into :price, :currency_code
    do begin
      suspend;
      exit;
    end
  end
end
^

ALTER PROCEDURE F_GET_OUT_COST_STOCKCARD_PRICE (
    STOCK_CODE VARCHAR(20),
    MOL_CODE VARCHAR(20),
    PRICELISTSPEC_ID INTEGER)
RETURNS (
    RESULT NUMERIC(15,8))
AS
declare variable catalog_id integer;
declare variable price_curr_code varchar (5);
declare variable temp_price numeric (15,8);
declare variable curr_code_income varchar (5);
declare variable equalsum numeric (15,8);
begin
  select prs.catalog_id, prh.currency_code
  from pricelistspec prs
       join pricelistfolder prf on prs.folder_id=prf.id
       join pricelisthead prh on prf.pricelisthead_id=prh.id 
  where prs.id=:PRICELISTSPEC_ID
  into :catalog_id, price_curr_code;
  
  select f.price, f.currency_code
  from f_get_out_cost_stockcard_curr(:STOCK_CODE, :MOL_CODE, :catalog_id) f
  into :temp_price, :curr_code_income;  
  
  select f.result
  from f_get_currcource_to_currcource(:curr_code_income, 0,:price_curr_code, 0,'today') f
  into :equalsum; 

  result=temp_price * equalsum; 
  suspend;
end
^

ALTER PROCEDURE F_GET_PRICE_PRICETYPE (
    PRICE_TYPE_CODE VARCHAR(20),
    PRICELISTSPEC_ID INTEGER)
RETURNS (
    RESULT NUMERIC(15,2))
AS
declare variable pricetype_id integer;
begin
  select prt.id
  from pricetype prt
  where upper(prt.code collate pxw_cyrl)= upper(:PRICE_TYPE_CODE collate pxw_cyrl)
  into :pricetype_id;
  
  select prlp.price
  from pricelistspec_price prlp
  where (prlp.pricelistspec_id=:PRICELISTSPEC_ID) and
        (prlp.pricetype_id=:pricetype_id)
  into :result;

  if (result is null) then result = 0;
        
  suspend;
end
^

ALTER PROCEDURE F_GET_PRODUCTVOLUME (
    AMCODE_ID INTEGER,
    AMONTH INTEGER)
RETURNS (
    VOL DOUBLE PRECISION)
AS
DECLARE VARIABLE ANS INTEGER;
DECLARE VARIABLE CURMONTH INTEGER;
DECLARE VARIABLE CURVOL DOUBLE PRECISION;
begin
  ans = dm('*** Start F_GET_PRODUCTVOLUME');
  ans = wi('ACode', AMCODE_ID);
  ans = wi('AMonth', AMonth);
  select n.VOLUMEPROD from acc_amrate n
    where (n.AMCODE_ID = :AMCODE_ID) and 
           n.ACTMONTH in (select max(n1.ACTMONTH) from acc_amrate n1
                          where (n1.AMCODE_ID = :AMCODE_ID))
    into :Vol;
  ans = wf('MaxVol', 15, 4, Vol);
  if (Vol is null) then Vol = 0;
  for select n.ACTMONTH, n.VOLUMEPROD from acc_amrate n
        where (n.AMCODE_ID = :AMCODE_ID)
        order by n.ACTMONTH desc
        into :CurMonth, :CurVol
  do begin
    if (CurMonth >= AMonth) then Vol = CurVol;
    ans = wi('Month', CurMonth);
    ans = wf('Vol', 15, 4, Vol);
  end
  ans = dm('*** Finish F_GET_PRODUCTVOLUME');
end
^

ALTER PROCEDURE F_GET_SPECIFICATION_QUAN (
    SECTIONDOC SMALLINT,
    SPECIFICATION_ID INTEGER)
RETURNS (
    RESULT DOUBLE PRECISION)
AS
begin
  select quantity from docspec where id = :specification_id
  into :result;
  suspend;
end
^

ALTER PROCEDURE F_GET_SPECIFICATION_SUMM (
    SECTIONDOC SMALLINT,
    SPECIFICATION_ID INTEGER)
RETURNS (
    RESULT DOUBLE PRECISION)
AS
begin
  select summa from docspec where id = :specification_id
  into :result;
  suspend;
end
^

ALTER PROCEDURE F_GET_SPECSUM_WITHOUT_DISCOUNT (
    SECTIONDOC SMALLINT,
    SPECIFICATION_ID INTEGER)
RETURNS (
    RESULT NUMERIC(15,4))
AS
begin
  /* StockDocument */
  if (sectiondoc between 16 and 17) then begin
    select summa1 from docspec where id = :specification_id
    into :result;
    suspend; Exit;
  end
  else begin
    result = 0;
    suspend;
  end
end
^

ALTER PROCEDURE F_GET_TAX_SUMM (
    SECTIONDOC SMALLINT,
    SPECIFICATION_ID INTEGER,
    TAX_CODE CHAR(20))
RETURNS (
    RESULT DOUBLE PRECISION)
AS
begin
/**********************************************************
 Автор: Олег Сафонов, Константин Кривопустов
 Создано: давно
 Измененено: 17.12.2001
 Назначение: сумма налога в спецификации документа
 Используется: сервером приложения
**********************************************************/
  select ts.summ from taxsumm ts, tax t
  where (ts.specification_id = :specification_id) and (ts.tax_id = t.id) and 
    (t.upcode = :tax_code)
  into :result;
  if (result is null)  then result=0;
  suspend;
end
^

ALTER PROCEDURE F_GET_TAXES_SUM_FOR_BANKDOC (
    SECTIONSRC SMALLINT,
    SRC_ID INTEGER,
    DIRECTRATE NUMERIC(15,5))
RETURNS (
    DOC_SUM NUMERIC(15,4),
    TAX_SUM NUMERIC(15,4))
AS
begin
/**********************************************************
 Автор: Олег Сафонов, Константин Кривопустов
 Создано: давно
 Измененено: 10.07.2001, 17.12.2001
 Назначение: суммы налогов для создания банк.документа
 Используется: сервером приложения
**********************************************************/
  select d.summacur from dochead d
  where d.id = :src_id into :doc_sum;

  select sum(ts.summ)
  from taxsumm ts, tax t, docspec ds 
  where (ts.tax_id = t.id) and (ts.specification_id = ds.id) and
    (ds.dochead_id = :src_id) and
    (t.directrate = :directrate)
  into :tax_sum;

  suspend;
end
^

ALTER PROCEDURE F_GET_WAREHOUSE_PLAN_IN (
    WARECARD_ID INTEGER)
RETURNS (
    SYSDATETIME TIMESTAMP,
    PROCESSDATE TIMESTAMP,
    FROM_CODE CHAR(20),
    TO_CODE CHAR(20),
    QUANTITY NUMERIC(15,3),
    QUANTITY2 NUMERIC(15,3),
    DOCTYPE CHAR(15),
    DOCNUMBER CHAR(20),
    DOCDATE TIMESTAMP,
    DOCUMENT_ID INTEGER,
    DOCSECTION INTEGER,
    BASEDOCTYPE CHAR(15),
    BASEDOCNUMBER CHAR(20),
    BASEDOCDATE TIMESTAMP)
AS
declare variable qty numeric(15,3);
declare variable qty2 numeric(15,3);
begin
/**********************************************************
   Автор: Константин Кривопустов
   Создано: 25.05.2001
   Назначение: планируемый приход по скл.карточке.
   Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 18.06.2001 Кривопустов
 09.12.2002 Кривопустов Добавлены поля document_id, docsection
 28.01.2003 Кривопустов Добавлены поля в рез.набор
**********************************************************/
  for
    select h.sysdatetime, h.processdate,
      h.quantity, h.quantity2, d.doctype, d.docnumber, d.docdate, d.id, d.docsection,
      d.basedoctype, d.basedocnumber, d.basedocdate, c1.code from_code, c2.code to_code
    from stockplanhistory h
      left join dochead d on d.id = h.dochead_id
      left join contractor c1 on c1.id = d.from_id
      left join contractor c2 on c2.id = d.to_id
    where (h.stockcard_id = :warecard_id) and
     (h.kind = 0) and (h.direction = 0)
    order by h.processdate
    into :sysdatetime,:processdate,
      :quantity,:quantity2,:doctype,:docnumber,:docdate,:document_id,:docsection,
      :basedoctype, :basedocnumber, :basedocdate, :from_code, :to_code
  do begin
    qty = 0;
    qty2 = 0;
    select sum(h1.quantity), sum(h1.quantity2) from stockplanhistory h1
    where (h1.stockcard_id = :warecard_id) and
          (h1.kind = 1) and (h1.direction = 0) and
          (h1.dochead_id = :document_id)
    into :qty, :qty2;
    if (qty is null) then qty = 0;
    if (qty2 is null) then qty2 = 0;

    quantity = quantity - qty;
    quantity2 = quantity2 - qty2;
    if ((quantity > 0) or (quantity2 > 0)) then
      suspend;
  end
end
^

ALTER PROCEDURE F_GET_WAREHOUSE_PLAN_OUT (
    WARECARD_ID INTEGER)
RETURNS (
    SYSDATETIME TIMESTAMP,
    PROCESSDATE TIMESTAMP,
    FROM_CODE CHAR(20),
    TO_CODE CHAR(20),
    QUANTITY NUMERIC(15,3),
    QUANTITY2 NUMERIC(15,3),
    DOCTYPE CHAR(15),
    DOCNUMBER CHAR(20),
    DOCDATE TIMESTAMP,
    DOCUMENT_ID INTEGER,
    DOCSECTION INTEGER,
    BASEDOCTYPE CHAR(15),
    BASEDOCNUMBER CHAR(20),
    BASEDOCDATE TIMESTAMP)
AS
declare variable qty numeric(15,3);
declare variable qty2 numeric(15,3);
begin
/**********************************************************
   Автор: Константин Кривопустов
   Создано: 25.05.2001
   Назначение: планируемый расход по скл.карточке.
   Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 18.06.2001 Кривопустов
 09.12.2002 Кривопустов Добавлены поля document_id, docsection
 28.01.2003 Кривопустов Добавлены поля в рез.набор
**********************************************************/
  for
    select h.sysdatetime, h.processdate,
      h.quantity, h.quantity2, d.doctype, d.docnumber, d.docdate, d.id, d.docsection,
      d.basedoctype, d.basedocnumber, d.basedocdate, c1.code from_code, c2.code to_code
    from stockplanhistory h
      left join dochead d on d.id = h.dochead_id
      left join contractor c1 on c1.id = d.from_id
      left join contractor c2 on c2.id = d.to_id
    where (h.stockcard_id = :warecard_id) and
     (h.kind = 0) and (h.direction = 1)
    order by h.processdate
    into :sysdatetime,:processdate,
      :quantity,:quantity2,:doctype,:docnumber,:docdate,:document_id,:docsection,
      :basedoctype, :basedocnumber, :basedocdate, :from_code, :to_code
  do begin
    qty = 0;
    qty2 = 0;
    select sum(h1.quantity), sum(h1.quantity2) from stockplanhistory h1
    where (h1.stockcard_id = :warecard_id) and
          (h1.kind = 1) and (h1.direction = 1) and
          (h1.dochead_id = :document_id)
    into :qty, :qty2;
    if (qty is null) then qty = 0;
    if (qty2 is null) then qty2 = 0;

    quantity = quantity - qty;
    quantity2 = quantity2 - qty2;
    if ((quantity > 0) or (quantity2 > 0)) then
      suspend;
  end
end
^

ALTER PROCEDURE F_INC_DATE (
    ADATE TIMESTAMP,
    ADAY INTEGER,
    AMONTH INTEGER,
    AYEAR INTEGER)
RETURNS (
    RESULT TIMESTAMP)
AS
declare variable d integer;
declare variable m integer;
declare variable y integer;
declare variable days integer;
/*declare variable a integer;*/
begin
  /*a = dm('Enter F_INC_DATE');*/
  d = extract(day from :adate);
  m = extract(month from :adate);
  y = extract(year from :adate);
  y = y + ayear;
  y = y + Div(amonth, 12);
  m = m + amonth - Div(amonth, 12) * 12;
  if (m < 1) then begin
    m = m + 12;
    y = y - 1;
  end
  else if (m > 12) then begin
    m = m - 12;
    y = y + 1;
  end
  days = DaysPerMonth(m, y);
  if (d > days) then d = days;
  execute procedure encodedate(:y, :m, :d) returning_values :result;
  result = result + aday;
  /*a = dm('Exit F_INC_DATE');*/
  suspend;
end
^

ALTER PROCEDURE F_NESTED_CATFOLDERS (
    HEAD_ID INTEGER)
RETURNS (
    FOLDER_ID INTEGER)
AS
declare variable recurse smallint;
declare variable is_member smallint;
begin
  select c.recurse_catfolder from systemconfig c into :recurse;
  if (recurse = 0) then begin
    folder_id = head_id;
    suspend;
  end
  else
    for select f.id from catalogfolder f
      order by f.id
      into :folder_id
    do begin
      execute procedure f_catfold_is_branch_member(:folder_id, :head_id)
        returning_values :is_member;
      if (is_member = 1) then suspend;
    end
end
^

ALTER PROCEDURE F_NESTED_CATFOLDERS_ALL_RECURSE (
    HEAD_ID INTEGER)
RETURNS (
    FOLDER_ID INTEGER)
AS
declare variable recurse smallint;
declare variable is_member smallint;
begin
    for select f.id from catalogfolder f
      order by f.id
      into :folder_id
    do begin
      execute procedure f_catfold_is_branch_member(:folder_id, :head_id)
        returning_values :is_member;
      if (is_member = 1) then suspend;
    end
end
^

ALTER PROCEDURE F_NESTED_FOLDERS (
    HEAD_ID INTEGER,
    FOLDERTYPE SMALLINT)
RETURNS (
    FOLDER_ID INTEGER)
AS
declare variable recurse smallint;
declare variable is_member smallint;
begin
  select c.recurse_folder from systemconfig c into :recurse;
  if (recurse = 0) then begin
    folder_id = head_id;
    suspend;
  end
  else
    for select f.id from folder f
      where f.foldertype = :foldertype
      order by f.id
      into :folder_id
    do begin
      execute procedure f_fold_is_branch_member(:folder_id, :head_id, foldertype)
        returning_values :is_member;
      if (is_member = 1) then suspend;
    end
end
^

ALTER PROCEDURE F_NESTED_ORGUNITS (
    HEAD_ID INTEGER)
RETURNS (
    FOLDER_ID INTEGER)
AS
declare variable recurse smallint;
declare variable is_member smallint;
begin
  select c.recurse_orgunit from systemconfig c into :recurse;
  if (recurse = 0) then begin
    folder_id = head_id;
    suspend;
  end
  else
    for 
      select o.id 
      from contractor o 
      where o.kind = 1
      order by o.id
      into :folder_id
    do begin
      execute procedure f_orgunit_is_branch_member(:folder_id, :head_id)
        returning_values :is_member;
      if (is_member = 1) then suspend;
    end
end
^

ALTER PROCEDURE F_ORGUNIT_IS_BRANCH_MEMBER (
    FOLDER_ID INTEGER,
    BRANCHHEAD_ID INTEGER)
RETURNS (
    RESULT SMALLINT)
AS
declare variable foldparent_id integer;
begin
  if (folder_id = branchhead_id) then begin result = 1; Exit; end
  select o.folder_id from contractor o where o.id = :folder_id
  into :foldparent_id;
  if (foldparent_id is null) then begin
    result = 0; Exit;
  end
  else
    execute procedure f_orgunit_is_branch_member (:foldparent_id, branchhead_id)
    returning_values :result;
end
^

ALTER PROCEDURE F_OUTCOME_FROM_STOCK (
    ADATE TIMESTAMP,
    STOCK_ID INTEGER,
    STOCKCARD_ID INTEGER,
    STOCKBATCH_ID INTEGER,
    DOC_ID INTEGER,
    DOCSPEC_ID INTEGER,
    QUANTITY NUMERIC(18,6),
    QUANTITY2 NUMERIC(18,6),
    PREV_SBH_ID INTEGER,
    OWNER_ID INTEGER,
    STOCK_KIND_ID INTEGER)
RETURNS (
    STOCKBATCHHIST_ID INTEGER,
    END_QUAN NUMERIC(18,6))
AS
DECLARE VARIABLE ST_POLICY SMALLINT;
DECLARE VARIABLE SB_ID INTEGER;
DECLARE VARIABLE QUAN NUMERIC(18,6);
DECLARE VARIABLE QUAN2 NUMERIC(18,6);
begin
/**********************************************************
 Автор: Олег Сафонов
 Создано: давно
 Назначение: расход по складу
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 30.07.2004 Кривопустов Bugs 2385,2386. Новые атрибуты
                        партии - OWNER_ID, STOCK_KIND_ID
**********************************************************/
  /*prev_sbh_id = null;*/
  select stockpolicy from wh_warehouse where (contractor_id = :stock_id) into :st_policy;
  /* FIFO */
  if ((st_policy = 0) or ((st_policy = 2) and (stockbatch_id is null))) then begin
    for
      select sb.id, sb.endquan, sb.endquan2 from stockbatch sb
      where (sb.stockcard_id = :stockcard_id)and(sb.createdate <= :adate) and
            (sb.endquan <> 0) and
            ((sb.owner_id = :owner_id) or (:owner_id is null)) and
            ((sb.stock_kind_id = :stock_kind_id) or (:stock_kind_id is null))
      order by sb.createdate, sb.id asc
      into :sb_id, :quan, :quan2
    do begin
      if (quan < quantity) then
        quantity = quantity - quan;
      else begin
        quan = quantity;
        quantity = 0;
      end
      if (quan2 < quantity2) then
        quantity2 = quantity2 - quan2;
      else begin
        quan2 = quantity2;
        quantity2 = 0;
      end
      stockbatchhist_id = gen_id(stockbatchhistory_id_gen, 1);
      end_quan = quan;
      insert into stockbatchhistory(id, stockbatch_id, kind, document_id, documentspec_id, quantity, quantity2, prev_stockbatchhist_id, processdate)
      values(:stockbatchhist_id, :sb_id, 1, :doc_id, :docspec_id, :quan, :quan2, :prev_sbh_id, :adate);
      prev_sbh_id = stockbatchhist_id;   
      if ((quantity <= 0) and (quantity2 <= 0)) then begin
        suspend;
        Exit;
      end
    end
    exception E_IS_NOT_ENOUGH_QUAN_ON_STOCK;
  end
  else
  /* LIFO */
  if (st_policy = 1) then begin
    for
      select sb.id, sb.endquan, sb.endquan2 from stockbatch sb
      where (sb.stockcard_id = :stockcard_id)and(sb.createdate <= :adate) and
            (sb.endquan <> 0) and
            ((sb.owner_id = :owner_id) or (:owner_id is null)) and
            ((sb.stock_kind_id = :stock_kind_id) or (:stock_kind_id is null))
      /*order by sb.id, sb.createdate desc  //11.11.2001, comment by OVS, SQA № 569 */
      order by sb.createdate desc
      into :sb_id, :quan, :quan2
    do begin
      if (quan < quantity) then
        quantity = quantity - quan;
      else begin
        quan = quantity;
        quantity = 0;
      end
      if (quan2 < quantity2) then
        quantity2 = quantity2 - quan2;
      else begin
        quan2 = quantity2;
        quantity2 = 0;
      end
      stockbatchhist_id = gen_id(stockbatchhistory_id_gen, 1);
      end_quan = quan;
      insert into stockbatchhistory(id, stockbatch_id, kind, document_id, documentspec_id, quantity, quantity2, prev_stockbatchhist_id, processdate)
      values(:stockbatchhist_id, :sb_id, 1, :doc_id, :docspec_id, :quan, :quan2, :prev_sbh_id, :adate);
      prev_sbh_id = stockbatchhist_id;   
      if ((quantity <= 0) and (quantity2 <= 0)) then begin
        suspend;
        Exit;
      end
    end
    exception E_IS_NOT_ENOUGH_QUAN_ON_STOCK;
  end
  else
  /* Batch */
  if (st_policy = 2) then begin
    select sb.id, sb.endquan, sb.endquan2 from stockbatch sb where (sb.id = :stockbatch_id)
    into :sb_id, :quan, :quan2;
    if ((quan < quantity) or (quan2 < quantity2)) then exception E_IS_NOT_ENOUGH_QUAN_ON_STOCK;
    stockbatchhist_id = gen_id(stockbatchhistory_id_gen, 1);
    insert into stockbatchhistory(id, stockbatch_id, kind, document_id, documentspec_id, quantity, quantity2, prev_stockbatchhist_id, processdate)
    values(:stockbatchhist_id, :sb_id, 1, :doc_id, :docspec_id, :quantity, :quantity2, :prev_sbh_id, :adate);
    Suspend;
    Exit;
  end
end
^

ALTER PROCEDURE F_OVERESTIMATION_AP (
    ADATE TIMESTAMP,
    ACC INTEGER,
    ACCPROFIT INTEGER,
    ANLPR1_ID INTEGER,
    ANLPR2_ID INTEGER,
    ANLPR3_ID INTEGER,
    ANLPR4_ID INTEGER,
    ANLPR5_ID INTEGER,
    ACCLOSS INTEGER,
    ANLLOSS1_ID INTEGER,
    ANLLOSS2_ID INTEGER,
    ANLLOSS3_ID INTEGER,
    ANLLOSS4_ID INTEGER,
    ANLLOSS5_ID INTEGER,
    FOLDER_ID INTEGER,
    CURRATE_TYPE INTEGER,
    CURRATE_AUTHORITY INTEGER)
RETURNS (
    ECONOMICOPER_ID INTEGER)
AS
declare variable real_cource numeric(15,5);
declare variable cource numeric(15,5);
declare variable currency_code char(5);
declare variable remant_nat_db numeric(15,4);
declare variable remant_cur_db numeric(15,4);
declare variable remant_nat_kt numeric(15,4);
declare variable remant_cur_kt numeric(15,4);
declare variable rdb smallint;
declare variable rkt smallint;
declare variable overSum numeric(15,4);
declare variable overSumDb numeric(15,4);
declare variable overSumKt numeric(15,4);
declare variable accDb integer;
declare variable accKt integer;
declare variable acctype smallint;
declare variable anlform smallint;
declare variable ANLDB1_ID integer;
declare variable ANLDB2_ID integer;
declare variable ANLDB3_ID integer;
declare variable ANLDB4_ID integer;
declare variable ANLDB5_ID integer;
declare variable ANLKT1_ID integer;
declare variable ANLKT2_ID integer;
declare variable ANLKT3_ID integer;
declare variable ANLKT4_ID integer;
declare variable ANLKT5_ID integer;
begin
  overSumDb = 0; overSumKt = 0;
  select currency_code, acctype, anlform from accplan where id = :Acc
  into :currency_code, :acctype, :anlform;
  /* не переоцениваем Дб/Кр и материальные счета */
  if (((acctype > 1)and(acctype <> 5))or(anlform between 3 and 8)or(anlform between 11 and 14)) then Exit;
  execute procedure F_GET_CURRENCYCOURCE(:currency_code, :ADate, :currate_type, :currate_authority)
  returning_values :cource;
  execute procedure F_CALC_REMANTACC_ONDATE(:Acc, :ADate, 0)
  returning_values :remant_nat_db, :remant_cur_db, :remant_nat_kt, :remant_cur_kt;
  if (remant_cur_db <> 0) then begin
    real_cource = remant_nat_db / remant_cur_db; rdb = 1; rkt = 0;
    if (real_cource - cource <> 0) then overSumDb = remant_cur_db * cource - remant_nat_db;
  end
  else begin
    if (remant_cur_kt <> 0) then begin
      real_cource = remant_nat_kt / remant_cur_kt; rdb = 0; rkt = 1;
      if (real_cource - cource <> 0) then overSumKt = remant_cur_kt * cource - remant_nat_kt;
    end
    else Exit;
  end
  if (acctype = 0) then begin
    /* Active */
    if (rdb = 1) then begin
      overSum = overSumDb;
      if (remant_nat_db <> 0) then begin
        if (overSumDb > 0) then begin
          accDb = Acc; accKt = AccProfit;
        end
        else begin
          accDb = AccLoss; accKt = Acc;
        end
      end
      else begin
        if (overSumDb > 0) then begin
          accDb = Acc; accKt = AccProfit;
        end
        else begin
          accDb = AccLoss; accKt = Acc;
        end
      end
    end
    if (rkt = 1) then begin
      overSum = overSumKt;
      if (remant_nat_kt <> 0) then begin
        if (overSumKt > 0) then begin
          accDb = AccProfit; accKt = Acc;
        end
        else begin
          accDb = Acc; accKt = AccLoss;
        end
      end
      else begin
        if (overSumKt > 0) then begin
          accDb = Acc; accKt = AccProfit;
        end
        else begin
          accDb = AccLoss; accKt = Acc;
        end
      end
    end
  end
  else begin
    /* Passive, Zero */
    if (rdb = 1) then begin
      overSum = overSumDb;
      if (remant_nat_db <> 0) then begin
        if (overSumDb > 0) then begin
          accDb = Acc; accKt = AccLoss;
        end
        else begin
          accDb = AccProfit; accKt = Acc;
        end
      end
      else begin
        if (overSumDb > 0) then begin
          accDb = AccProfit; accKt = Acc;
        end
        else begin
          accDb = Acc; accKt = AccLoss;
        end
      end
    end
    if (rkt = 1) then begin
      overSum = overSumKt;
      if (remant_nat_kt <> 0) then begin
        if (overSumKt > 0) then begin
          accDb = AccProfit; accKt = Acc;
        end
        else begin
          accDb = Acc; accKt = AccLoss;
        end
      end
      else begin
        if (overSumKt > 0) then begin
          accDb = AccProfit; accKt = Acc;
        end
        else begin
          accDb = Acc; accKt = AccLoss;
        end
      end
    end
  end
  if (overSum = 0) then Exit;
  if (accDb = AccProfit) then begin
    ANLDB1_ID = ANLPR1_ID; ANLDB2_ID = ANLPR2_ID; ANLDB3_ID = ANLPR3_ID;
    ANLDB4_ID = ANLPR4_ID; ANLDB5_ID = ANLPR5_ID;
  end
  else begin
    if (accDb = AccLoss) then begin
      ANLDB1_ID = ANLLOSS1_ID; ANLDB2_ID = ANLLOSS2_ID; ANLDB3_ID = ANLLOSS3_ID;
      ANLDB4_ID = ANLLOSS4_ID; ANLDB5_ID = ANLLOSS5_ID;
    end
  end
  if (accKt = AccProfit) then begin
    ANLKT1_ID = ANLPR1_ID; ANLKT2_ID = ANLPR2_ID; ANLKT3_ID = ANLPR3_ID;
    ANLKT4_ID = ANLPR4_ID; ANLKT5_ID = ANLPR5_ID;
  end
  else begin
    if (accKt = AccLoss) then begin
      ANLKT1_ID = ANLLOSS1_ID; ANLKT2_ID = ANLLOSS2_ID; ANLKT3_ID = ANLLOSS3_ID;
      ANLKT4_ID = ANLLOSS4_ID; ANLKT5_ID = ANLLOSS5_ID;
    end
  end
  /* Insert EconomicOper */
  EconomicOper_ID = gen_id(EconomicOper_id_gen, 1);
  insert into economicoper(id, folder_id, keepdate, insertsign)
  values(:EconomicOper_ID, :FOLDER_ID, :ADate, 1);
  insert into economicspec(ID,ECONOMICOPER_ID,ACCDB_id,ANLDB1_ID,ANLDB2_ID,ANLDB3_ID,ANLDB4_ID,ANLDB5_ID,
    ACCKT_id,ANLKT1_ID,ANLKT2_ID,ANLKT3_ID,ANLKT4_ID,ANLKT5_ID,
    CATALOG_ID,QUANTITY,SUMMANAT,SUMMACUR,CURCOURCE, ACCBATCHDB_ID, ACCBATCHKT_ID,
    ACCBATCHHISTORYDB_ID, ACCBATCHHISTORYKT_ID)
  values
    (gen_id(economicspec_id_gen, 1),:EconomicOper_ID,:AccDb,:ANLDB1_ID,:ANLDB2_ID,:ANLDB3_ID,:ANLDB4_ID,:ANLDB5_ID,
    :AccKt,:ANLKT1_ID,:ANLKT2_ID,:ANLKT3_ID,:ANLKT4_ID,:ANLKT5_ID,null,0,Abs(:overSum),0,null, null, null,null, null);
  update economicoper set
    comment = 'Переоценка валютного актива / пассива',
    insertsign = 0
  where id = :EconomicOper_ID;
  suspend;
end
^

ALTER PROCEDURE F_QUAN_ON_STOCK (
    STOCK CHAR(20),
    MOL CHAR(20),
    CATALOG_ID INTEGER,
    USER_ID INTEGER)
RETURNS (
    FACT NUMERIC(15,3),
    PLAN_IN NUMERIC(15,3),
    PLAN_OUT NUMERIC(15,3),
    RESERVE NUMERIC(15,3),
    AVAILABLE NUMERIC(15,3),
    FACT2 NUMERIC(15,3),
    PLAN_IN2 NUMERIC(15,3),
    PLAN_OUT2 NUMERIC(15,3),
    RESERVE2 NUMERIC(15,3),
    AVAILABLE2 NUMERIC(15,3))
AS
declare variable calc_fact smallint;
declare variable calc_fact_sign smallint;
declare variable calc_plan_in smallint;
declare variable calc_plan_in_sign smallint;
declare variable calc_plan_out smallint;
declare variable calc_plan_out_sign smallint;
declare variable calc_reserve smallint;
declare variable calc_reserve_sign smallint;
declare variable stock_id integer;
declare variable tmp_available numeric(15,3);
declare variable tmp_fact numeric(15,3);
declare variable tmp_plan_in numeric(15,3);
declare variable tmp_plan_out numeric(15,3);
declare variable tmp_reserve numeric(15,3);
declare variable tmp_available2 numeric(15,3);
declare variable tmp_fact2 numeric(15,3);
declare variable tmp_plan_in2 numeric(15,3);
declare variable tmp_plan_out2 numeric(15,3);
declare variable tmp_reserve2 numeric(15,3);
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: количество на складах с учетом прав пользователя
 Используется: сервером приложения, WH_QUAN_ON_WAREHOUSES
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 30.10.2003 Кривопустов Bug2048 - возможность не указывать МОЛ
**********************************************************/
  available = 0.0;
  fact = 0.0;
  plan_in = 0.0;
  plan_out = 0.0;
  reserve = 0.0;
      
  if (stock is not null) then begin
    /* by one stock */
    select sum(sc.quantity), sum(sc.plan_in), sum(sc.plan_out), sum(sc.reserve),
      sum(sc.quantity2), sum(sc.plan_in2), sum(sc.plan_out2), sum(sc.reserve2)
    from stockcard sc left join contractor c on sc.stock_id = c.id
         left join contractor e on sc.mol_id = e.id
    where ((sc.catalog_id = :catalog_id) and 
      (c.upcode = :stock) and
      ((:mol is null) or (e.upcode = :mol)))
    into :fact, :plan_in, :plan_out, :reserve,
      :fact2, :plan_in2, :plan_out2, :reserve2;
  
    if (fact is null) then fact = 0.0;
    if (plan_in is null) then plan_in = 0.0;
    if (plan_out is null) then plan_out = 0.0;
    if (reserve is null) then reserve = 0.0;
    if (fact2 is null) then fact2 = 0.0;
    if (plan_in2 is null) then plan_in2 = 0.0;
    if (plan_out2 is null) then plan_out2 = 0.0;
    if (reserve2 is null) then reserve2 = 0.0;
  
    select o.calc_fact, o.calc_fact_sign, o.calc_plan_in, o.calc_plan_in_sign,
      o.calc_plan_out, o.calc_plan_out_sign, o.calc_reserve, o.calc_reserve_sign
    from contractor c join wh_warehouse o on o.contractor_id = c.id
    where c.upcode = :stock
    into :calc_fact, :calc_fact_sign, :calc_plan_in, :calc_plan_in_sign,
      :calc_plan_out, :calc_plan_out_sign, :calc_reserve, :calc_reserve_sign;
      
    if (calc_fact = 1) then begin
      if (calc_fact_sign = 0) then begin
        available = available + fact;
        available2 = available2 + fact2;
      end
      else begin
        available = available - fact;
        available2 = available2 - fact2;
      end
    end
    if (calc_plan_in = 1) then begin
      if (calc_plan_in_sign = 0) then begin
        available = available + plan_in;
        available2 = available2 + plan_in2;
      end
      else begin
        available = available - plan_in;
        available2 = available2 - plan_in2;
      end
    end
    if (calc_plan_out = 1) then begin
      if (calc_plan_out_sign = 0) then begin
        available = available + plan_out;
        available2 = available2 + plan_out2;
      end
      else begin
        available = available - plan_out;
        available2 = available2 - plan_out2;
      end
    end
    if (calc_reserve = 1) then begin
      if (calc_reserve_sign = 0) then begin
        available = available + reserve;
        available2 = available2 + reserve2;
      end
      else begin
        available = available - reserve;
        available2 = available2 - reserve2;
      end
    end
  end
  else begin
    /* by all stocks */
    for 
      select c.id, o.calc_fact, o.calc_fact_sign, o.calc_plan_in, o.calc_plan_in_sign,
        o.calc_plan_out, o.calc_plan_out_sign, o.calc_reserve, o.calc_reserve_sign
      from contractor c join wh_warehouse o on o.contractor_id = c.id
      into :stock_id, :calc_fact, :calc_fact_sign, :calc_plan_in, :calc_plan_in_sign,
        :calc_plan_out, :calc_plan_out_sign, :calc_reserve, :calc_reserve_sign
    do begin
      tmp_fact = 0.0;
      tmp_plan_in = 0.0;
      tmp_plan_out = 0.0;
      tmp_reserve = 0.0;
      tmp_available = 0.0;

      /*#3199, #3163 Valentin A. Poroxnenko*/
      select sum(sc.quantity), sum(sc.plan_in), sum(sc.plan_out), sum(sc.reserve),
        sum(sc.quantity2), sum(sc.plan_in2), sum(sc.plan_out2), sum(sc.reserve2)
      from stockcard sc
     where
     (sc.stock_id = :stock_id and sc.catalog_id = :catalog_id)
     and
     (exists (select e.id, s.user_id, r.folder_id,
     r.group_id, s.group_id, r.folderpart, r.rights, e.upcode
        from folder_rights r
        left join contractor e on e.id = sc.stock_id
        left join sec_link_users_groups s on s.user_id = :user_id
        where
        (r.folder_id = e.id) and (r.group_id = s.group_id)
         and (r.folderpart = 4) and (r.rights > 0)
        and ((:mol is null) or (e.upcode = :mol))))
      into :tmp_fact, :tmp_plan_in, :tmp_plan_out, :tmp_reserve,
        :tmp_fact2, :tmp_plan_in2, :tmp_plan_out2, :tmp_reserve2;
    
      if (tmp_fact is null) then tmp_fact = 0.0;
      if (tmp_plan_in is null) then tmp_plan_in = 0.0;
      if (tmp_plan_out is null) then tmp_plan_out = 0.0;
      if (tmp_reserve is null) then tmp_reserve = 0.0;
      if (tmp_fact2 is null) then tmp_fact2 = 0.0;
      if (tmp_plan_in2 is null) then tmp_plan_in2 = 0.0;
      if (tmp_plan_out2 is null) then tmp_plan_out2 = 0.0;
      if (tmp_reserve2 is null) then tmp_reserve2 = 0.0;
    
      if (calc_fact = 1) then begin
        if (calc_fact_sign = 0) then begin
          tmp_available = tmp_available + tmp_fact;
          tmp_available2 = tmp_available2 + tmp_fact2;
        end
        else begin
          tmp_available = tmp_available - tmp_fact;
          tmp_available2 = tmp_available2 - tmp_fact2;
        end
      end
      if (calc_plan_in = 1) then begin
        if (calc_plan_in_sign = 0) then begin
          tmp_available = tmp_available + tmp_plan_in;
          tmp_available2 = tmp_available2 + tmp_plan_in2;
        end
        else begin
          tmp_available = tmp_available - tmp_plan_in;
          tmp_available2 = tmp_available2 - tmp_plan_in2;
        end
      end
      if (calc_plan_out = 1) then begin
        if (calc_plan_out_sign = 0) then begin
          tmp_available = tmp_available + tmp_plan_out;
          tmp_available2 = tmp_available2 + tmp_plan_out2;
        end
        else begin
          tmp_available = tmp_available - tmp_plan_out;
          tmp_available2 = tmp_available2 - tmp_plan_out2;
        end
      end
      if (calc_reserve = 1) then begin
        if (calc_reserve_sign = 0) then begin
          tmp_available = tmp_available + tmp_reserve;
          tmp_available2 = tmp_available2 + tmp_reserve2;
        end
        else begin
          tmp_available = tmp_available - tmp_reserve;
          tmp_available2 = tmp_available2 - tmp_reserve2;
        end
      end
    
      available = available + tmp_available;
      fact = fact + tmp_fact;
      plan_in = plan_in + tmp_plan_in;
      plan_out = plan_out + tmp_plan_out;
      reserve = reserve + tmp_reserve;
      available2 = available2 + tmp_available2;
      fact2 = fact2 + tmp_fact2;
      plan_in2 = plan_in2 + tmp_plan_in2;
      plan_out2 = plan_out2 + tmp_plan_out2;
      reserve2 = reserve2 + tmp_reserve2;

    end
  end  
  suspend;
end
^

ALTER PROCEDURE F_REGISTERFACTURA_IN_BUYBOOK (
    DOC_ID INTEGER,
    SPEC_ID INTEGER,
    FOLDER_ID INTEGER,
    ADATE TIMESTAMP,
    ASUM NUMERIC(18,4),
    BOOK_ID INTEGER)
RETURNS (
    BUYBOOK_ID INTEGER)
AS
DECLARE VARIABLE DRATE NUMERIC(15,5);
DECLARE VARIABLE NSP NUMERIC(15,4);
DECLARE VARIABLE SUM_WO_10 NUMERIC(15,4);
DECLARE VARIABLE SUM_WO_20 NUMERIC(15,4);
DECLARE VARIABLE SUM_WO_18 NUMERIC(15,4);
DECLARE VARIABLE NDS10 NUMERIC(15,4);
DECLARE VARIABLE NDS20 NUMERIC(15,4);
DECLARE VARIABLE NDS18 NUMERIC(15,4);
DECLARE VARIABLE NOT_TAXABLE NUMERIC(15,4);
DECLARE VARIABLE SPECSUM NUMERIC(15,4);
DECLARE VARIABLE F DOUBLE PRECISION;
DECLARE VARIABLE SUMTAX NUMERIC(15,4);
DECLARE VARIABLE TAXFORM SMALLINT;
declare variable prec integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: используется при отработке документов в 
 книге покупок
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 19.06.2003 Кривопустов Точность из настроек модуля, суммы
                        без НДС теперь не включают НСП
 05.11.2003 Кривопустов Точность расчетов =4, округление
                        производится сервером приложения
 29.12.2003 Кривопустов Поддержка НДС 18%
 17.03.2004 Кривопустов Поддержка НДС 18% в соответствии с
                        "Постановлением Правительства РФ от
                        16.02.2004 № 84"
**********************************************************/
  /*select c.currency_prec from acc_config c into :prec;*/
  prec = 4;

  select s.summa from docspec s where s.id = :spec_id into :specsum;

  if (specsum = 0) then begin
    buybook_id = 0;
    suspend;
    Exit;
  end

  f = asum / specsum;
  
  drate = 0; nsp = 0;
  sum_wo_10 = 0; sum_wo_20 = 0; sum_wo_18 = 0;
  nds10 = 0; nds20 = 0; nds18 = 0;
  not_taxable = specsum;
  for
    select ts.summ, t.directrate, t.taxform
      from taxsumm ts left join tax t on t.id = ts.tax_id
      where (ts.specification_id = :spec_id)
      into :sumtax, :drate, :TaxForm
  do begin
    if (TaxForm = 0) then begin 
      if (drate = 10) then begin
        nds10 = nds10 + sumtax;
        sum_wo_10 = sum_wo_10 + specsum - sumtax;
        not_taxable = 0;
      end
      else if (drate = 20) then begin
        nds20 = nds20 + sumtax;
        sum_wo_20 = sum_wo_20 + specsum - sumtax;
        not_taxable = 0;
      end
      else if (drate = 18) then begin
        nds18 = nds18 + sumtax;
        sum_wo_18 = sum_wo_18 + specsum - sumtax;
        not_taxable = 0;
      end
    end
    else if (TaxForm = 3) then begin 
      nsp = nsp + sumtax;
      if (sum_wo_10 <> 0) then
        sum_wo_10 = sum_wo_10 - sumtax;
      else if (sum_wo_20 <> 0) then
        sum_wo_20 = sum_wo_20 - sumtax;
      else if (sum_wo_18 <> 0) then
        sum_wo_18 = sum_wo_18 - sumtax;
      not_taxable = 0;
    end
  end
  nds10 = Round(nds10 * f, prec);
  nds20 = Round(nds20 * f, prec);
  nds18 = Round(nds18 * f, prec);
  sum_wo_10 = Round(sum_wo_10 * f, prec);
  sum_wo_20 = Round(sum_wo_20 * f, prec);
  sum_wo_18 = Round(sum_wo_18 * f, prec);
  nsp = Round(nsp * f, prec);
  not_taxable = Round(not_taxable * f, prec);
  if (book_id is null) then begin
    buybook_id = gen_id(buybook_id_gen, 1);
    insert into buybook(id, folder_id, document_id, doctype, docnumber, docdate, insertdate, indate, stockdate, paydate, provider_id, totalsum, sum_without_nds1, sum_without_nds2, sum_without_nds3,
      nds1_sum, nds2_sum, nds3_sum, not_taxable_sum, nsp_sum)
    select :buybook_id, :folder_id, :doc_id, d.doctype, d.docnumber, d.docdate, :adate, f.indate, f.stockdate, f.paydate, d.from_id, :asum, :sum_wo_20, :sum_wo_10, :sum_wo_18,
      :nds20, :nds10, :nds18, :not_taxable, :nsp
    from dochead d, facturahead f where (d.id = :Doc_id) and (d.id = f.dochead_id);
  end
  else begin
    update buybook set
      totalsum = totalsum + :asum,
      sum_without_nds1 = sum_without_nds1 + :sum_wo_20,
      sum_without_nds2 = sum_without_nds2 + :sum_wo_10,
      sum_without_nds3 = sum_without_nds3 + :sum_wo_18,
      nds1_sum = nds1_sum + :nds20,
      nds2_sum = nds2_sum + :nds10,
      nds3_sum = nds3_sum + :nds18,
      not_taxable_sum = not_taxable_sum + :not_taxable,
      nsp_sum = nsp_sum + :nsp
    where id = :book_id;
    
    buybook_id = book_id;
  end
  
  suspend;
end
^

ALTER PROCEDURE F_REGISTERFACTURA_IN_SALEBOOK (
    DOC_ID INTEGER,
    SPEC_ID INTEGER,
    FOLDER_ID INTEGER,
    ADATE TIMESTAMP,
    ASUM NUMERIC(18,4),
    BOOK_ID INTEGER)
RETURNS (
    SALEBOOK_ID INTEGER)
AS
DECLARE VARIABLE DRATE NUMERIC(15,5);
DECLARE VARIABLE NSP NUMERIC(15,4);
DECLARE VARIABLE SUM_WO_10 NUMERIC(15,4);
DECLARE VARIABLE SUM_WO_20 NUMERIC(15,4);
DECLARE VARIABLE SUM_WO_18 NUMERIC(15,4);
DECLARE VARIABLE NDS10 NUMERIC(15,4);
DECLARE VARIABLE NDS20 NUMERIC(15,4);
DECLARE VARIABLE NDS18 NUMERIC(15,4);
DECLARE VARIABLE NOT_TAXABLE NUMERIC(15,4);
DECLARE VARIABLE SPECSUM NUMERIC(15,4);
DECLARE VARIABLE F DOUBLE PRECISION;
DECLARE VARIABLE SUMTAX NUMERIC(15,4);
DECLARE VARIABLE TAXFORM SMALLINT;
declare variable prec integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: используется при отработке документов в 
 книге продаж
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 19.06.2003 Кривопустов Точность из настроек модуля, суммы
                        без НДС теперь не включают НСП
 05.11.2003 Кривопустов Точность расчетов =4, округление
                        производится сервером приложения
 29.12.2003 Кривопустов Поддержка НДС 18%
 17.03.2004 Кривопустов Поддержка НДС 18% в соответствии с
                        "Постановлением Правительства РФ от
                        16.02.2004 № 84"
**********************************************************/
  /*select c.currency_prec from acc_config c into :prec;*/
  prec = 4;

  select s.summa from docspec s where s.id = :spec_id into :specsum;

  if (specsum = 0) then begin
    salebook_id = 0;
    suspend;
    Exit;
  end

  f = asum / specsum;
  
  drate = 0; nsp = 0;
  sum_wo_10 = 0; sum_wo_20 = 0; sum_wo_18 = 0;
  nds10 = 0; nds20 = 0; nds18 = 0;
  not_taxable = specsum;
  for
    select ts.summ, t.directrate, t.taxform
      from taxsumm ts left join tax t on t.id = ts.tax_id
      where (ts.specification_id = :spec_id)
      into :sumtax, :drate, :TaxForm
  do begin
    if (TaxForm = 0) then begin 
      if (drate = 10) then begin
        nds10 = nds10 + sumtax;
        sum_wo_10 = sum_wo_10 + specsum - sumtax;
        not_taxable = 0;
      end
      else if (drate = 20) then begin
        nds20 = nds20 + sumtax;
        sum_wo_20 = sum_wo_20 + specsum - sumtax;
        not_taxable = 0;
      end
      else if (drate = 18) then begin
        nds18 = nds18 + sumtax;
        sum_wo_18 = sum_wo_18 + specsum - sumtax;
        not_taxable = 0;
      end
    end
    else if (TaxForm = 3) then begin 
      nsp = nsp + sumtax;
      if (sum_wo_10 <> 0) then
        sum_wo_10 = sum_wo_10 - sumtax;
      else if (sum_wo_20 <> 0) then
        sum_wo_20 = sum_wo_20 - sumtax;
      else if (sum_wo_18 <> 0) then
        sum_wo_18 = sum_wo_18 - sumtax;
      not_taxable = 0;
    end
  end
  nds10 = Round(nds10 * f, prec);
  nds20 = Round(nds20 * f, prec);
  nds18 = Round(nds18 * f, prec);
  sum_wo_10 = Round(sum_wo_10 * f, prec);
  sum_wo_20 = Round(sum_wo_20 * f, prec);
  sum_wo_18 = Round(sum_wo_18 * f, prec);
  nsp = Round(nsp * f, prec);
  not_taxable = Round(not_taxable * f, prec);
  if (book_id is null) then begin
    salebook_id = gen_id(salebook_id_gen, 1);
    insert into salebook(id, folder_id, document_id, doctype, docnumber, docdate, insertdate, paydate, customer_id, totalsum, sum_without_nds1, sum_without_nds2, sum_without_nds3,
      nds1_sum, nds2_sum, nds3_sum, not_taxable_sum, nsp_sum)
    select :salebook_id, :folder_id, :doc_id, d.doctype, d.docnumber, d.docdate, :adate, f.paydate, d.to_id, :asum, :sum_wo_20, :sum_wo_10, :sum_wo_18,
      :nds20, :nds10, :nds18, :not_taxable, :nsp
    from dochead d, facturahead f where (d.id = :Doc_id) and (d.id = f.dochead_id);
  end
  else begin
    update salebook set
      totalsum = totalsum + :asum,
      sum_without_nds1 = sum_without_nds1 + :sum_wo_20,
      sum_without_nds2 = sum_without_nds2 + :sum_wo_10,
      sum_without_nds3 = sum_without_nds3 + :sum_wo_18,
      nds1_sum = nds1_sum + :nds20,
      nds2_sum = nds2_sum + :nds10,
      nds3_sum = nds3_sum + :nds18,
      not_taxable_sum = not_taxable_sum + :not_taxable,
      nsp_sum = nsp_sum + :nsp
    where id = :book_id;
    
    salebook_id = book_id;
  end

  suspend;
end
^

ALTER PROCEDURE F_REVALUE_REMNVAL (
    INCOMEDATE TIMESTAMP,
    ACCBATCH_ID INTEGER,
    ACC INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    CATALOG_ID INTEGER,
    DOCSECTION SMALLINT,
    DOC_ID INTEGER,
    DOCTYPE VARCHAR(15),
    DOCNUMBER VARCHAR(20),
    DOCDATE TIMESTAMP,
    QUAN NUMERIC(15,3),
    SUMMANAT NUMERIC(15,4),
    SUMMACUR NUMERIC(15,4),
    CONTR INTEGER,
    OLDACCBATCHHISTORY_ID INTEGER)
RETURNS (
    ACCBATCHHISTORY_ID INTEGER)
AS
/*declare variable accbatch_id integer;*/
  declare variable prev_id integer;
  declare variable next_id integer;
  declare variable enddate  timestamp;
  declare variable costnat numeric(15,5);
  declare variable costcur numeric(15,5);
  declare variable newcostnat numeric(15,5);
  declare variable newcostcur numeric(15,5);
  declare variable quantity numeric(15,3);
  declare variable accbatchquan numeric(15,3);
declare variable ans integer;
begin
  /* find accbatch */
  ans=dm('accbatch_id');
  if (accbatch_id is null) then exception E_Invalid_AccBatch;
  select a.beginquan from accbatch a
  where id = :accbatch_id
  into :accbatchquan;
  ans=wi('batchid',accbatch_id);
  if (quan <> 0) then
    update accbatch
    set
      endquan = endquan + :quan
    where id = :accbatch_id;
  /* find prev accbatchhistory */
  ans=dm('prev');
  select a.id, a.costnat, a.costcur from accbatchhistory a
  where (a.accbatch_id = :accbatch_id)and((a.begindate<=:incomedate)and
    (((a.enddate>:incomedate)or(a.enddate is null)))and
    ((a.id <> :oldaccbatchhistory_id)or(:oldaccbatchhistory_id is null))or
    ((a.enddate=:incomedate)and(a.id <> :oldaccbatchhistory_id)and(:oldaccbatchhistory_id is not null)))/* if update revalue */
  into :prev_id, :costnat, :costcur;
  if (prev_id is null) then begin
    /* update income history */
    costnat = 0;
    costcur = 0;
  end
  ans=wi('previd',prev_id);
  ans=wi('old',oldaccbatchhistory_id);
  /* find next accbatchhistory */
  ans=dm('next');
  if (oldaccbatchhistory_id is null) then begin
    select a.id, a.begindate from accbatchhistory a
    where (a.accbatch_id = :accbatch_id)and
      (a.begindate = (select min(a1.begindate) from accbatchhistory a1 where (a1.accbatch_id = :accbatch_id) and (a1.begindate>=:incomedate)))
    into :next_id, :enddate;
    /* may be if change in own date */
    if (prev_id = next_id) then begin
      next_id = null; enddate = null;
    end
    ans=wd('enddate',enddate);
    /* update previous history */
    update accbatchhistory
    set
      enddate = :incomedate
    where id = :prev_id;
  end
  /* calculation out quantity up to income date */
  select sum(a.quantity) from accbatchhistory a
  where (a.accbatch_id = :accbatch_id)and(a.begindate <= :incomedate)and(a.enddate <= :incomedate)and
    ((a.id <> :oldaccbatchhistory_id)or(:oldaccbatchhistory_id is null))
  into :quantity;
  /* calculate new cost */
  if (quantity is null) then quantity = 0;
  quantity = accbatchquan - quantity;
  newcostnat = (costnat*quantity+summanat)/(quantity+quan);
  newcostcur = (costcur*quantity+summacur)/(quantity+quan);
  if (oldaccbatchhistory_id is null) then begin
    /* add history about revalue */
    accbatchhistory_id = gen_id(accbatchhistory_id_gen,1);
    insert into accbatchhistory(id, accbatch_id, begindate, enddate, costnat, costcur, actiontype)
    values
    (:accbatchhistory_id, :accbatch_id, :incomedate, :enddate, :newcostnat, :newcostcur, 1);
  end
  else
    /* update */
    update accbatchhistory
    set
      costnat = :newcostnat,
      costcur = :newcostcur
    where id = :oldaccbatchhistory_id;
end
^

ALTER PROCEDURE F_ROLLBACK_ACTIVITY (
    SEGMENT_ID INTEGER)
RETURNS (
    PROCESS_ID INTEGER,
    ACTIVITY_ID INTEGER,
    RESOURCE_ID INTEGER,
    ITERATE SMALLINT)
AS
declare variable comment varchar(256);
declare variable restore smallint;
declare variable assign_id integer;
begin
  /* load data */
  select wfa.wf_process_id, wfrb.wf_activity_id, wfrb.wf_resource_id, wfrb.iterate, wfrb.restore_assignment, wfrb.comment from wf_rollback_segment wfrb
    join wf_activity wfa on wfa.id = wfrb.wf_activity_id
  where (wfrb.id = :segment_id)
  into :process_id, :activity_id, :resource_id, :iterate, :restore, :comment;

  /* delete assignment */
  delete from wf_assignment wa where wa.id in
    (select wfas.id from wf_rollback_segment wfr, wf_assignment wfas, wf_map_edge wfme, wf_activity wfa1, wf_activity wfa2
    where (wfr.wf_activity_id = wfa1.id) and (wfa1.model_id = wfme.source_activity_id) and
      (wfas.wf_activity_id = wfa2.id) and (wfa2.model_id = wfme.dest_activity_id) and (wfr.id = :segment_id) and (wfa2.wf_process_id = :process_id));
  /* restore assignment */
  if (restore = 1) then begin
    select id from wf_assignment where (wf_activity_id = :activity_id) and (wf_resource_id = :resource_id)
    into :assign_id;
    if (assign_id is null) then
      insert into wf_assignment (id, wf_activity_id, wf_resource_id, comment)
      values (gen_id(wf_assignment_id_gen, 1), :activity_id, :resource_id, :comment);
  end

  /* delete rollback segment */
  delete from wf_rollback_segment where id = :segment_id;

  suspend;
end
^

ALTER PROCEDURE F_TAXES_SUM (
    ALLTAXES SMALLINT,
    TAXFORM SMALLINT,
    INCLUDED SMALLINT,
    CALCTAXESKIND_ID INTEGER,
    SECTIONDOC SMALLINT,
    SPEC_ID INTEGER)
RETURNS (
    RESULT NUMERIC(15,4))
AS
begin
  if (alltaxes = 1) then begin 
    select sum(summ) from taxsumm ts, calctaxeslink ctk 
    where (ctk.tax_id = ts.tax_id) and (ctk.kind_id = :CALCTAXESKIND_ID) and
          (ctk.included = :included) and (specification_id = :spec_id)
    into :result;
  end
  else begin
    select sum(ts.summ) from taxsumm ts, tax t, calctaxeslink ctk 
    where (ctk.tax_id = t.id) and (ctk.kind_id = :CALCTAXESKIND_ID) and (ctk.included = :included) and 
          (specification_id = :spec_id) and (t.taxform = :taxform) and (t.id = ts.tax_id)
    into :result;
  end
  if (result is null) then result = 0;
  suspend;
end
^

ALTER PROCEDURE F_UPDATE_STOCK_PLANNED (
    DIRECTION SMALLINT,
    KIND SMALLINT,
    STOCK_ID INTEGER,
    MOL_ID INTEGER,
    QUANTITY NUMERIC(15,3),
    QUANTITY2 NUMERIC(15,3),
    CATALOG_ID INTEGER,
    DOCHEAD_ID INTEGER,
    DOCSPEC_ID INTEGER,
    PROCESSDATE TIMESTAMP)
RETURNS (
    HISTORY_ID INTEGER)
AS
declare variable STOCKCARD_ID integer;
begin 
  /* Find stockcard */
  select sc.id from stockcard sc where
    (sc.catalog_id = :catalog_id) and
    (sc.stock_id = :stock_id) and 
    ((sc.mol_id = :mol_id) or ((sc.mol_id is null) and (:mol_id is null)))
  into :stockcard_id;
  if (kind = 0) then begin /* income */
    if (stockcard_id is null) then begin
      /* add stockcard */
      stockcard_id = gen_id(stockcard_id_gen, 1); 
      insert into stockcard(id, stock_id, mol_id, catalog_id, cardnumber, plan_in, plan_in2)
      values (:stockcard_id, :stock_id, :mol_id, :catalog_id, '1', :quantity, :quantity2);
    end
    else begin
      if (direction = 0) then
        /* do plan in */
        update stockcard sc set
            sc.plan_in = sc.plan_in + :quantity,
            sc.plan_in2 = sc.plan_in2 + :quantity2
          where sc.id = :stockcard_id;
      else
        /* undo plan in */
        update stockcard sc set
            sc.plan_in = sc.plan_in - :quantity,
            sc.plan_in2 = sc.plan_in2 - :quantity2
          where sc.id = :stockcard_id;
    end
  end
  else begin /* outcome */
    if (stockcard_id is null) then begin
      /* add stockcard */
      stockcard_id = gen_id(stockcard_id_gen, 1); 
      insert into stockcard(id, stock_id, mol_id, catalog_id, cardnumber, plan_out, plan_out2)
      values (:stockcard_id, :stock_id, :mol_id, :catalog_id, '1', :quantity, :quantity2);
    end
    else begin
      if (direction = 0) then
        /* do plan out */
        update stockcard sc set
            sc.plan_out = sc.plan_out + :quantity,
            sc.plan_out2 = sc.plan_out2 + :quantity2
          where sc.id = :stockcard_id; 
      else
        /* undo plan out */
        update stockcard sc set
            sc.plan_out = sc.plan_out - :quantity,
            sc.plan_out2 = sc.plan_out2 - :quantity2
          where sc.id = :stockcard_id; 
    end  
  end
  /* insert history */
  history_id = gen_id(stockplanhistory_id_gen, 1);
  insert into stockplanhistory (ID,STOCKCARD_ID,KIND,DIRECTION,PROCESSDATE,SYSDATETIME,
    QUANTITY,QUANTITY2,DOCHEAD_ID,DOCSPEC_ID)
  values (:history_id, :stockcard_id, :direction, :kind, :processdate, 'now',
    :quantity, :quantity2, :dochead_id, :docspec_id);
  suspend;
end
^

ALTER PROCEDURE F_UPDATE_STOCK_RESERVE (
    DIRECTION SMALLINT,
    STOCK_ID INTEGER,
    MOL_ID INTEGER,
    QUANTITY NUMERIC(15,3),
    QUANTITY2 NUMERIC(15,3),
    CATALOG_ID INTEGER,
    DOCHEAD_ID INTEGER,
    DOCSPEC_ID INTEGER,
    PROCESSDATE TIMESTAMP)
RETURNS (
    HISTORY_ID INTEGER)
AS
declare variable STOCKCARD_ID integer;
begin 
  /* Find stockcard */
  select sc.id from stockcard sc where
    (sc.catalog_id = :catalog_id) and
    (sc.stock_id = :stock_id) and 
    ((sc.mol_id = :mol_id) or ((sc.mol_id is null) and (:mol_id is null)))
  into :stockcard_id;
  if (stockcard_id is null) then begin
    /* add stockcard */
    stockcard_id = gen_id(stockcard_id_gen, 1); 
    insert into stockcard(id, stock_id, mol_id, catalog_id, cardnumber, reserve, reserve2)
    values (:stockcard_id, :stock_id, :mol_id, :catalog_id, '1', :quantity, :quantity2);
  end
  else begin
    if (direction = 0) then begin /* do reserve */
      update stockcard sc set
          sc.reserve = sc.reserve + :quantity,
          sc.reserve2 = sc.reserve2 + :quantity2
        where sc.id = :stockcard_id;
    end
    else begin /* undo reserve */
      update stockcard sc set
          sc.reserve = sc.reserve - :quantity,
          sc.reserve2 = sc.reserve2 - :quantity2
        where sc.id = :stockcard_id;
    end
  end
  /* insert history */
  history_id = gen_id(stockplanhistory_id_gen, 1);
  insert into stockplanhistory (ID,STOCKCARD_ID,KIND,DIRECTION,PROCESSDATE,SYSDATETIME,
    QUANTITY,QUANTITY2,DOCHEAD_ID,DOCSPEC_ID)
  values (:history_id, :stockcard_id, :direction+2, 0, :processdate, 'now',
    :quantity, :quantity2, :dochead_id, :docspec_id);
  suspend;
end
^

ALTER PROCEDURE F_VALQUANT_ONDATE (
    CATID INTEGER,
    ADATE TIMESTAMP,
    ACC INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    CONTR INTEGER)
RETURNS (
    QUANT NUMERIC(15,3))
AS
declare variable PeriodId integer;
declare variable BegDate  timestamp;
declare variable BegQuant numeric(15,3); 
declare variable DbQuant numeric(15,3); 
declare variable KtQuant numeric(15,3); 
begin
  select p.id, p.datefrom from period p
    where (p.datefrom <= :ADate) and (p.dateto >= :ADate)
    into :PeriodId, :BegDate; 
  if ((Anl1 is null) and (Anl2 is null) and (Anl3 is null) and
      (Anl4 is null) and (Anl5 is null)) then begin
    /* without analytika */
    select sum(r.beginquan)
      from remnval r join period p on p.id=r.period_id
      where (r.period_id = :PeriodId) and
            (r.catalog_id = :CatId) and
            (r.acc_id = :Acc) and
            ((r.contractor_id = :contr)or((r.contractor_id is null)and(:contr is null)))
      into :BegQuant;
    select sum(s.quantity)
      from economicspec s join economicoper h on h.id = s.economicoper_id
      where (s.catalog_id = :CatId) and
            (s.accdb_id = :Acc) and
            ((h.to_id = :contr)or((h.to_id is null)and(:contr is null))) and
            (h.keepdate between :BegDate and :ADate)
      into :DbQuant;
    select sum(s.quantity)
      from economicspec s join economicoper h on h.id = s.economicoper_id
      where (s.catalog_id = :CatId) and
            (s.acckt_id = :Acc) and
            ((h.from_id = :contr)or((h.from_id is null)and(:contr is null))) and
            (h.keepdate between :BegDate and :ADate)
      into :KtQuant;
  end
  else begin
    /* with analytika */
    select sum(r.beginquan)
      from remnval r join period p on p.id=r.period_id
      where (r.period_id = :PeriodId) and
            (r.catalog_id = :CatId) and
            (r.acc_id = :Acc) and
            ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and
            ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and
            ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and
            ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and
            ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null))) and
            ((r.contractor_id = :contr)or((r.contractor_id is null)and(:contr is null)))
      into :BegQuant;
    select sum(s.quantity)
      from economicspec s join economicoper h on h.id = s.economicoper_id
      where (s.catalog_id = :CatId) and
            (s.accdb_id = :Acc) and
            ((s.anldb1_id = :Anl1)or((s.anldb1_id is null)and(:Anl1 is null))) and
            ((s.anldb2_id = :Anl2)or((s.anldb2_id is null)and(:Anl2 is null))) and
            ((s.anldb3_id = :Anl3)or((s.anldb3_id is null)and(:Anl3 is null))) and
            ((s.anldb4_id = :Anl4)or((s.anldb4_id is null)and(:Anl4 is null))) and
            ((s.anldb5_id = :Anl5)or((s.anldb5_id is null)and(:Anl5 is null))) and
            ((h.to_id = :contr)or((h.to_id is null)and(:contr is null))) and
            (h.keepdate between :BegDate and :ADate)
      into :DbQuant;        
    select sum(s.quantity)
      from economicspec s join economicoper h on h.id = s.economicoper_id
      where (s.catalog_id = :CatId) and
            (s.acckt_id = :Acc) and
            ((s.anlkt1_id = :Anl1)or((s.anlkt1_id is null)and(:Anl1 is null))) and
            ((s.anlkt2_id = :Anl2)or((s.anlkt2_id is null)and(:Anl2 is null))) and
            ((s.anlkt3_id = :Anl3)or((s.anlkt3_id is null)and(:Anl3 is null))) and
            ((s.anlkt4_id = :Anl4)or((s.anlkt4_id is null)and(:Anl4 is null))) and
            ((s.anlkt5_id = :Anl5)or((s.anlkt5_id is null)and(:Anl5 is null))) and
            ((h.from_id = :contr)or((h.from_id is null)and(:contr is null))) and
            (h.keepdate between :BegDate and :ADate)
      into :KtQuant;
  end
  if (DbQuant is null) then DbQuant = 0;        
  if (KtQuant is null) then KtQuant = 0;        
  Quant = BegQuant + DbQuant - KtQuant;
  suspend;
end
^

ALTER PROCEDURE F_VALSUM_ONDATE (
    CATID INTEGER,
    ADATE TIMESTAMP,
    ACC INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    CONTR INTEGER)
RETURNS (
    SUMMA NUMERIC(15,4))
AS
declare variable PeriodId integer;
declare variable BegDate  timestamp;
declare variable BegSum numeric(15,4);
declare variable DbSum numeric(15,4);
declare variable KtSum numeric(15,4);
begin
  select p.id, p.datefrom from period p
    where (p.datefrom <= :ADate) and (p.dateto >= :ADate)
    into :PeriodId, :BegDate; 
  if ((Anl1 is null) and (Anl2 is null) and (Anl3 is null) and
      (Anl4 is null) and (Anl5 is null)) then begin
    /* without analytika */
    select sum(r.remnbeginnat)
      from remnval r join period p on p.id=r.period_id
      where (r.period_id = :PeriodId) and
            (r.catalog_id = :CatId) and
            (r.acc_id = :Acc) and
            ((r.contractor_id = :contr)or((r.contractor_id is null)and(:contr is null)))
      into :BegSum;
    select sum(s.summanat)
      from economicspec s join economicoper h on h.id = s.economicoper_id
      where (s.catalog_id = :CatId) and
            (s.accdb_id = :Acc) and
            ((h.to_id = :contr)or((h.to_id is null)and(:contr is null))) and
            (h.keepdate between :BegDate and :ADate)
      into :DbSum;        
    select sum(s.summanat)
      from economicspec s join economicoper h on h.id = s.economicoper_id
      where (s.catalog_id = :CatId) and
            (s.acckt_id = :Acc) and
            ((h.from_id = :contr)or((h.from_id is null)and(:contr is null))) and
            (h.keepdate between :BegDate and :ADate)
      into :KtSum;
  end
  else begin
    /* with analytika */
    select sum(r.remnbeginnat)
      from remnval r join period p on p.id=r.period_id
      where (r.period_id = :PeriodId) and
            (r.catalog_id = :CatId) and
            (r.acc_id = :Acc) and
            ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and
            ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and
            ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and
            ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and
            ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null))) and
            ((r.contractor_id = :contr)or((r.contractor_id is null)and(:contr is null)))
      into :BegSum;
    select sum(s.summanat)
      from economicspec s join economicoper h on h.id = s.economicoper_id
      where (s.catalog_id = :CatId) and
            (s.accdb_id = :Acc) and
            ((s.anldb1_id = :Anl1)or((s.anldb1_id is null)and(:Anl1 is null))) and
            ((s.anldb2_id = :Anl2)or((s.anldb2_id is null)and(:Anl2 is null))) and
            ((s.anldb3_id = :Anl3)or((s.anldb3_id is null)and(:Anl3 is null))) and
            ((s.anldb4_id = :Anl4)or((s.anldb4_id is null)and(:Anl4 is null))) and
            ((s.anldb5_id = :Anl5)or((s.anldb5_id is null)and(:Anl5 is null))) and
            ((h.to_id = :contr)or((h.to_id is null)and(:contr is null))) and
            (h.keepdate between :BegDate and :ADate)
      into :DbSum;        
    select sum(s.summanat)
      from economicspec s join economicoper h on h.id = s.economicoper_id
      where (s.catalog_id = :CatId) and
            (s.acckt_id = :Acc) and
            ((s.anlkt1_id = :Anl1)or((s.anlkt1_id is null)and(:Anl1 is null))) and
            ((s.anlkt2_id = :Anl2)or((s.anlkt2_id is null)and(:Anl2 is null))) and
            ((s.anlkt3_id = :Anl3)or((s.anlkt3_id is null)and(:Anl3 is null))) and
            ((s.anlkt4_id = :Anl4)or((s.anlkt4_id is null)and(:Anl4 is null))) and
            ((s.anlkt5_id = :Anl5)or((s.anlkt5_id is null)and(:Anl5 is null))) and
            ((h.from_id = :contr)or((h.from_id is null)and(:contr is null))) and
            (h.keepdate between :BegDate and :ADate)
      into :KtSum;
  end
  if (DbSum is null) then DbSum = 0;        
  if (KtSum is null) then KtSum = 0;        
  Summa = BegSum + DbSum - KtSum;        
  suspend;
end
^

ALTER PROCEDURE FIN_CARRY_FORWARD_BALANCE (
    PERIOD_ID INTEGER,
    ACCOUNT_ID INTEGER)
AS
declare variable dateclose timestamp;
declare variable begindate timestamp;
declare variable prev_per_id integer;
declare variable ta_id integer;
declare variable tf_id integer;
declare variable prev_ta_id integer;
declare variable prev_tf_id integer;
declare variable prev_ta_curcode char(5);
declare variable prev_tf_curcode char(5);
declare variable prev_ta_cur numeric (15,4);
declare variable prev_ta_nat numeric (15,4);
declare variable prev_tf_cur numeric (15,4);
declare variable prev_tf_nat numeric (15,4);
declare variable prev_ta_cur_plan numeric (15,4);
declare variable prev_ta_nat_plan numeric (15,4);
declare variable prev_tf_cur_plan numeric (15,4);
declare variable prev_tf_nat_plan numeric (15,4);
declare variable ta_anl1 integer;
declare variable ta_anl2 integer;
declare variable ta_anl3 integer;
declare variable ta_anl4 integer;
declare variable ta_anl5 integer;
declare variable tf_feat integer;
declare variable tf_anl1 integer;
declare variable tf_anl2 integer;
declare variable tf_anl3 integer;
declare variable tf_anl4 integer;
declare variable tf_anl5 integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: ?
 Назначение: Фин.учет: перенос остатков
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 27.09.2002 Кривопустов Введение плановых операций
 14.04.2003 Кривопустов Валюты добавлены в строки обороток
**********************************************************/
  /* check period */
  select p.datefrom, p.dateclose from finperiod p
  where p.id = :period_id
  into :begindate, :dateclose;
  if (dateclose is not null) then exception E_FINPERIOD_IS_CLOSED;
  /* find previous period, may be several, get first */
  for
    select p.id from finperiod p where p.dateto < :begindate
    order by p.datefrom desc /* !!! */
    into :prev_per_id
  do begin
    /* delete lines with empty turnover */
    for
      select ta.id
      from finturnacc ta
      where (ta.period_id = :period_id) and (ta.acc_id = :account_id) and
            (not exists (select * from finspec s
                         where (s.srcturnacc_id = ta.id) or (s.dstturnacc_id = ta.id) ))
      into :ta_id
    do begin
      delete from finturnfeat tf
      where (tf.finturnacc_id = :ta_id) and
            (not exists (select * from finspec s
                         where (s.srcturnfeat_id = tf.id) or (s.dstturnfeat_id = tf.id) ));

      delete from finturnacc ta
      where (ta.id = :ta_id) and
            (not exists (select * from finturnfeat tf where (tf.finturnacc_id = :ta_id)));
    end
    /* update opening balances with zero */
    for
      select ta.id
      from finturnacc ta
      where (ta.period_id = :period_id) and (ta.acc_id = :account_id)
      into :ta_id
    do begin
      update finturnfeat tf set
        tf.remnbegcur = 0,
        tf.remnbegnat = 0,
        tf.remnbegcurplan = 0,
        tf.remnbegnatplan = 0
      where (tf.finturnacc_id = :ta_id);

      update finturnacc ta set
        ta.remnbegcur = 0,
        ta.remnbegnat = 0,
        ta.remnbegcurplan = 0,
        ta.remnbegnatplan = 0
      where (ta.id = :ta_id);
    end
    /* carry forward */
    for
      select ta.id
      from finturnacc ta
      where (ta.period_id = :prev_per_id) and (ta.acc_id = :account_id)
      into :prev_ta_id
    do begin
      if (prev_ta_id is not null) then begin
        select ta.curcode, ta.anl1_id, ta.anl2_id, ta.anl3_id, ta.anl4_id, ta.anl5_id,
          ta.remnendnat, ta.remnendcur, ta.remnendnatplan, ta.remnendcurplan
        from fin_turnacc_load(:prev_ta_id) ta
        into :prev_ta_curcode, :ta_anl1, :ta_anl2, :ta_anl3, :ta_anl4, :ta_anl5,
          :prev_ta_nat, :prev_ta_cur, :prev_ta_nat_plan, :prev_ta_cur_plan;
        /* find corresponding finturnacc record in current period */
        ta_id = null;
        select ta.id
        from finturnacc ta
        where (ta.period_id = :period_id) and (ta.acc_id = :account_id) and
          ((ta.anl1_id = :ta_anl1) or ((ta.anl1_id is null) and (:ta_anl1 is null))) and
          ((ta.anl2_id = :ta_anl2) or ((ta.anl2_id is null) and (:ta_anl2 is null))) and
          ((ta.anl3_id = :ta_anl3) or ((ta.anl3_id is null) and (:ta_anl3 is null))) and
          ((ta.anl4_id = :ta_anl4) or ((ta.anl4_id is null) and (:ta_anl4 is null))) and
          ((ta.anl5_id = :ta_anl5) or ((ta.anl5_id is null) and (:ta_anl5 is null))) and
          ((ta.curcode = :prev_ta_curcode) or ((ta.curcode is null) and (:prev_ta_curcode is null)))
        into :ta_id;

        if (ta_id is null) then begin
          ta_id = gen_id(finturnacc_id_gen, 1);
          insert into finturnacc(id, period_id, curcode, acc_id, anl1_id, anl2_id, anl3_id, anl4_id, anl5_id, remnbegcur, remnbegnat, remnbegcurplan, remnbegnatplan)
          values (:ta_id, :period_id, :prev_ta_curcode, :account_id, :ta_anl1, :ta_anl2, :ta_anl3, :ta_anl4, :ta_anl5, :prev_ta_cur, :prev_ta_nat, :prev_ta_cur_plan, :prev_ta_nat_plan);
        end
        else
          update finturnacc ta set
            ta.remnbegcur = :prev_ta_cur,
            ta.remnbegnat = :prev_ta_nat,
            ta.remnbegcurplan = :prev_ta_cur_plan,
            ta.remnbegnatplan = :prev_ta_nat_plan
          where ta.id = :ta_id;

        /* carry forward finturnfeat */
        for
          select tf.id
          from finturnfeat tf
          where tf.finturnacc_id = :prev_ta_id
          into :prev_tf_id
        do begin
          if (prev_tf_id is not null) then begin
            select tf.curcode, tf.feat_id, tf.fanl1_id, tf.fanl2_id, tf.fanl3_id, tf.fanl4_id, tf.fanl5_id,
              tf.remnendnat, tf.remnendcur, tf.remnendnatplan, tf.remnendcurplan
            from fin_turnfeat_load(:prev_tf_id) tf
            into :prev_tf_curcode, :tf_feat, :tf_anl1, :tf_anl2, :tf_anl3, :tf_anl4, :tf_anl5,
              :prev_tf_nat, :prev_tf_cur, :prev_tf_nat_plan, :prev_tf_cur_plan;

            /* find corresponding finturnfeat record in current period */
            tf_id = null;
            select tf.id
            from finturnfeat tf
            where (tf.period_id = :period_id) and (tf.finturnacc_id = :ta_id) and
              (tf.feat_id = :tf_feat) and
              ((tf.fanl1_id = :tf_anl1) or ((tf.fanl1_id is null) and (:tf_anl1 is null))) and
              ((tf.fanl2_id = :tf_anl2) or ((tf.fanl2_id is null) and (:tf_anl2 is null))) and
              ((tf.fanl3_id = :tf_anl3) or ((tf.fanl3_id is null) and (:tf_anl3 is null))) and
              ((tf.fanl4_id = :tf_anl4) or ((tf.fanl4_id is null) and (:tf_anl4 is null))) and
              ((tf.fanl5_id = :tf_anl5) or ((tf.fanl5_id is null) and (:tf_anl5 is null))) and
              ((tf.curcode = :prev_tf_curcode) or ((tf.curcode is null) and (:prev_tf_curcode is null)))
            into :tf_id;

            if (tf_id is null) then begin
              tf_id = gen_id(finturnfeat_id_gen, 1);
              insert into finturnfeat(id, period_id, curcode, finturnacc_id, feat_id, fanl1_id, fanl2_id, fanl3_id, fanl4_id, fanl5_id, remnbegcur, remnbegnat, remnbegcurplan, remnbegnatplan)
              values (:tf_id, :period_id, :prev_tf_curcode, :ta_id, :tf_feat, :tf_anl1, :tf_anl2, :tf_anl3, :tf_anl4, :tf_anl5, :prev_tf_cur, :prev_tf_nat, :prev_tf_cur_plan, :prev_tf_nat_plan);
            end
            else
              update finturnfeat tf set
                tf.remnbegcur = :prev_tf_cur,
                tf.remnbegnat = :prev_tf_nat,
                tf.remnbegcurplan = :prev_tf_cur_plan,
                tf.remnbegnatplan = :prev_tf_nat_plan
              where tf.id = :tf_id;

          end
        end
      end
    end

    exit;
  end
END
^

ALTER PROCEDURE FIN_CLEAR_TURN (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER)
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
declare variable at_id integer;
declare variable p_id integer;
begin
  if (period1 is not null) then
    select p.datefrom from period p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from period p where p.id = :period2 into :date2;

  for
    select t.id, p.id
    from finturnacc t, finperiod p
    where (p.id = t.period_id) and
      ((p.datefrom >= :date1) or (:period1 is null)) and
      ((p.datefrom <= :date2) or (:period2 is null)) and
      (Round4(t.remnbegcur) = 0) and
      (Round4(t.remnbegnat) = 0) and
      (not exists (select * from finspec s
                   where (s.srcturnacc_id = t.id) or (s.dstturnacc_id = t.id) ))
    into :at_id, :p_id
  do begin
    delete from finturnfeat ft
    where (ft.period_id = :p_id) and
      (ft.finturnacc_id = :at_id) and
      (Round4(ft.remnbegcur) = 0) and
      (Round4(ft.remnbegnat) = 0) and
      (not exists (select * from finspec s
                   where (s.srcturnfeat_id = ft.id) or (s.dstturnfeat_id = ft.id) ));

    if (not exists(select * from finturnfeat ft where ft.finturnacc_id = :at_id)) then
      delete from finturnacc t
      where t.id = :at_id;
  end
end
^

ALTER PROCEDURE FIN_FIND_OPENED_PERIOD (
    OPERDATE TIMESTAMP)
RETURNS (
    PERIOD_ID INTEGER)
AS
declare variable dateclose  timestamp;
begin
  select p.id, p.dateclose from finperiod p
  where ( :operdate between p.datefrom and p.dateto )
  into :period_id, :dateclose;
  if ( period_id is null ) then exception e_invalid_finperiod;
  if (dateclose is not null) then exception e_finperiod_is_closed;
end
^

ALTER PROCEDURE FIN_FIND_PERIOD (
    OPERDATE TIMESTAMP)
RETURNS (
    PERIOD_ID INTEGER)
AS
begin
  select p.id from finperiod p
  where ( :operdate between p.datefrom and p.dateto )
  into :period_id;
  if ( period_id is null ) then exception e_invalid_finperiod;
end
^

ALTER PROCEDURE FIN_INCL_SPEC_IN_TURNACC (
    PERIOD_ID INTEGER,
    ACC_ID INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    INCOMECUR NUMERIC(15,4),
    INCOMENAT NUMERIC(15,4),
    OUTCOMECUR NUMERIC(15,4),
    OUTCOMENAT NUMERIC(15,4),
    USER_ID INTEGER,
    CURCODE CHAR(5))
RETURNS (
    TURN_ID INTEGER)
AS
declare variable real_id integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: включение проводки фин.операции в строку
   оборотной ведомости
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 14.04.2003 Кривопустов Введение валют в оборотку
**********************************************************/
/* find real record */
  select t.id from finturnacc t
  where (t.period_id = :period_id) and
        (t.acc_id = :acc_id) and 
        ((t.anl1_id = :anl1) or ((t.anl1_id is null)and(:anl1 is null))) and 
        ((t.anl2_id = :anl2) or ((t.anl2_id is null)and(:anl2 is null))) and 
        ((t.anl3_id = :anl3) or ((t.anl3_id is null)and(:anl3 is null))) and 
        ((t.anl4_id = :anl4) or ((t.anl4_id is null)and(:anl4 is null))) and 
        ((t.anl5_id = :anl5) or ((t.anl5_id is null)and(:anl5 is null))) and
        ((t.curcode = :curcode) or ((t.curcode is null)and(:curcode is null)))
  into :turn_id;  

  if (turn_id is null) then begin
    /* insert real record */
    turn_id = gen_id(finturnacc_id_gen,1); 
    insert into finturnacc(id, period_id, 
                           acc_id, anl1_id, anl2_id, anl3_id, anl4_id, anl5_id,
                           remnbegcur, remnbegnat, remnbegcurplan, remnbegnatplan,
                           curcode)
    values(:turn_id, :period_id, 
           :acc_id, :anl1, :anl2, :anl3, :anl4, :anl5,
           0, 0, 0, 0,
           :curcode);
  end
  suspend;
end
^

ALTER PROCEDURE FIN_INCL_SPEC_IN_TURNFEAT (
    PERIOD_ID INTEGER,
    FINTURNACC_ID INTEGER,
    FEAT_ID INTEGER,
    FANL1 INTEGER,
    FANL2 INTEGER,
    FANL3 INTEGER,
    FANL4 INTEGER,
    FANL5 INTEGER,
    INCOMECUR NUMERIC(15,4),
    INCOMENAT NUMERIC(15,4),
    OUTCOMECUR NUMERIC(15,4),
    OUTCOMENAT NUMERIC(15,4),
    CURCODE CHAR(5))
RETURNS (
    TURN_ID INTEGER)
AS
declare variable a integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: включение проводки фин.операции в строку
   оборотной ведомости по признакам
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 14.04.2003 Кривопустов Введение валют в оборотку
**********************************************************/
  a = dm('Enter FIN_INCL_SPEC_IN_TURNFEAT');
  select t.id from finturnfeat t
  where (t.period_id = :period_id) and
        (t.finturnacc_id = :finturnacc_id) and 
        ((t.feat_id = :feat_id) or ((t.feat_id is null)and(:feat_id is null))) and 
        ((t.fanl1_id = :fanl1) or ((t.fanl1_id is null)and(:fanl1 is null))) and 
        ((t.fanl2_id = :fanl2) or ((t.fanl2_id is null)and(:fanl2 is null))) and 
        ((t.fanl3_id = :fanl3) or ((t.fanl3_id is null)and(:fanl3 is null))) and 
        ((t.fanl4_id = :fanl4) or ((t.fanl4_id is null)and(:fanl4 is null))) and 
        ((t.fanl5_id = :fanl5) or ((t.fanl5_id is null)and(:fanl5 is null)))  and
        ((t.curcode = :curcode) or ((t.curcode is null)and(:curcode is null)))
  into :turn_id;  
  if (turn_id is null) then begin
    turn_id = gen_id(finturnfeat_id_gen,1);
    insert into finturnfeat(id, period_id, finturnacc_id, 
                           feat_id, fanl1_id, fanl2_id, fanl3_id, fanl4_id, fanl5_id,
                           remnbegcur, remnbegnat, remnbegcurplan, remnbegnatplan,
                           curcode)
    values(:turn_id, :period_id, :finturnacc_id, 
           :feat_id, :fanl1, :fanl2, :fanl3, :fanl4, :fanl5,
           0, 0, 0, 0,
           :curcode);
  end
  a = dm('Exit FIN_INCL_SPEC_IN_TURNFEAT');
  suspend;
end
^

ALTER PROCEDURE FIN_TURNACC_LOAD (
    ID INTEGER)
RETURNS (
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    CURCODE CHAR(5),
    ACC_ID INTEGER,
    ACC_CODE CHAR(20),
    ACCFOLDER_ID INTEGER,
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(20),
    ANL2_CODE CHAR(20),
    ANL3_CODE CHAR(20),
    ANL4_CODE CHAR(20),
    ANL5_CODE CHAR(20),
    REMNBEGNAT NUMERIC(15,4),
    REMNBEGCUR NUMERIC(15,4),
    INCOMENAT NUMERIC(15,4),
    INCOMECUR NUMERIC(15,4),
    OUTCOMENAT NUMERIC(15,4),
    OUTCOMECUR NUMERIC(15,4),
    REMNENDNAT NUMERIC(15,4),
    REMNENDCUR NUMERIC(15,4),
    REMNBEGNATPLAN NUMERIC(15,4),
    REMNBEGCURPLAN NUMERIC(15,4),
    INCOMENATPLAN NUMERIC(15,4),
    INCOMECURPLAN NUMERIC(15,4),
    OUTCOMENATPLAN NUMERIC(15,4),
    OUTCOMECURPLAN NUMERIC(15,4),
    REMNENDNATPLAN NUMERIC(15,4),
    REMNENDCURPLAN NUMERIC(15,4))
AS
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 17.05.2002
 Назначение: Фин.учет: загрузка строки оборотки по счетам
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 26.09.2002 Кривопустов Введение плановых операций
 14.04.2003 Кривопустов Валюты добавлены в строки обороток
**********************************************************/
  select ta.period_id, p.pname, ta.curcode, ta.acc_id, a.code, a.folder_id,
    ta.anl1_id, ta.anl2_id, ta.anl3_id, ta.anl4_id, ta.anl5_id,
    an1.code, an2.code, an3.code, an4.code, an5.code,
    ta.remnbegcur, ta.remnbegnat, ta.remnbegcurplan, ta.remnbegnatplan
  from finturnacc ta
    left join finperiod p on p.id = ta.period_id
    left join finaccount a on a.id = ta.acc_id
    left join finanl an1 on an1.id = ta.anl1_id
    left join finanl an2 on an2.id = ta.anl2_id
    left join finanl an3 on an3.id = ta.anl3_id
    left join finanl an4 on an4.id = ta.anl4_id
    left join finanl an5 on an5.id = ta.anl5_id
  where (ta.id = :id)
  into :period_id, :pname, :curcode, :acc_id, :acc_code, :accfolder_id,
    :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
    :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code,
    :remnbegcur, :remnbegnat, :remnbegcurplan, :remnbegnatplan;

  if (remnbegcur is null) then remnbegcur = 0;
  if (remnbegnat is null) then remnbegnat = 0;
  if (remnbegcurplan is null) then remnbegcurplan = 0;
  if (remnbegnatplan is null) then remnbegnatplan = 0;

  /*fact*/
  select sum(fs.sumcur), sum(fs.sumnat)
  from finspec fs
  where (fs.dstturnacc_id = :id) and (fs.planned = 0)
  into :incomecur, :incomenat;

  if (incomecur is null) then incomecur = 0;
  if (incomenat is null) then incomenat = 0;

  select sum(fs.sumcur), sum(fs.sumnat)
  from finspec fs
  where (fs.srcturnacc_id = :id) and (fs.planned = 0)
  into :outcomecur, :outcomenat;

  if (outcomecur is null) then outcomecur = 0;
  if (outcomenat is null) then outcomenat = 0;

  remnendcur = remnbegcur + incomecur - outcomecur;
  remnendnat = remnbegnat + incomenat - outcomenat;

  /*plan*/
  select sum(fs.sumcur), sum(fs.sumnat)
  from finspec fs
  where (fs.dstturnacc_id = :id) and (fs.planned = 1)
  into :incomecurplan, :incomenatplan;

  if (incomecurplan is null) then incomecurplan = 0;
  if (incomenatplan is null) then incomenatplan = 0;

  select sum(fs.sumcur), sum(fs.sumnat)
  from finspec fs
  where (fs.srcturnacc_id = :id) and (fs.planned = 1)
  into :outcomecurplan, :outcomenatplan;

  if (outcomecurplan is null) then outcomecurplan = 0;
  if (outcomenatplan is null) then outcomenatplan = 0;

  remnendcurplan = remnbegcurplan + incomecurplan - outcomecurplan;
  remnendnatplan = remnbegnatplan + incomenatplan - outcomenatplan;

  suspend;
end
^

ALTER PROCEDURE FIN_TURNACC_SELECT (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER,
    ACCOUNT_FOLDER INTEGER,
    ACCOUNT INTEGER,
    INCL_FACT INTEGER,
    INCL_PLAN INTEGER,
    INCL_DIFF INTEGER)
RETURNS (
    ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    DATEFROM TIMESTAMP,
    DATETO TIMESTAMP,
    ACC_ID INTEGER,
    ACC_CODE CHAR(20),
    ACCFOLDER_ID INTEGER,
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(20),
    ANL2_CODE CHAR(20),
    ANL3_CODE CHAR(20),
    ANL4_CODE CHAR(20),
    ANL5_CODE CHAR(20),
    REMNBEGNAT NUMERIC(15,4),
    REMNBEGCUR NUMERIC(15,4),
    INCOMENAT NUMERIC(15,4),
    INCOMECUR NUMERIC(15,4),
    OUTCOMENAT NUMERIC(15,4),
    OUTCOMECUR NUMERIC(15,4),
    REMNENDNAT NUMERIC(15,4),
    REMNENDCUR NUMERIC(15,4),
    KIND INTEGER,
    GROUPNUM INTEGER,
    CURCODE CHAR(5))
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
declare variable remnbegnatplan numeric(15,4);
declare variable remnbegcurplan numeric(15,4);
declare variable incomenatplan numeric(15,4);
declare variable incomecurplan numeric(15,4);
declare variable outcomenatplan numeric(15,4);
declare variable outcomecurplan numeric(15,4);
declare variable remnendnatplan numeric(15,4);
declare variable remnendcurplan numeric(15,4);
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 17.05.2002
 Назначение: Фин.учет: оборотка по счетам
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 24.09.2002 Кривопустов Введение плановых операций
 24.03.2003 Кривопустов Разделение валют
 14.04.2003 Кривопустов Валюты добавлены в строки обороток
**********************************************************/
  if (period1 is not null) then
    select p.datefrom from finperiod p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from finperiod p where p.id = :period2 into :date2;

  groupnum = 1;

  for
    select ta.id, ta.period_id, p.pname, p.datefrom, p.dateto, ta.curcode,
      ta.acc_id, a.code, a.folder_id,
      ta.anl1_id, ta.anl2_id, ta.anl3_id, ta.anl4_id, ta.anl5_id,
      an1.code, an2.code, an3.code, an4.code, an5.code,
      ta.remnbegcur, ta.remnbegnat, ta.remnbegcurplan, ta.remnbegnatplan
    from finturnacc ta
      left join finperiod p on p.id = ta.period_id
      left join finaccount a on a.id = ta.acc_id
      left join finanl an1 on an1.id = ta.anl1_id
      left join finanl an2 on an2.id = ta.anl2_id
      left join finanl an3 on an3.id = ta.anl3_id
      left join finanl an4 on an4.id = ta.anl4_id
      left join finanl an5 on an5.id = ta.anl5_id
    where
      ((p.datefrom >= :date1) or (:period1 is null) or (:period1 = 0)) and
      ((p.datefrom <= :date2) or (:period2 is null) or (:period2 = 0)) and
      ((a.folder_id = :account_folder) or (:account_folder is null) or (:account_folder = 0)) and
      ((ta.acc_id = :account) or (:account is null) or (:account = 0))
    into :id, :period_id, :pname, :datefrom, :dateto, :curcode,
      :acc_id, :acc_code, :accfolder_id,
      :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
      :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code,
      :remnbegcur, :remnbegnat, :remnbegcurplan, :remnbegnatplan
  do begin
    if ((incl_fact = 1) or (incl_diff = 1)) then begin
      if (remnbegcur is null) then remnbegcur = 0;
      if (remnbegnat is null) then remnbegnat = 0;

      select sum(fs.sumcur), sum(fs.sumnat)
      from finspec fs
      where (fs.dstturnacc_id = :id) and (fs.planned = 0)
      into :incomecur, :incomenat;
    
      if (incomecur is null) then incomecur = 0;
      if (incomenat is null) then incomenat = 0;
    
      select sum(fs.sumcur), sum(fs.sumnat)
      from finspec fs
      where (fs.srcturnacc_id = :id) and (fs.planned = 0)
      into :outcomecur, :outcomenat;
    
      if (outcomecur is null) then outcomecur = 0;
      if (outcomenat is null) then outcomenat = 0;
    
      remnendcur = remnbegcur + incomecur - outcomecur;
      remnendnat = remnbegnat + incomenat - outcomenat;
    end

    if ((incl_plan = 1) or (incl_diff = 1)) then begin
      if (remnbegcurplan is null) then remnbegcurplan = 0;
      if (remnbegnatplan is null) then remnbegnatplan = 0;

      select sum(fs.sumcur), sum(fs.sumnat)
      from finspec fs
      where (fs.dstturnacc_id = :id) and (fs.planned = 1)
      into :incomecurplan, :incomenatplan;
    
      if (incomecurplan is null) then incomecurplan = 0;
      if (incomenatplan is null) then incomenatplan = 0;
    
      select sum(fs.sumcur), sum(fs.sumnat)
      from finspec fs
      where (fs.srcturnacc_id = :id) and (fs.planned = 1)
      into :outcomecurplan, :outcomenatplan;
    
      if (outcomecurplan is null) then outcomecurplan = 0;
      if (outcomenatplan is null) then outcomenatplan = 0;
    
      remnendcurplan = remnbegcurplan + incomecurplan - outcomecurplan;
      remnendnatplan = remnbegnatplan + incomenatplan - outcomenatplan;
    end

    if (incl_fact = 1) then begin
      kind = 0;
      suspend;
    end

    if (incl_diff = 1) then begin
      remnbegnat = remnbegnat - remnbegnatplan;
      remnbegcur = remnbegcur - remnbegcurplan;
      incomenat = incomenat - incomenatplan;
      incomecur = incomecur - incomecurplan;
      outcomenat = outcomenat - outcomenatplan;
      outcomecur = outcomecur - outcomecurplan;
      remnendnat = remnendnat - remnendnatplan;
      remnendcur = remnendcur - remnendcurplan;
      kind = 2;
      suspend;
    end

    if (incl_plan = 1) then begin
      remnbegnat = remnbegnatplan;
      remnbegcur = remnbegcurplan;
      incomenat = incomenatplan;
      incomecur = incomecurplan;
      outcomenat = outcomenatplan;
      outcomecur = outcomecurplan;
      remnendnat = remnendnatplan;
      remnendcur = remnendcurplan;
      kind = 1;
      suspend;
    end
    groupnum = groupnum + 1;
  end
END
^

ALTER PROCEDURE FIN_TURNFEAT_LOAD (
    ID INTEGER)
RETURNS (
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    CURCODE CHAR(5),
    ACC_ID INTEGER,
    ACC_CODE CHAR(20),
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(20),
    ANL2_CODE CHAR(20),
    ANL3_CODE CHAR(20),
    ANL4_CODE CHAR(20),
    ANL5_CODE CHAR(20),
    FEAT_ID INTEGER,
    FEAT_CODE CHAR(20),
    FEATFOLDER_ID INTEGER,
    FANL1_ID INTEGER,
    FANL2_ID INTEGER,
    FANL3_ID INTEGER,
    FANL4_ID INTEGER,
    FANL5_ID INTEGER,
    FANL1_CODE CHAR(20),
    FANL2_CODE CHAR(20),
    FANL3_CODE CHAR(20),
    FANL4_CODE CHAR(20),
    FANL5_CODE CHAR(20),
    REMNBEGNAT NUMERIC(15,4),
    REMNBEGCUR NUMERIC(15,4),
    INCOMENAT NUMERIC(15,4),
    INCOMECUR NUMERIC(15,4),
    OUTCOMENAT NUMERIC(15,4),
    OUTCOMECUR NUMERIC(15,4),
    REMNENDNAT NUMERIC(15,4),
    REMNENDCUR NUMERIC(15,4),
    REMNBEGNATPLAN NUMERIC(15,4),
    REMNBEGCURPLAN NUMERIC(15,4),
    INCOMENATPLAN NUMERIC(15,4),
    INCOMECURPLAN NUMERIC(15,4),
    OUTCOMENATPLAN NUMERIC(15,4),
    OUTCOMECURPLAN NUMERIC(15,4),
    REMNENDNATPLAN NUMERIC(15,4),
    REMNENDCURPLAN NUMERIC(15,4))
AS
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 17.05.2002
 Назначение: Фин.учет: загрузка строки оборотки по признакам
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 26.09.2002 Кривопустов Введение плановых операций
 14.04.2003 Кривопустов Валюты добавлены в строки обороток
**********************************************************/
  select tf.period_id, p.pname, tf.curcode, tf.feat_id, f.code, f.folder_id,
    tf.fanl1_id, tf.fanl2_id, tf.fanl3_id, tf.fanl4_id, tf.fanl5_id,
    fan1.code, fan2.code, fan3.code, fan4.code, fan5.code,
    ta.acc_id, a.code,
    ta.anl1_id, ta.anl2_id, ta.anl3_id, ta.anl4_id, ta.anl5_id,
    an1.code, an2.code, an3.code, an4.code, an5.code,
    tf.remnbegcur, tf.remnbegnat, tf.remnbegcurplan, tf.remnbegnatplan
  from finturnfeat tf
    left join finperiod p on p.id = tf.period_id
    left join finaccount f on f.id = tf.feat_id
    left join finanl fan1 on fan1.id = tf.fanl1_id
    left join finanl fan2 on fan2.id = tf.fanl2_id
    left join finanl fan3 on fan3.id = tf.fanl3_id
    left join finanl fan4 on fan4.id = tf.fanl4_id
    left join finanl fan5 on fan5.id = tf.fanl5_id
    left join finturnacc ta on ta.id = tf.finturnacc_id
    left join finaccount a on a.id = ta.acc_id
    left join finanl an1 on an1.id = ta.anl1_id
    left join finanl an2 on an2.id = ta.anl2_id
    left join finanl an3 on an3.id = ta.anl3_id
    left join finanl an4 on an4.id = ta.anl4_id
    left join finanl an5 on an5.id = ta.anl5_id
  where (tf.id = :id)
  into :period_id, :pname, :curcode, :feat_id, :feat_code, :featfolder_id,
    :fanl1_id, :fanl2_id, :fanl3_id, :fanl4_id, :fanl5_id,
    :fanl1_code, :fanl2_code, :fanl3_code, :fanl4_code, :fanl5_code,
    :acc_id, :acc_code,
    :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
    :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code,
    :remnbegcur, :remnbegnat, :remnbegcurplan, :remnbegnatplan;

  if (remnbegcur is null) then remnbegcur = 0;
  if (remnbegnat is null) then remnbegnat = 0;
  if (remnbegcurplan is null) then remnbegcurplan = 0;
  if (remnbegnatplan is null) then remnbegnatplan = 0;

  /*fact*/
  select sum(fs.sumcur), sum(fs.sumnat)
  from finspec fs
  where (fs.dstturnfeat_id = :id) and (fs.planned = 0)
  into :incomecur, :incomenat;

  if (incomecur is null) then incomecur = 0;
  if (incomenat is null) then incomenat = 0;

  select sum(fs.sumcur), sum(fs.sumnat)
  from finspec fs
  where (fs.srcturnfeat_id = :id) and (fs.planned = 0)
  into :outcomecur, :outcomenat;

  if (outcomecur is null) then outcomecur = 0;
  if (outcomenat is null) then outcomenat = 0;

  remnendcur = remnbegcur + incomecur - outcomecur;
  remnendnat = remnbegnat + incomenat - outcomenat;

  /*plan*/
  select sum(fs.sumcur), sum(fs.sumnat)
  from finspec fs
  where (fs.dstturnfeat_id = :id) and (fs.planned = 1)
  into :incomecurplan, :incomenatplan;

  if (incomecurplan is null) then incomecurplan = 0;
  if (incomenatplan is null) then incomenatplan = 0;

  select sum(fs.sumcur), sum(fs.sumnat)
  from finspec fs
  where (fs.srcturnfeat_id = :id) and (fs.planned = 1)
  into :outcomecurplan, :outcomenatplan;

  if (outcomecurplan is null) then outcomecurplan = 0;
  if (outcomenatplan is null) then outcomenatplan = 0;

  remnendcurplan = remnbegcurplan + incomecurplan - outcomecurplan;
  remnendnatplan = remnbegnatplan + incomenatplan - outcomenatplan;

  suspend;
end
^

ALTER PROCEDURE FIN_TURNFEAT_SELECT (
    PERIOD1 INTEGER,
    PERIOD2 INTEGER,
    ACCOUNT_ID INTEGER,
    FEATURE_ID INTEGER,
    FEATURE_FOLDER_ID INTEGER,
    INCL_FACT INTEGER,
    INCL_PLAN INTEGER,
    INCL_DIFF INTEGER)
RETURNS (
    ID INTEGER,
    PERIOD_ID INTEGER,
    PNAME CHAR(20),
    DATEFROM TIMESTAMP,
    DATETO TIMESTAMP,
    ACC_ID INTEGER,
    ACC_CODE CHAR(20),
    ANL1_ID INTEGER,
    ANL2_ID INTEGER,
    ANL3_ID INTEGER,
    ANL4_ID INTEGER,
    ANL5_ID INTEGER,
    ANL1_CODE CHAR(20),
    ANL2_CODE CHAR(20),
    ANL3_CODE CHAR(20),
    ANL4_CODE CHAR(20),
    ANL5_CODE CHAR(20),
    FEAT_ID INTEGER,
    FEAT_CODE CHAR(20),
    FEATFOLDER_ID INTEGER,
    FANL1_ID INTEGER,
    FANL2_ID INTEGER,
    FANL3_ID INTEGER,
    FANL4_ID INTEGER,
    FANL5_ID INTEGER,
    FANL1_CODE CHAR(20),
    FANL2_CODE CHAR(20),
    FANL3_CODE CHAR(20),
    FANL4_CODE CHAR(20),
    FANL5_CODE CHAR(20),
    REMNBEGNAT NUMERIC(15,4),
    REMNBEGCUR NUMERIC(15,4),
    INCOMENAT NUMERIC(15,4),
    INCOMECUR NUMERIC(15,4),
    OUTCOMENAT NUMERIC(15,4),
    OUTCOMECUR NUMERIC(15,4),
    REMNENDNAT NUMERIC(15,4),
    REMNENDCUR NUMERIC(15,4),
    KIND INTEGER,
    GROUPNUM INTEGER,
    CURCODE CHAR(5))
AS
declare variable date1 timestamp;
declare variable date2 timestamp;
declare variable remnbegnatplan numeric(15,4);
declare variable remnbegcurplan numeric(15,4);
declare variable incomenatplan numeric(15,4);
declare variable incomecurplan numeric(15,4);
declare variable outcomenatplan numeric(15,4);
declare variable outcomecurplan numeric(15,4);
declare variable remnendnatplan numeric(15,4);
declare variable remnendcurplan numeric(15,4);
BEGIN
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 17.05.2002
 Назначение: Фин.учет: оборотка по признакам
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 24.09.2002 Кривопустов Введение плановых операций
 24.03.2003 Кривопустов Разделение валют
 14.04.2003 Кривопустов Валюты добавлены в строки обороток
**********************************************************/
  if (period1 is not null) then
    select p.datefrom from finperiod p where p.id = :period1 into :date1;

  if (period2 is not null) then
    select p.datefrom from finperiod p where p.id = :period2 into :date2;

  groupnum = 1;
  for
    select tf.id, tf.period_id, p.pname, p.datefrom, p.dateto, tf.curcode,
      tf.feat_id, f.code, f.folder_id,
      tf.fanl1_id, tf.fanl2_id, tf.fanl3_id, tf.fanl4_id, tf.fanl5_id,
      fan1.code, fan2.code, fan3.code, fan4.code, fan5.code,
      ta.acc_id, a.code,
      ta.anl1_id, ta.anl2_id, ta.anl3_id, ta.anl4_id, ta.anl5_id,
      an1.code, an2.code, an3.code, an4.code, an5.code,
      tf.remnbegcur, tf.remnbegnat
    from finturnfeat tf
      left join finperiod p on p.id = tf.period_id
      left join finaccount f on f.id = tf.feat_id
      left join finanl fan1 on fan1.id = tf.fanl1_id
      left join finanl fan2 on fan2.id = tf.fanl2_id
      left join finanl fan3 on fan3.id = tf.fanl3_id
      left join finanl fan4 on fan4.id = tf.fanl4_id
      left join finanl fan5 on fan5.id = tf.fanl5_id
      left join finturnacc ta on ta.id = tf.finturnacc_id
      left join finaccount a on a.id = ta.acc_id
      left join finanl an1 on an1.id = ta.anl1_id
      left join finanl an2 on an2.id = ta.anl2_id
      left join finanl an3 on an3.id = ta.anl3_id
      left join finanl an4 on an4.id = ta.anl4_id
      left join finanl an5 on an5.id = ta.anl5_id
    where
      ((p.datefrom >= :date1) or (:period1 is null) or (:period1 = 0)) and
      ((p.datefrom <= :date2) or (:period2 is null) or (:period2 = 0)) and
      ((ta.acc_id = :account_id) or (:account_id is null) or (:account_id = 0)) and
      ((f.folder_id = :feature_folder_id) or (:feature_folder_id is null) or (:feature_folder_id = 0)) and
      ((tf.feat_id = :feature_id) or (:feature_id is null) or (:feature_id = 0))
    into :id, :period_id, :pname, :datefrom, :dateto, :curcode,
      :feat_id, :feat_code, :featfolder_id,
      :fanl1_id, :fanl2_id, :fanl3_id, :fanl4_id, :fanl5_id,
      :fanl1_code, :fanl2_code, :fanl3_code, :fanl4_code, :fanl5_code,
      :acc_id, :acc_code,
      :anl1_id, :anl2_id, :anl3_id, :anl4_id, :anl5_id,
      :anl1_code, :anl2_code, :anl3_code, :anl4_code, :anl5_code,
      :remnbegcur, :remnbegnat
  do begin
    if ((incl_fact = 1) or (incl_diff = 1)) then begin
      if (remnbegcur is null) then remnbegcur = 0;
      if (remnbegnat is null) then remnbegnat = 0;

      select sum(fs.sumcur), sum(fs.sumnat)
      from finspec fs
      where (fs.dstturnfeat_id = :id) and (fs.planned = 0)
      into :incomecur, :incomenat;
    
      if (incomecur is null) then incomecur = 0;
      if (incomenat is null) then incomenat = 0;
    
      select sum(fs.sumcur), sum(fs.sumnat)
      from finspec fs
      where (fs.srcturnfeat_id = :id) and (fs.planned = 0)
      into :outcomecur, :outcomenat;
    
      if (outcomecur is null) then outcomecur = 0;
      if (outcomenat is null) then outcomenat = 0;
    
      remnendcur = remnbegcur + incomecur - outcomecur;
      remnendnat = remnbegnat + incomenat - outcomenat;
    end

    if ((incl_plan = 1) or (incl_diff = 1)) then begin
      if (remnbegcurplan is null) then remnbegcurplan = 0;
      if (remnbegnatplan is null) then remnbegnatplan = 0;

      select sum(fs.sumcur), sum(fs.sumnat)
      from finspec fs
      where (fs.dstturnfeat_id = :id) and (fs.planned = 1)
      into :incomecurplan, :incomenatplan;
    
      if (incomecurplan is null) then incomecurplan = 0;
      if (incomenatplan is null) then incomenatplan = 0;
    
      select sum(fs.sumcur), sum(fs.sumnat)
      from finspec fs
      where (fs.srcturnfeat_id = :id) and (fs.planned = 1)
      into :outcomecurplan, :outcomenatplan;
    
      if (outcomecurplan is null) then outcomecurplan = 0;
      if (outcomenatplan is null) then outcomenatplan = 0;
    
      remnendcurplan = remnbegcurplan + incomecurplan - outcomecurplan;
      remnendnatplan = remnbegnatplan + incomenatplan - outcomenatplan;
    end

    if (incl_fact = 1) then begin
      kind = 0;
      suspend;
    end

    if (incl_diff = 1) then begin
      remnbegnat = remnbegnat - remnbegnatplan;
      remnbegcur = remnbegcur - remnbegcurplan;
      incomenat = incomenat - incomenatplan;
      incomecur = incomecur - incomecurplan;
      outcomenat = outcomenat - outcomenatplan;
      outcomecur = outcomecur - outcomecurplan;
      remnendnat = remnendnat - remnendnatplan;
      remnendcur = remnendcur - remnendcurplan;
      kind = 2;
      suspend;
    end

    if (incl_plan = 1) then begin
      remnbegnat = remnbegnatplan;
      remnbegcur = remnbegcurplan;
      incomenat = incomenatplan;
      incomecur = incomecurplan;
      outcomenat = outcomenatplan;
      outcomecur = outcomecurplan;
      remnendnat = remnendnatplan;
      remnendcur = remnendcurplan;
      kind = 1;
      suspend;
    end
    groupnum = groupnum + 1;
  end
END
^

ALTER PROCEDURE FIN_UPDATE_TURN (
    FINOPER_ID INTEGER,
    PERIOD_ID INTEGER,
    USER_ID INTEGER,
    CURCODE CHAR(5))
AS
declare variable id integer;
declare variable id1 integer;
declare variable parent_id integer;
declare variable srcacc integer;
declare variable dstacc integer;
declare variable srcanl1 integer;
declare variable srcanl2 integer;
declare variable srcanl3 integer;
declare variable srcanl4 integer;
declare variable srcanl5 integer;
declare variable dstanl1 integer;
declare variable dstanl2 integer;
declare variable dstanl3 integer;
declare variable dstanl4 integer;
declare variable dstanl5 integer;
declare variable feat integer;
declare variable fanl1 integer;
declare variable fanl2 integer;
declare variable fanl3 integer;
declare variable fanl4 integer;
declare variable fanl5 integer;
declare variable sumnat numeric(15,4);
declare variable sumcur numeric(15,4);
declare variable srcturnacc_id integer;
declare variable dstturnacc_id integer;
declare variable turnfeat_id integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: обновление финансовых оборотных ведомостей
   после изменения операции
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 14.04.2003 Кривопустов Введение валют в оборотку
**********************************************************/
  for
    select s.id, s.parent_id, s.srcacc_id, s.dstacc_id,
      s.srcanl1_id, s.srcanl2_id, s.srcanl3_id, s.srcanl4_id, s.srcanl5_id,
      s.dstanl1_id, s.dstanl2_id, s.dstanl3_id, s.dstanl4_id, s.dstanl5_id,
      s.sumnat, s.sumcur
    from finspec s 
    where (s.finoper_id = :finoper_id) and (s.parent_id is null)
    into :id, :parent_id, :srcacc, :dstacc,
      :srcanl1, :srcanl2, :srcanl3, :srcanl4, :srcanl5,
      :dstanl1, :dstanl2, :dstanl3, :dstanl4, :dstanl5, 
      :sumnat, :sumcur
  do begin
    if (srcacc is not null) then begin
      execute procedure fin_incl_spec_in_turnacc(:period_id, :srcacc,
                :srcanl1, :srcanl2, :srcanl3, :srcanl4, :srcanl5,
                0, 0, :sumcur, :sumnat, :user_id, :curcode)
      returning_values :srcturnacc_id;
      update finspec s set srcturnacc_id = :srcturnacc_id where s.id = :id;     
    end
    else
      update finspec s set srcturnacc_id = null where s.id = :id;
      
    if (dstacc is not null) then begin
      execute procedure fin_incl_spec_in_turnacc(:period_id, :dstacc,
                :dstanl1, :dstanl2, :dstanl3, :dstanl4, :dstanl5, 
                :sumcur, :sumnat, 0, 0, :user_id, :curcode)
      returning_values :dstturnacc_id;    
      update finspec s set dstturnacc_id = :dstturnacc_id where s.id = :id;     
    end
    else
      update finspec s set dstturnacc_id = null where s.id = :id; 
        
    /* cycle for features */
    for
      select s.id, s.srcacc_id, 
        s.srcanl1_id, s.srcanl2_id, s.srcanl3_id, s.srcanl4_id, s.srcanl5_id,
        s.sumnat, s.sumcur  
      from finspec s 
      where s.parent_id = :id
      into :id1, :feat, 
           :fanl1, :fanl2, :fanl3, :fanl4, :fanl5,
           :sumnat, :sumcur
    do begin
    
      if (srcacc is not null) then begin
        execute procedure fin_incl_spec_in_turnfeat(:period_id, :srcturnacc_id,
                  :feat, :fanl1, :fanl2, :fanl3, :fanl4, :fanl5,  
                  0, 0, :sumcur, :sumnat, :curcode)
        returning_values :turnfeat_id;
        update finspec s set srcturnfeat_id = :turnfeat_id where s.id = :id1;     
      end
      else
        update finspec s set srcturnfeat_id = null where s.id = :id1;      
        
      if (dstacc is not null) then begin
        execute procedure fin_incl_spec_in_turnfeat(:period_id, :dstturnacc_id,
                  :feat, :fanl1, :fanl2, :fanl3, :fanl4, :fanl5,  
                  :sumcur, :sumnat, 0, 0, :curcode)
        returning_values :turnfeat_id;
        update finspec s set dstturnfeat_id = :turnfeat_id where s.id = :id1;     
      end
      else
        update finspec s set dstturnfeat_id = null where s.id = :id1;     
        
    end
  end
END
^

ALTER PROCEDURE FUEL_COPYDETAIL_DENSITY (
    SRCHEAD_ID INTEGER,
    DSTHEAD_ID INTEGER)
AS
begin
  insert into fuel_density_spec (
    id,
    head_id,
    date_time,
    tank_id,
    fuel_id,
    density,
    temperature,
    operator_id)
  select gen_id(fuel_density_spec_id_gen,1),
    :dsthead_id,
    s.date_time,
    s.tank_id,
    s.fuel_id,
    s.density,
    s.temperature,
    s.operator_id
  from fuel_density_spec s
  where s.head_id = :srchead_id;
end
^

ALTER PROCEDURE GET_DATE (
    ADATE TIMESTAMP)
RETURNS (
    RDATE TIMESTAMP)
AS
begin
  rdate = adate;
  suspend;
end
^

ALTER PROCEDURE GET_DOUBLE (
    ADOUBLE DOUBLE PRECISION)
RETURNS (
    RDOUBLE DOUBLE PRECISION)
AS
begin
  rdouble = adouble;
  suspend;
end
^

ALTER PROCEDURE ITDBEGIN
AS
 declare variable Dbg integer;
begin
 Dbg = DB();
end
^

ALTER PROCEDURE ITDEND
AS
 declare variable Dbg integer;
begin
 Dbg = DE();
end
^

ALTER PROCEDURE MF_P_COPY_BOM_TO_JOB (
    BOM_ID INTEGER,
    JOB_ID INTEGER)
AS
DECLARE VARIABLE BOM_ROUTE_ID INTEGER;
DECLARE VARIABLE JOB_ROUTE_ID INTEGER;
DECLARE VARIABLE BOM_RESOURCE_ID INTEGER;
DECLARE VARIABLE JOB_RESOURCE_ID INTEGER;
DECLARE VARIABLE OPER_NUM INTEGER;
DECLARE VARIABLE DESCRIPTION VARCHAR(256);
DECLARE VARIABLE EFF_ON_DATE TIMESTAMP;
DECLARE VARIABLE EFF_OFF_DATE TIMESTAMP;
DECLARE VARIABLE WC_ID INTEGER;
DECLARE VARIABLE EFFICIENCY NUMERIC(15,5);
DECLARE VARIABLE MOVE_TICKS NUMERIC(18,0);
DECLARE VARIABLE MOVE_TIME_UM INTEGER;
DECLARE VARIABLE SETUP_TICKS NUMERIC(18,0);
DECLARE VARIABLE SETUP_TIME_UM INTEGER;
DECLARE VARIABLE RUN_TICKS NUMERIC(18,0);
DECLARE VARIABLE RUN_TIME_UM INTEGER;
DECLARE VARIABLE SCHED_TICKS NUMERIC(18,0);
DECLARE VARIABLE SCHED_TIME_UM INTEGER;
DECLARE VARIABLE SCHED_OFFSET_TICKS NUMERIC(18,0);
DECLARE VARIABLE SCHED_OFFSET_TIME_UM INTEGER;
DECLARE VARIABLE QUEUE_TICKS NUMERIC(18,0);
DECLARE VARIABLE QUEUE_TIME_UM INTEGER;
DECLARE VARIABLE CONTROL_POINT_FLAG SMALLINT;
DECLARE VARIABLE STD_COST_DETAIL_ID INTEGER;
DECLARE VARIABLE COMMENT VARCHAR(256);
DECLARE VARIABLE RESOURCE_GROUP_ID INTEGER;
DECLARE VARIABLE RESOURCE_TYPE SMALLINT;
DECLARE VARIABLE TIME_SEQUENCE INTEGER;
begin
  for
    select ID, OPER_NUM, DESCRIPTION, EFF_ON_DATE, EFF_OFF_DATE, WC_ID,
      EFFICIENCY, MOVE_TICKS, MOVE_TIME_UM, SETUP_TICKS, SETUP_TIME_UM, RUN_TICKS,
      RUN_TIME_UM, SCHED_TICKS, SCHED_TIME_UM, SCHED_OFFSET_TICKS, SCHED_OFFSET_TIME_UM,
      QUEUE_TICKS, QUEUE_TIME_UM, CONTROL_POINT_FLAG, STD_COST_DETAIL_ID, COMMENT
    from MF_BOM_ROUTE
    where BOM_ID = :BOM_ID
    into :BOM_ROUTE_ID, :OPER_NUM, :DESCRIPTION, :EFF_ON_DATE, :EFF_OFF_DATE, :WC_ID,
      :EFFICIENCY, :MOVE_TICKS, :MOVE_TIME_UM, :SETUP_TICKS, :SETUP_TIME_UM,
      :RUN_TICKS, :RUN_TIME_UM, :SCHED_TICKS, :SCHED_TIME_UM, :SCHED_OFFSET_TICKS,
      :SCHED_OFFSET_TIME_UM, :QUEUE_TICKS, :QUEUE_TIME_UM, :CONTROL_POINT_FLAG,
      :STD_COST_DETAIL_ID, :COMMENT
  do
  begin
    JOB_ROUTE_ID = gen_id(mf_job_route_id_gen, 1);
    /* copy route */
    insert into MF_JOB_ROUTE (ID, JOB_ID, OPER_NUM, DESCRIPTION, EFF_ON_DATE,
      EFF_OFF_DATE, WC_ID, COMPLETE_FLAG, EFFICIENCY_FACTOR, START_DATE, END_DATE,
      START_TICK, END_TICK, MOVE_TICKS, MOVE_TIME_UM, SETUP_TICKS, SETUP_TIME_UM,
      RUN_TICKS, RUN_TIME_UM, SCHED_TICKS, SCHED_TIME_UM, SCHED_OFFSET_TICKS,
      SCHED_OFFSET_TIME_UM, QUEUE_TICKS, QUEUE_TIME_UM, FREEZE_SCHEDULE_FLAG,
      QTY_RECEIVED, QTY_COMPLETE, QTY_SCRAPPED, QTY_MOVED, CONTROL_POINT_FLAG,
      STD_COST_DETAIL_ID, ACT_COST_DETAIL_ID, COMMENT)
    values (:JOB_ROUTE_ID, :JOB_ID, :OPER_NUM, :DESCRIPTION, :EFF_ON_DATE, :EFF_OFF_DATE,
      :WC_ID, 0, :EFFICIENCY, '30.12.1899', '30.12.1899', 0, 0, :MOVE_TICKS, :MOVE_TIME_UM, :SETUP_TICKS, :SETUP_TIME_UM, :RUN_TICKS,
      :RUN_TIME_UM, :SCHED_TICKS, :SCHED_TIME_UM, :SCHED_OFFSET_TICKS, :SCHED_OFFSET_TIME_UM,
      :QUEUE_TICKS, :QUEUE_TIME_UM, 0, 0, 0, 0, 0, :CONTROL_POINT_FLAG, :STD_COST_DETAIL_ID, null, :COMMENT);
    /* copy route resources */
    for
      select ID, RESOURCE_GROUP_ID, RESOURCE_TYPE, TIME_SEQUENCE, EFF_ON_DATE,
        EFF_OFF_DATE, STD_COST_DETAIL_ID, COMMENT
      from MF_BOM_ROUTE_RESOURCE
      where BOM_OPER_ID = :BOM_ROUTE_ID
      INTO :BOM_RESOURCE_ID, :RESOURCE_GROUP_ID, :RESOURCE_TYPE, :TIME_SEQUENCE,
        :EFF_ON_DATE, :EFF_OFF_DATE, :STD_COST_DETAIL_ID, :COMMENT
    do begin
      JOB_RESOURCE_ID = gen_id(mf_job_route_resource_id_gen, 1);
      insert into MF_JOB_ROUTE_RESOURCE (ID, RESOURCE_GROUP_ID, OPER_ID, RESOURCE_TYPE,
        TIME_SEQUENCE, EFF_ON_DATE, EFF_OFF_DATE, STD_COST_DETAIL_ID, ACT_COST_DETAIL_ID,
        COMMENT)
      values (:JOB_RESOURCE_ID, :RESOURCE_GROUP_ID, :JOB_ROUTE_ID, :RESOURCE_TYPE,
        :TIME_SEQUENCE, :EFF_ON_DATE, :EFF_OFF_DATE, :STD_COST_DETAIL_ID, null, :COMMENT);
      /* copy route material */
      insert into MF_JOB_MATERIAL (ID, JOB_ROUTE_RESOURCE_ID, CATALOG_ID, REVISION,
        VIEW_SEQUENCE, REPORT_SEQUENCE, QUANTITY_RATE_FLAG,
        MTL_QTY, SCRAP_FACTOR, MEASURE_ID, MTL_COST_CATEGORY_ID, MTL_BACKFLUSH_FLAG,
        BACKFLUSH_ZONE_ID, MTL_OH_ALLOCATION_FLAG, MTL_OH_RATE, CURRENCY_CODE,
        MTL_OH_RATIO, MTL_OH_COST_CATEGORY_ID, MTL_OH_BACKFLUSH_FLAG)
      select gen_id(mf_job_material_id_gen, 1), :JOB_RESOURCE_ID, CATALOG_ID,
        REVISION, VIEW_SEQUENCE, REPORT_SEQUENCE, QUANTITY_RATE_FLAG,
        MTL_QTY, SCRAP_FACTOR, MEASURE_ID, MTL_COST_CATEGORY_ID, MTL_BACKFLUSH_FLAG,
        BACKFLUSH_ZONE_ID, MTL_OH_ALLOCATION_FLAG, MTL_OH_RATE, CURRENCY_CODE,
        MTL_OH_RATIO, MTL_OH_COST_CATEGORY_ID, MTL_OH_BACKFLUSH_FLAG
      from MF_BOM_MATERIAL where BOM_ROUTE_RESOURCE_ID = :bom_resource_id;
      /* copy route machine */
      insert into MF_JOB_MACHINE (ID, JOB_ROUTE_RESOURCE_ID, TIME_RATE_FLAG,
        RUN_TICKS_MCH, RUN_TIME_MCH_UM, MCH_NUMBER, MCH_RECOVERY_FLAG, MCH_RATE,
        MCH_RATE_CUR_CODE, MCH_COST_CATEGORY_ID, MCH_BACKFLUSH_FLAG, MCH_OH_ALLOCATION_FLAG,
        MCH_OH_RATE, MCH_OH_RATE_CUR_CODE, MCH_OH_RATIO, MCH_OH_COST_CATEGORY_ID,
        MCH_OH_BACKFLUSH_FLAG)
      select gen_id(mf_job_machine_id_gen, 1), :JOB_RESOURCE_ID, TIME_RATE_FLAG,
        RUN_TICKS_MCH, RUN_TIME_MCH_UM, MCH_NUMBER, MCH_RECOVERY_FLAG, MCH_RATE,
        MCH_RATE_CUR_CODE, MCH_COST_CATEGORY_ID, MCH_BACKFLUSH_FLAG, MCH_OH_ALLOCATION_FLAG,
        MCH_OH_RATE, MCH_OH_RATE_CUR_CODE, MCH_OH_RATIO, MCH_OH_COST_CATEGORY_ID,
        MCH_OH_BACKFLUSH_FLAG
      from MF_BOM_MACHINE where BOM_ROUTE_RESOURCE_ID = :bom_resource_id;
      /* copy route labor */
      insert into MF_JOB_LABOR (ID, JOB_ROUTE_RESOURCE_ID, TIME_RATE_FLAG,
        RUN_TICKS_LBR, RUN_TIME_LBR_UM, LBR_NUMBER, LBR_RATE, LBR_COST_CATEGORY_ID,
        LBR_RATE_CUR_CODE, LBR_BACKFLUSH_FLAG, LBR_OH_ALLOCATION_FLAG, LBR_OH_RATE,
        LBR_OH_RATE_CUR_CODE, LBR_OH_RATIO, LBR_OH_COST_CATEGORY_ID, LBR_OH_BACKFLUSH_FLAG)
      select gen_id(mf_job_labor_id_gen, 1), :JOB_RESOURCE_ID, lc.time_rate_flag, bl.RUN_TICKS_LBR,
        bl.RUN_TIME_LBR_UM, bl.LBR_NUMBER, lc.lbr_rate, lc.lbr_cost_category_id,
        lc.lbr_rate_cur_code, bl.LBR_BACKFLUSH_FLAG, lc.lbr_oh_allocation_flag, lc.lbr_oh_rate,
        lc.lbr_oh_rate_cur_code, lc.lbr_oh_ratio, lc.lbr_oh_cost_category_id, bl.LBR_OH_BACKFLUSH_FLAG
      from MF_BOM_LABOR bl, MF_LABOR_CLASS lc where (bl.BOM_ROUTE_RESOURCE_ID = :bom_resource_id) and (bl.labor_class_id = lc.id);
    end
  end
end
^

ALTER PROCEDURE MF_P_COPYDETAIL_BOM (
    SRCBOM_ID INTEGER,
    DSTBOM_ID INTEGER)
AS
DECLARE VARIABLE SRCBOM_ROUTE_ID INTEGER;
DECLARE VARIABLE DSTBOM_ROUTE_ID INTEGER;
DECLARE VARIABLE ROUTE_STD_COST_DETAIL_ID INTEGER;
DECLARE VARIABLE SRCBOM_RESOURCE_ID INTEGER;
DECLARE VARIABLE DSTBOM_RESOURCE_ID INTEGER;
DECLARE VARIABLE ROUTE_RESOURCE_STD_COST_DETAIL_ID INTEGER;
DECLARE VARIABLE OPER_NUM INTEGER;
DECLARE VARIABLE DESCRIPTION VARCHAR(256);
DECLARE VARIABLE EFF_ON_DATE TIMESTAMP;
DECLARE VARIABLE EFF_OFF_DATE TIMESTAMP;
DECLARE VARIABLE WC_ID INTEGER;
DECLARE VARIABLE EFFICIENCY NUMERIC(15,5);
DECLARE VARIABLE MOVE_TICKS NUMERIC(18,0);
DECLARE VARIABLE MOVE_TIME_UM INTEGER;
DECLARE VARIABLE SETUP_TICKS NUMERIC(18,0);
DECLARE VARIABLE SETUP_TIME_UM INTEGER;
DECLARE VARIABLE RUN_TICKS NUMERIC(18,0);
DECLARE VARIABLE RUN_TIME_UM INTEGER;
DECLARE VARIABLE SCHED_TICKS NUMERIC(18,0);
DECLARE VARIABLE SCHED_TIME_UM INTEGER;
DECLARE VARIABLE SCHED_OFFSET_TICKS NUMERIC(18,0);
DECLARE VARIABLE SCHED_OFFSET_TIME_UM INTEGER;
DECLARE VARIABLE QUEUE_TICKS NUMERIC(18,0);
DECLARE VARIABLE QUEUE_TIME_UM INTEGER;
DECLARE VARIABLE CONTROL_POINT_FLAG SMALLINT;
DECLARE VARIABLE STD_COST_DETAIL_ID INTEGER;
DECLARE VARIABLE COMMENT VARCHAR(256);
DECLARE VARIABLE RESOURCE_GROUP_ID INTEGER;
DECLARE VARIABLE RESOURCE_TYPE SMALLINT;
DECLARE VARIABLE TIME_SEQUENCE INTEGER;
begin
  for
    select ID, OPER_NUM, DESCRIPTION, EFF_ON_DATE, EFF_OFF_DATE,  WC_ID,
      EFFICIENCY, MOVE_TICKS, MOVE_TIME_UM, SETUP_TICKS, SETUP_TIME_UM, RUN_TICKS,
      RUN_TIME_UM, SCHED_TICKS, SCHED_TIME_UM, SCHED_OFFSET_TICKS, SCHED_OFFSET_TIME_UM,
      QUEUE_TICKS, QUEUE_TIME_UM, CONTROL_POINT_FLAG, STD_COST_DETAIL_ID, COMMENT
    from MF_BOM_ROUTE
    where BOM_ID = :SRCBOM_ID
    into :SRCBOM_ROUTE_ID, :OPER_NUM, :DESCRIPTION, :EFF_ON_DATE, :EFF_OFF_DATE, :WC_ID,
      :EFFICIENCY, :MOVE_TICKS, :MOVE_TIME_UM, :SETUP_TICKS, :SETUP_TIME_UM, :RUN_TICKS,
      :RUN_TIME_UM, :SCHED_TICKS, :SCHED_TIME_UM, :SCHED_OFFSET_TICKS, :SCHED_OFFSET_TIME_UM,
      :QUEUE_TICKS, :QUEUE_TIME_UM, :CONTROL_POINT_FLAG, :STD_COST_DETAIL_ID, :COMMENT
  do
  begin
    DSTBOM_ROUTE_ID = gen_id(mf_bom_route_id_gen, 1);
    ROUTE_STD_COST_DETAIL_ID = gen_id(mf_cost_detail_id_gen, 1);
    insert into mf_cost_detail (ID)
    values (:ROUTE_STD_COST_DETAIL_ID);
    /* copy route */
    insert into MF_BOM_ROUTE (ID, BOM_ID, OPER_NUM, DESCRIPTION, EFF_ON_DATE, EFF_OFF_DATE, WC_ID,
      EFFICIENCY, MOVE_TICKS, MOVE_TIME_UM, SETUP_TICKS, SETUP_TIME_UM, RUN_TICKS,
      RUN_TIME_UM, SCHED_TICKS, SCHED_TIME_UM, SCHED_OFFSET_TICKS, SCHED_OFFSET_TIME_UM,
      QUEUE_TICKS, QUEUE_TIME_UM, CONTROL_POINT_FLAG, STD_COST_DETAIL_ID, COMMENT)
    values (:DSTBOM_ROUTE_ID, :DSTBOM_ID, :OPER_NUM, :DESCRIPTION, :EFF_ON_DATE, :EFF_OFF_DATE, :WC_ID,
      :EFFICIENCY, :MOVE_TICKS, :MOVE_TIME_UM, :SETUP_TICKS, :SETUP_TIME_UM, :RUN_TICKS,
      :RUN_TIME_UM, :SCHED_TICKS, :SCHED_TIME_UM, :SCHED_OFFSET_TICKS, :SCHED_OFFSET_TIME_UM,
      :QUEUE_TICKS, :QUEUE_TIME_UM, :CONTROL_POINT_FLAG, :ROUTE_STD_COST_DETAIL_ID, :COMMENT);

    /* copy route resources */
    for
      select ID, RESOURCE_GROUP_ID, RESOURCE_TYPE, TIME_SEQUENCE, EFF_ON_DATE,
        EFF_OFF_DATE, STD_COST_DETAIL_ID, COMMENT
      from MF_BOM_ROUTE_RESOURCE
      where BOM_OPER_ID = :SRCBOM_ROUTE_ID
      INTO :SRCBOM_RESOURCE_ID, :RESOURCE_GROUP_ID, :RESOURCE_TYPE, :TIME_SEQUENCE,
        :EFF_ON_DATE, :EFF_OFF_DATE, :STD_COST_DETAIL_ID, :COMMENT
    do begin
      DSTBOM_RESOURCE_ID = gen_id(mf_bom_route_resource_id_gen, 1);
      ROUTE_RESOURCE_STD_COST_DETAIL_ID = gen_id(mf_cost_detail_id_gen, 1);
      insert into mf_cost_detail (ID)
      values (:ROUTE_RESOURCE_STD_COST_DETAIL_ID);
      insert into MF_BOM_ROUTE_RESOURCE (ID, RESOURCE_GROUP_ID, BOM_OPER_ID,
        RESOURCE_TYPE, TIME_SEQUENCE, EFF_ON_DATE, EFF_OFF_DATE,
        STD_COST_DETAIL_ID, COMMENT)
      values (:DSTBOM_RESOURCE_ID, :RESOURCE_GROUP_ID, :DSTBOM_ROUTE_ID, :RESOURCE_TYPE,
        :TIME_SEQUENCE, :EFF_ON_DATE, :EFF_OFF_DATE, :ROUTE_RESOURCE_STD_COST_DETAIL_ID, :COMMENT);

      /* copy route material */
      insert into MF_BOM_MATERIAL (
        ID, BOM_ROUTE_RESOURCE_ID, CATALOG_ID, REVISION,
        VIEW_SEQUENCE, REPORT_SEQUENCE, PROBABLE, QUANTITY_RATE_FLAG,
        MTL_QTY, SCRAP_FACTOR, MEASURE_ID, MTL_COST_CATEGORY_ID,
        MTL_BACKFLUSH_FLAG, BACKFLUSH_ZONE_ID, MTL_OH_ALLOCATION_FLAG,
        MTL_OH_RATE, CURRENCY_CODE, MTL_OH_RATIO, MTL_OH_COST_CATEGORY_ID,
        MTL_OH_BACKFLUSH_FLAG)
      select gen_id(mf_bom_material_id_gen, 1), :DSTBOM_RESOURCE_ID, CATALOG_ID,
        REVISION, VIEW_SEQUENCE, REPORT_SEQUENCE, PROBABLE, QUANTITY_RATE_FLAG,
        MTL_QTY, SCRAP_FACTOR, MEASURE_ID, MTL_COST_CATEGORY_ID, MTL_BACKFLUSH_FLAG,
        BACKFLUSH_ZONE_ID, MTL_OH_ALLOCATION_FLAG, MTL_OH_RATE, CURRENCY_CODE,
        MTL_OH_RATIO, MTL_OH_COST_CATEGORY_ID, MTL_OH_BACKFLUSH_FLAG
      from MF_BOM_MATERIAL where BOM_ROUTE_RESOURCE_ID = :SRCBOM_RESOURCE_ID;

      /* copy route machine */
      insert into MF_BOM_MACHINE (ID, BOM_ROUTE_RESOURCE_ID, TIME_RATE_FLAG,
        RUN_TICKS_MCH, RUN_TIME_MCH_UM, MCH_NUMBER, MCH_RECOVERY_FLAG, MCH_RATE,
        MCH_RATE_CUR_CODE, MCH_COST_CATEGORY_ID, MCH_BACKFLUSH_FLAG, MCH_OH_ALLOCATION_FLAG,
        MCH_OH_RATE, MCH_OH_RATE_CUR_CODE, MCH_OH_RATIO, MCH_OH_COST_CATEGORY_ID,
        MCH_OH_BACKFLUSH_FLAG)
      select gen_id(mf_bom_machine_id_gen, 1), :DSTBOM_RESOURCE_ID, TIME_RATE_FLAG,
        RUN_TICKS_MCH, RUN_TIME_MCH_UM, MCH_NUMBER, MCH_RECOVERY_FLAG, MCH_RATE,
        MCH_RATE_CUR_CODE, MCH_COST_CATEGORY_ID, MCH_BACKFLUSH_FLAG, MCH_OH_ALLOCATION_FLAG,
        MCH_OH_RATE, MCH_OH_RATE_CUR_CODE, MCH_OH_RATIO, MCH_OH_COST_CATEGORY_ID,
        MCH_OH_BACKFLUSH_FLAG
      from MF_BOM_MACHINE where BOM_ROUTE_RESOURCE_ID = :SRCBOM_RESOURCE_ID;

      /* copy route labor */
      insert into MF_BOM_LABOR (ID, BOM_ROUTE_RESOURCE_ID, LABOR_CLASS_ID,
        RUN_TICKS_LBR, RUN_TIME_LBR_UM, LBR_NUMBER, LBR_BACKFLUSH_FLAG,
        LBR_OH_BACKFLUSH_FLAG)
      select gen_id(mf_bom_labor_id_gen, 1), :DSTBOM_RESOURCE_ID, LABOR_CLASS_ID,
        RUN_TICKS_LBR, RUN_TIME_LBR_UM, LBR_NUMBER, LBR_BACKFLUSH_FLAG,
        LBR_OH_BACKFLUSH_FLAG
      from MF_BOM_LABOR where BOM_ROUTE_RESOURCE_ID = :SRCBOM_RESOURCE_ID;
    end
  end
end
^

ALTER PROCEDURE OBDB (
    ACCDB VARCHAR(255),
    ACCKT CHAR(20),
    DATE1 TIMESTAMP,
    DATE2 TIMESTAMP,
    SPECMARK CHAR(20))
RETURNS (
    TURN NUMERIC(15,4))
AS
declare variable TurnNat numeric(15,4);
declare variable N integer; 
declare variable Counter integer;
declare variable A char(20); 
declare variable a_id integer;
declare variable acckt_id integer;
begin
  Turn = 0;
  N = 1;
  Counter = WordCount(AccDb);

  select a.id from accplan a where a.upacc = :acckt into :acckt_id;

  while (N <= Counter) do begin
    A = ExtractWord(N, AccDb);
    select a.id from accplan a where a.upacc = :a into :a_id;

    if ((AccKt is null) and (Specmark is null)) then
      select sum(s.summanat)
        from (economicspec s left join economicoper h on h.id=s.economicoper_id)
        where (h.keepdate between :Date1 and :Date2) and
              (s.accdb_id=:A_id)
        into :TurnNat;
    else if ((AccKt is not null) and (Specmark is null)) then  
      select sum(s.summanat)
        from (economicspec s left join economicoper h on h.id=s.economicoper_id)
        where (h.keepdate between :Date1 and :Date2) and
              (s.accdb_id=:A_id) and
              (s.acckt_id=:AccKt_id)
        into :TurnNat;
    else if ((AccKt is null) and (Specmark is not null)) then    
      select sum(s.summanat)
        from (economicspec s left join economicoper h on h.id=s.economicoper_id)
        where (h.keepdate between :Date1 and :Date2) and
              (s.accdb_id=:A_id) and
              (h.specmark=:Specmark)
        into :TurnNat;
    else if ((AccKt is not null) and (Specmark is not null)) then     
      select sum(s.summanat)
        from (economicspec s left join economicoper h on h.id=s.economicoper_id)
        where (h.keepdate between :Date1 and :Date2) and
              (s.accdb_id=:A_id) and
              (s.acckt_id=:AccKt_id) and
              (h.specmark=:Specmark)
        into :TurnNat;
    if (TurnNat is null) then TurnNat = 0;    
    Turn = Turn + TurnNat; 
    N = N + 1;
  end
  suspend;
end
^

ALTER PROCEDURE OBDB_DETAIL (
    ACC_DB INTEGER,
    ANL_DB1 INTEGER,
    ANL_DB2 INTEGER,
    ANL_DB3 INTEGER,
    ANL_DB4 INTEGER,
    ANL_DB5 INTEGER,
    ACC_KT INTEGER,
    ANL_KT1 INTEGER,
    ANL_KT2 INTEGER,
    ANL_KT3 INTEGER,
    ANL_KT4 INTEGER,
    ANL_KT5 INTEGER,
    CONTR_ID INTEGER,
    CATALOG_ID INTEGER,
    DATE1 TIMESTAMP,
    DATE2 TIMESTAMP)
RETURNS (
    TURNNATDB NUMERIC(15,4),
    TURNCURDB NUMERIC(15,4))
AS
begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 22.12.00
 Назначение: Расчет оборота по дебету счета в корреспонденции
 с другим счетом за произвольный период
 со следующими дополнительными параметрами:
 - аналитика счета Дт
 - аналитика счета Кт
 - сотрудник, партнер, подразделение
 - ТМЦ
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 02.08.2001 Кривопустов Переход на вер.2
 06.09.2002 Кривопустов Из селектов убрано сравнение с 0
**********************************************************/
  if (acc_db = 0) then acc_db = null;
  if (anl_db1 = 0) then anl_db1 = null;
  if (anl_db2 = 0) then anl_db2 = null;
  if (anl_db3 = 0) then anl_db3 = null;
  if (anl_db4 = 0) then anl_db4 = null;
  if (anl_db5 = 0) then anl_db5 = null;
  if (acc_kt = 0) then acc_kt = null;
  if (anl_kt1 = 0) then anl_kt1 = null;
  if (anl_kt2 = 0) then anl_kt2 = null;
  if (anl_kt3 = 0) then anl_kt3 = null;
  if (anl_kt4 = 0) then anl_kt4 = null;
  if (anl_kt5 = 0) then anl_kt5 = null;
  if (contr_id = 0) then contr_id = null;
  if (catalog_id = 0) then catalog_id = null;
  /**/
  select sum(es.summanat), sum(es.summacur)
  from economicspec es
    join economicoper eo on eo.id=es.economicoper_id
  where (es.accdb_id =:acc_db) and
      ((es.anldb1_id = :Anl_Db1)or((es.anldb1_id is null)and(:Anl_Db1 is null))) and
      ((es.anldb2_id = :Anl_Db2)or((es.anldb2_id is null)and(:Anl_Db2 is null))) and
      ((es.anldb3_id = :Anl_Db3)or((es.anldb3_id is null)and(:Anl_Db3 is null))) and
      ((es.anldb4_id = :Anl_Db4)or((es.anldb4_id is null)and(:Anl_Db4 is null))) and
      ((es.anldb5_id = :Anl_Db5)or((es.anldb5_id is null)and(:Anl_Db5 is null))) and
      ((es.acckt_id =:acc_kt)or(:acc_kt is null)) and
      ((es.anlkt1_id = :Anl_kt1)or((es.anlkt1_id is null)and(:Anl_kt1 is null))) and
      ((es.anlkt2_id = :Anl_kt2)or((es.anlkt2_id is null)and(:Anl_kt2 is null))) and
      ((es.anlkt3_id = :Anl_kt3)or((es.anlkt3_id is null)and(:Anl_kt3 is null))) and
      ((es.anlkt4_id = :Anl_kt4)or((es.anlkt4_id is null)and(:Anl_kt4 is null))) and
      ((es.anlkt5_id = :Anl_kt5)or((es.anlkt5_id is null)and(:Anl_kt5 is null))) and
      ((eo.to_id = :contr_id)or(:contr_id is null)) and
      ((es.catalog_id = :catalog_id)or(:catalog_id is null)) and
      (eo.keepdate between :Date1 and :Date2)
  into :TurnNatDb, :TurnCurDb;

  if (TurnNatDb is null) then TurnNatDb = 0;
  if (TurnCurDb is null) then TurnCurDb = 0;

  suspend;
end
^

ALTER PROCEDURE OBKT (
    ACCKT VARCHAR(255),
    ACCDB CHAR(20),
    DATE1 TIMESTAMP,
    DATE2 TIMESTAMP,
    SPECMARK CHAR(20))
RETURNS (
    TURN NUMERIC(15,4))
AS
declare variable TurnNat numeric(15,4);
declare variable N integer; 
declare variable Counter integer;
declare variable A char(20); 
declare variable a_id integer;
declare variable accdb_id integer;
begin
  Turn = 0;
  N = 1;
  Counter = WordCount(AccKt);

  select a.id from accplan a where a.upacc = :accdb into :accdb_id;

  while (N <= Counter) do begin
    A = ExtractWord(N, AccKt);
    select a.id from accplan a where a.upacc = :a into :a_id;

    if ((AccDb is null) and (Specmark is null)) then
      select sum(s.summanat)
        from (economicspec s left join economicoper h on h.id=s.economicoper_id)
        where (h.keepdate between :Date1 and :Date2) and
              (s.acckt_id=:A_id)
        into :TurnNat;
    else if ((AccDb is not null) and (Specmark is null)) then  
      select sum(s.summanat)
        from (economicspec s left join economicoper h on h.id=s.economicoper_id)
        where (h.keepdate between :Date1 and :Date2) and
              (s.acckt_id=:A_id) and
              (s.accdb_id=:AccDb_id)
        into :TurnNat;
    else if ((AccDb is null) and (Specmark is not null)) then    
      select sum(s.summanat)
        from (economicspec s left join economicoper h on h.id=s.economicoper_id)
        where (h.keepdate between :Date1 and :Date2) and
              (s.acckt_id=:A_id) and
              (h.specmark=:Specmark)
        into :TurnNat;
    else if ((AccDb is not null) and (Specmark is not null)) then     
      select sum(s.summanat)
        from (economicspec s left join economicoper h on h.id=s.economicoper_id)
        where (h.keepdate between :Date1 and :Date2) and
              (s.acckt_id=:A_id) and
              (s.accdb_id=:AccDb_id) and
              (h.specmark=:Specmark)
        into :TurnNat;
    if (TurnNat is null) then TurnNat = 0;    
    Turn = Turn + TurnNat; 
    N = N + 1;
  end
  suspend;
end
^

ALTER PROCEDURE OBKT_DETAIL (
    ACC_KT INTEGER,
    ANL_KT1 INTEGER,
    ANL_KT2 INTEGER,
    ANL_KT3 INTEGER,
    ANL_KT4 INTEGER,
    ANL_KT5 INTEGER,
    ACC_DB INTEGER,
    ANL_DB1 INTEGER,
    ANL_DB2 INTEGER,
    ANL_DB3 INTEGER,
    ANL_DB4 INTEGER,
    ANL_DB5 INTEGER,
    CONTR_ID INTEGER,
    CATALOG_ID INTEGER,
    DATE1 TIMESTAMP,
    DATE2 TIMESTAMP)
RETURNS (
    TURNNATKT NUMERIC(15,4),
    TURNCURKT NUMERIC(15,4))
AS
begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 22.12.00
 Назначение: Расчет оборота по кредиту счета в корреспонденции
 с другим счетом за произвольный период
 со следующими дополнительными параметрами:
 - аналитика счета Дт
 - аналитика счета Кт
 - сотрудник, партнер, подразделение
 - ТМЦ
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 02.08.2001 Кривопустов Переход на вер.2
 06.09.2002 Кривопустов Из селектов убрано сравнение с 0
**********************************************************/
  if (acc_db = 0) then acc_db = null;
  if (anl_db1 = 0) then anl_db1 = null;
  if (anl_db2 = 0) then anl_db2 = null;
  if (anl_db3 = 0) then anl_db3 = null;
  if (anl_db4 = 0) then anl_db4 = null;
  if (anl_db5 = 0) then anl_db5 = null;
  if (acc_kt = 0) then acc_kt = null;
  if (anl_kt1 = 0) then anl_kt1 = null;
  if (anl_kt2 = 0) then anl_kt2 = null;
  if (anl_kt3 = 0) then anl_kt3 = null;
  if (anl_kt4 = 0) then anl_kt4 = null;
  if (anl_kt5 = 0) then anl_kt5 = null;
  if (contr_id = 0) then contr_id = null;
  if (catalog_id = 0) then catalog_id = null;
  /**/
  select sum(es.summanat), sum(es.summacur)
  from economicspec es
    join economicoper eo on eo.id=es.economicoper_id
  where (es.acckt_id =:acc_kt) and
      ((es.anlkt1_id = :Anl_kt1)or((es.anlkt1_id is null)and(:Anl_kt1 is null))) and
      ((es.anlkt2_id = :Anl_kt2)or((es.anlkt2_id is null)and(:Anl_kt2 is null))) and
      ((es.anlkt3_id = :Anl_kt3)or((es.anlkt3_id is null)and(:Anl_kt3 is null))) and
      ((es.anlkt4_id = :Anl_kt4)or((es.anlkt4_id is null)and(:Anl_kt4 is null))) and
      ((es.anlkt5_id = :Anl_kt5)or((es.anlkt5_id is null)and(:Anl_kt5 is null))) and
      ((es.accdb_id =:acc_db)or(:acc_db is null)) and
      ((es.anldb1_id = :Anl_Db1)or((es.anldb1_id is null)and(:Anl_Db1 is null))) and
      ((es.anldb2_id = :Anl_Db2)or((es.anldb2_id is null)and(:Anl_Db2 is null))) and
      ((es.anldb3_id = :Anl_Db3)or((es.anldb3_id is null)and(:Anl_Db3 is null))) and
      ((es.anldb4_id = :Anl_Db4)or((es.anldb4_id is null)and(:Anl_Db4 is null))) and
      ((es.anldb5_id = :Anl_Db5)or((es.anldb5_id is null)and(:Anl_Db5 is null))) and
      ((eo.from_id = :contr_id)or(:contr_id is null)) and
      ((es.catalog_id = :catalog_id)or(:catalog_id is null)) and
      (eo.keepdate between :Date1 and :Date2)
  into :TurnNatKt, :TurnCurKt;

  if (TurnNatKt is null) then TurnNatKt = 0;
  if (TurnCurKt is null) then TurnCurKt = 0;

  suspend;
end
^

ALTER PROCEDURE OSDB (
    ACC VARCHAR(255),
    ADATE TIMESTAMP,
    ONBEGINDAY SMALLINT)
RETURNS (
    REMN NUMERIC(15,4))
AS
DECLARE VARIABLE REMNNATDB NUMERIC(15,4);
DECLARE VARIABLE REMNNATKT NUMERIC(15,4);
DECLARE VARIABLE REMNCURDB NUMERIC(15,4);
DECLARE VARIABLE REMNCURKT NUMERIC(15,4);
DECLARE VARIABLE N INTEGER;
DECLARE VARIABLE COUNTER INTEGER;
DECLARE VARIABLE A CHAR(20);
DECLARE VARIABLE A_ID INTEGER;
begin
  Remn = 0;
  N = 1;
  Counter = WordCount(Acc);
  while (N <= Counter) do begin
    A = ExtractWord(N, Acc);
    select a.id from accplan a where (a.upacc = :a) into :a_id;
    execute procedure F_CALC_REMANTACC_ONDATE(:A_id, :ADATE, :ONBEGINDAY)
      returning_values :RemnNatDb, :RemnCurDb, :RemnNatKt, :RemnCurKt;
    Remn = Remn + RemnNatDb; 
    N = N + 1;
  end
  suspend;
end
^

ALTER PROCEDURE OSDB_DETAIL (
    ACC INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    CONTR_ID INTEGER,
    CATALOG_ID INTEGER,
    ADATE TIMESTAMP,
    ONBEGINDAY SMALLINT)
RETURNS (
    REMNNATDB NUMERIC(15,4),
    REMNCURDB NUMERIC(15,4))
AS
DECLARE VARIABLE ACCTYPE INTEGER;
DECLARE VARIABLE ANLFORM INTEGER;
DECLARE VARIABLE PERIOD_ID INTEGER;
DECLARE VARIABLE DATEFROM TIMESTAMP;
DECLARE VARIABLE DATETO TIMESTAMP;
DECLARE VARIABLE TEMPDATE TIMESTAMP;
DECLARE VARIABLE REMNBEGINNATDB NUMERIC(15,4);
DECLARE VARIABLE REMNBEGINCURDB NUMERIC(15,4);
DECLARE VARIABLE REMNBEGINNATKT NUMERIC(15,4);
DECLARE VARIABLE REMNBEGINCURKT NUMERIC(15,4);
DECLARE VARIABLE TURNNATDB NUMERIC(15,4);
DECLARE VARIABLE TURNCURDB NUMERIC(15,4);
DECLARE VARIABLE TURNNATKT NUMERIC(15,4);
DECLARE VARIABLE TURNCURKT NUMERIC(15,4);
begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 22.12.00
 Назначение: Расчет дебетового остатка по счету
 со следующими дополнительными параметрами:
 - аналитика счета
 - сотрудник, партнер, подразделение
 - ТМЦ
 - на начало (1) и на конец дня (0)
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 02.08.2001 Кривопустов Переход на вер.2
 06.09.2002 Кривопустов Из селектов убрано сравнение с 0
**********************************************************/
  if (acc = 0) then acc = null;
  if (anl1 = 0) then anl1 = null;
  if (anl2 = 0) then anl2 = null;
  if (anl3 = 0) then anl3 = null;
  if (anl4 = 0) then anl4 = null;
  if (anl5 = 0) then anl5 = null;
  if (:contr_id = 0) then contr_id = null;
  if (:catalog_id = 0) then catalog_id = null;
  /**/
  select a.acctype, a.anlform
    from accplan a
    where (a.id=:acc)
  into :acctype, :anlform;
  if (AccType is null) then exception e_invalid_account;
  /**/
  /*  if (onbeginday = 1) then ADate = ADate - 1;*/
  /*Определение периода*/
  select p.id, p.DateFrom, p.Dateto
    from period p
    where (:Adate between p.datefrom and p.dateto)
  into :period_id, :DateFrom, :DateTo;

  /*Определение остатка на начало периода*/
  /*Нет, Торговая наценка, Реализация, Затраты, Расходы будущих периодов, Денежные средства*/
  if (anlform in (0, 2, 9, 10, 16, 17, 18)) then begin
     if ((anl1 is null)and(anl2 is null)and(anl3 is null)and(anl4 is null)and(anl5 is null)) then begin

         select r.remnbeginnatdb, r.remnbeginnatkt,
                r.remnbegincurdb, r.remnbegincurkt
           from remnacc r
           where (r.acc_id = :Acc) and (r.period_id=:period_id)
         into :remnbeginnatdb, :remnbeginnatkt,
              :remnbegincurdb, :remnbegincurkt;
     end
     else begin
         select sum(r.remnbeginnatdb), sum(r.remnbeginnatkt),
                sum(r.remnbegincurdb), sum(r.remnbegincurkt)
           from remnanl r
           where (r.acc_id = :Acc) and (r.period_id=:period_id) and
                 ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and
                 ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and
                 ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and
                 ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and
                 ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null)))
         into :remnbeginnatdb, :remnbeginnatkt,
              :remnbegincurdb, :remnbegincurkt;
     end
  end /*конец 0,9,10,16,17,18*/

  /*Дебиторы кредиторы по основанию, по договору, по договору и основанию, по партнеру, подотчетные лица*/
  if (anlform in (11, 12, 13, 14, 15)) then begin
     if (((anl1 is null)and(anl2 is null)and(anl3 is null)and(anl4 is null)and(anl5 is null)) and
         (contr_id is null)) then begin

         select r.remnbeginnatdb, r.remnbeginnatkt,
                r.remnbegincurdb, r.remnbegincurkt
           from remnacc r
           where (r.acc_id = :Acc) and (r.period_id=:period_id)
         into :remnbeginnatdb, :remnbeginnatkt,
              :remnbegincurdb, :remnbegincurkt;
     end
     else begin
         select sum(r.remnbeginnatdb), sum(r.remnbeginnatkt),
                sum(r.remnbegincurdb), sum(r.remnbegincurkt)
            from remndbkt r
            where (r.acc_id=:acc) and (r.period_id=:period_id) and
                 ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and
                 ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and
                 ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and
                 ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and
                 ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null))) and
                 ((r.contractor_id = :contr_id)or(:contr_id is null))
         into :remnbeginnatdb, :remnbeginnatkt,
              :remnbegincurdb, :remnbegincurkt;
     end
  end /*конец 11,12,13,14,15*/

  /*Материалы, товары, МБП*/
  if (anlform in (1, 3, 4, 5, 6, 7, 8)) then begin
     if (((anl1 is null)and(anl2 is null)and(anl3 is null)and(anl4 is null)and(anl5 is null)) and
         (catalog_id is null)) then begin

         select r.remnbeginnatdb, r.remnbeginnatkt,
                r.remnbegincurdb, r.remnbegincurkt
           from remnacc r
           where (r.acc_id = :Acc) and (r.period_id=:period_id)
         into :remnbeginnatdb, :remnbeginnatkt,
              :remnbegincurdb, :remnbegincurkt;
     end
     else begin
         select sum(r.remnbeginnat),
                sum(r.remnbegincur)
            from remnval r
            where (r.acc_id=:acc) and (r.period_id=:period_id) and
                 ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and
                 ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and
                 ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and
                 ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and
                 ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null))) and
                 ((r.contractor_id = :contr_id)or(:contr_id is null)) and
                 ((r.catalog_id = :catalog_id)or(:catalog_id is null))
         into :remnbeginnatdb,
              :remnbegincurdb;
     end
  end /*конец 1,3,4,5,6,7,8*/


  /*Обороты за период*/
  /*Начало периода и остаток на начало дня*/
  if ((DateFrom = Adate) and (ONBEGINDAY = 1)) then begin
      REMNNATDB = remnbeginnatdb - remnbeginnatkt;
      REMNCURDB = remnbegincurdb - remnbegincurkt;
      if (REMNNATDB < 0) then REMNNATDB = 0;
      if (REMNCURDB < 0) then REMNCURDB = 0;
  end
  /*Середина периода и остаток на начало дня*/
  if ((DateFrom <> Adate) and (ONBEGINDAY = 1)) then Tempdate = :Adate - 1;
  /*Середина периода и остаток на конец дня*/
  if ((DateFrom <> Adate) and (ONBEGINDAY = 0)) then Tempdate = :Adate;


  if ((DateFrom = Adate) and (ONBEGINDAY = 1)) then begin
        suspend;
  end
  else begin
        select turnnatdb, turncurdb
          from obdb_detail(:acc, :anl1, :anl2, :anl3, :anl4, :anl5,
                           null, null, null, null, null, null, /*Исправлено Денисом*/
                           :contr_id, :catalog_id, :DateFrom, :Tempdate)
          into :turnnatdb, :turncurdb;
    
        select turnnatkt, turncurkt
          from obkt_detail(:acc, :anl1, :anl2, :anl3, :anl4, :anl5,
                           null, null, null, null, null, null, /*Исправлено Денисом*/
                           :contr_id, :catalog_id, :DateFrom, :Tempdate)
          into :turnnatkt, :turncurkt;
    
          if (TurnNatDb is null) then TurnNatDb = 0;
          if (TurnCurDb is null) then TurnCurDb = 0;
          if (TurnNatKt is null) then TurnNatKt = 0;
          if (TurnCurKt is null) then TurnCurKt = 0;
    
          REMNNATDB = remnbeginnatdb - remnbeginnatkt + turnnatdb - turnnatkt;
          REMNCURDB = remnbegincurdb - remnbegincurkt + turncurdb - turncurkt;
    
          if (REMNNATDB < 0) then REMNNATDB = 0;
          if (REMNCURDB < 0) then REMNCURDB = 0;

          suspend;
  end
end
^

ALTER PROCEDURE OSKT (
    ACC VARCHAR(255),
    ADATE TIMESTAMP,
    ONBEGINDAY SMALLINT)
RETURNS (
    REMN NUMERIC(15,4))
AS
declare variable RemnNatDb numeric(15,4);
declare variable RemnNatKt numeric(15,4);
declare variable RemnCurDb numeric(15,4);
declare variable RemnCurKt numeric(15,4);
declare variable N integer; 
declare variable Counter integer;
declare variable A char(20); 
declare variable a_id integer;
begin
  Remn = 0;
  N = 1;
  Counter = WordCount(Acc);
  while (N <= Counter) do begin
    A = ExtractWord(N, Acc);
    select a.id from accplan a where (a.upacc = :a) into :a_id;
    execute procedure F_CALC_REMANTACC_ONDATE(:A_id, :ADATE, :ONBEGINDAY)
      returning_values :RemnNatDb, :RemnCurDb, :RemnNatKt, :RemnCurKt;
    Remn = Remn + RemnNatKt; 
    N = N + 1;
  end
  suspend;
end
^

ALTER PROCEDURE OSKT_DETAIL (
    ACC INTEGER,
    ANL1 INTEGER,
    ANL2 INTEGER,
    ANL3 INTEGER,
    ANL4 INTEGER,
    ANL5 INTEGER,
    CONTR_ID INTEGER,
    CATALOG_ID INTEGER,
    ADATE TIMESTAMP,
    ONBEGINDAY SMALLINT)
RETURNS (
    REMNNATKT NUMERIC(15,4),
    REMNCURKT NUMERIC(15,4))
AS
DECLARE VARIABLE ACCTYPE INTEGER;
DECLARE VARIABLE ANLFORM INTEGER;
DECLARE VARIABLE PERIOD_ID INTEGER;
DECLARE VARIABLE DATEFROM TIMESTAMP;
DECLARE VARIABLE DATETO TIMESTAMP;
DECLARE VARIABLE TEMPDATE TIMESTAMP;
DECLARE VARIABLE REMNBEGINNATDB NUMERIC(15,4);
DECLARE VARIABLE REMNBEGINCURDB NUMERIC(15,4);
DECLARE VARIABLE REMNBEGINNATKT NUMERIC(15,4);
DECLARE VARIABLE REMNBEGINCURKT NUMERIC(15,4);
DECLARE VARIABLE TURNNATDB NUMERIC(15,4);
DECLARE VARIABLE TURNCURDB NUMERIC(15,4);
DECLARE VARIABLE TURNNATKT NUMERIC(15,4);
DECLARE VARIABLE TURNCURKT NUMERIC(15,4);
begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 22.12.00
 Назначение: Расчет кредитового остатка по счету
 со следующими дополнительными параметрами:
 - аналитика счета
 - сотрудник, партнер, подразделение
 - ТМЦ
 - на начало (1) и на конец дня (0)
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 02.08.2001 Кривопустов Переход на вер.2
 06.09.2002 Кривопустов Из селектов убрано сравнение с 0
**********************************************************/
  if (acc = 0) then acc = null;
  if (anl1 = 0) then anl1 = null;
  if (anl2 = 0) then anl2 = null;
  if (anl3 = 0) then anl3 = null;
  if (anl4 = 0) then anl4 = null;
  if (anl5 = 0) then anl5 = null;
  if (:contr_id = 0) then contr_id = null;
  if (:catalog_id = 0) then catalog_id = null;
  /**/
  select a.acctype, a.anlform
    from accplan a
    where (a.id=:acc)
  into :acctype, :anlform;
  if (AccType is null) then exception e_invalid_account;
  /**/
  /*Определение периода*/
  select p.id, p.DateFrom, p.Dateto
    from period p
    where (:Adate between p.datefrom and p.dateto)
  into :period_id, :DateFrom, :DateTo;

  /*Определение остатка на начало периода*/
  /*Нет, Торговая наценка, Реализация, Затраты, Расходы будущих периодов, Денежные средства*/
  if (anlform in (0, 2, 9, 10, 16, 17, 18)) then begin
     if ((anl1 is null)and(anl2 is null)and(anl3 is null)and(anl4 is null)and(anl5 is null)) then begin

         select r.remnbeginnatdb, r.remnbeginnatkt,
                r.remnbegincurdb, r.remnbegincurkt
           from remnacc r
           where (r.acc_id = :Acc) and (r.period_id=:period_id)
         into :remnbeginnatdb, :remnbeginnatkt,
              :remnbegincurdb, :remnbegincurkt;
     end
     else begin
         select sum(r.remnbeginnatdb), sum(r.remnbeginnatkt),
                sum(r.remnbegincurdb), sum(r.remnbegincurkt)
           from remnanl r
           where (r.acc_id = :Acc) and (r.period_id=:period_id) and
                 ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and
                 ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and
                 ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and
                 ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and
                 ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null)))
         into :remnbeginnatdb, :remnbeginnatkt,
              :remnbegincurdb, :remnbegincurkt;
     end
  end /*конец 0,9,10,16,17,18*/

  /*Дебиторы кредиторы по основанию, по договору, по договору и основанию, по партнеру, подотчетные лица*/
  if (anlform in (11, 12, 13, 14, 15)) then begin
     if ((anl1 is null)and(anl2 is null)and(anl3 is null)and(anl4 is null)and(anl5 is null) and
         (contr_id = 0)) then begin

         select r.remnbeginnatdb, r.remnbeginnatkt,
                r.remnbegincurdb, r.remnbegincurkt
           from remnacc r
           where (r.acc_id = :Acc) and (r.period_id=:period_id)
         into :remnbeginnatdb, :remnbeginnatkt,
              :remnbegincurdb, :remnbegincurkt;
     end
     else begin
         select sum(r.remnbeginnatdb), sum(r.remnbeginnatkt),
                sum(r.remnbegincurdb), sum(r.remnbegincurkt)
            from remndbkt r
            where (r.acc_id=:acc) and (r.period_id=:period_id) and
                 ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))) and
                 ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))) and
                 ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))) and
                 ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))) and
                 ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null))) and
                 ((r.contractor_id = :contr_id)or(:contr_id is null))
         into :remnbeginnatdb, :remnbeginnatkt,
              :remnbegincurdb, :remnbegincurkt;
     end
  end /*конец 11,12,13,14,15*/

  /*Материалы, товары, МБП*/
  if (anlform in (1, 3, 4, 5, 6, 7, 8)) then begin
     if ((anl1 is null)and(anl2 is null)and(anl3 is null)and(anl4 is null)and(anl5 is null) and
         (catalog_id is null)) then begin

         select r.remnbeginnatdb, r.remnbeginnatkt,
                r.remnbegincurdb, r.remnbegincurkt
           from remnacc r
           where (r.acc_id = :Acc) and (r.period_id=:period_id)
         into :remnbeginnatdb, :remnbeginnatkt,
              :remnbegincurdb, :remnbegincurkt;
     end
     else begin
         select sum(r.remnbeginnat),
                sum(r.remnbegincur)
            from remnval r
            where (r.acc_id=:acc) and (r.period_id=:period_id) and
                 ((r.anlplan1_id = :Anl1)or((r.anlplan1_id is null)and(:Anl1 is null))or(:Anl1 = 0)) and
                 ((r.anlplan2_id = :Anl2)or((r.anlplan2_id is null)and(:Anl2 is null))or(:Anl1 = 0)) and
                 ((r.anlplan3_id = :Anl3)or((r.anlplan3_id is null)and(:Anl3 is null))or(:Anl1 = 0)) and
                 ((r.anlplan4_id = :Anl4)or((r.anlplan4_id is null)and(:Anl4 is null))or(:Anl1 = 0)) and
                 ((r.anlplan5_id = :Anl5)or((r.anlplan5_id is null)and(:Anl5 is null))or(:Anl1 = 0)) and
                 ((r.contractor_id = :contr_id)or(:contr_id is null)) and
                 ((r.catalog_id = :catalog_id)or(:catalog_id is null))
         into :remnbeginnatdb,
              :remnbegincurdb;
     end
  end /*конец 1,3,4,5,6,7,8*/


  /*Обороты за период*/
  /*Начало периода и остаток на начало дня*/
  if ((DateFrom = Adate) and (ONBEGINDAY = 1)) then begin
      REMNNATKT = remnbeginnatkt - remnbeginnatdb;
      REMNCURKT = remnbegincurkt - remnbegincurdb;
      if (REMNNATKT < 0) then REMNNATKT = 0;
      if (REMNCURKT < 0) then REMNCURKT = 0;
  end
  /*Середина периода и остаток на начало дня*/
  if ((DateFrom <> Adate) and (ONBEGINDAY = 1)) then Tempdate = :Adate - 1;
  /*Середина периода и остаток на конец дня*/
  if ((DateFrom <> Adate) and (ONBEGINDAY = 0)) then Tempdate = :Adate;


  if ((DateFrom = Adate) and (ONBEGINDAY = 1)) then begin
        suspend;
  end
  else begin
        select turnnatdb, turncurdb
          from obdb_detail(:acc, :anl1, :anl2, :anl3, :anl4, :anl5,
                           null, null, null, null, null, null,
                           :contr_id, :catalog_id, :DateFrom, :Tempdate)
          into :turnnatdb, :turncurdb;
    
        select turnnatkt, turncurkt
          from obkt_detail(:acc, :anl1, :anl2, :anl3, :anl4, :anl5,
                           null, null, null, null, null, null,
                           :contr_id, :catalog_id, :DateFrom, :Tempdate)
          into :turnnatkt, :turncurkt;
    
          if (TurnNatDb is null) then TurnNatDb = 0;
          if (TurnCurDb is null) then TurnCurDb = 0;
          if (TurnNatKt is null) then TurnNatKt = 0;
          if (TurnCurKt is null) then TurnCurKt = 0;
    
          REMNNATKT = remnbeginnatkt - remnbeginnatdb - turnnatdb + turnnatkt;
          REMNCURKT = remnbegincurkt - remnbegincurdb - turncurdb + turncurkt;
    
          if (REMNNATKT < 0) then REMNNATKT = 0;
          if (REMNCURKT < 0) then REMNCURKT = 0;

          suspend;
  end
end
^

ALTER PROCEDURE OVR_CALC_GIVEN_OUT (
    OVR_CARD_ID INTEGER,
    OVR_NORM_SPEC_ID INTEGER)
RETURNS (
    RESULT NUMERIC(15,3))
AS
DECLARE VARIABLE CARD_HIST_ID INTEGER;
DECLARE VARIABLE ISPERIODIC INTEGER;
DECLARE VARIABLE QUANTITY NUMERIC(15,3);
DECLARE VARIABLE LAST_HIST_STATUS INTEGER;
DECLARE VARIABLE READOUT_DATE TIMESTAMP;
DECLARE VARIABLE SHELFLIFE NUMERIC(15,3);
DECLARE VARIABLE SHELFLIFE_MEAS SMALLINT;
DECLARE VARIABLE DEAD_DATE TIMESTAMP;
begin
/**********************************************************
 Автор: Алексей Прокудин
 Создано: 10.04.2002
 Измененено:
 Назначение: вычисление количества спецодежды, выданной
   сотруднику по заданной позиции спецификации нормы выдачи
 Используется: сервером приложения
**********************************************************/
  result = 0;
  for
    select
      och.id, och.quantity, och.isperiodic, och.readout_date, och.shelflife, och.shelflife_meas
    from
      ovr_card_hist och
    where
      (och.ovr_card_id = :ovr_card_id)
      and (och.ovr_norm_spec_id= :ovr_norm_spec_id)
    into
      :card_hist_id, :quantity, :isperiodic, :readout_date, :shelflife, :shelflife_meas
  do begin
    execute procedure ovr_get_last_hist_status(card_hist_id)
      returning_values last_hist_status;
    if (isperiodic=0) then begin /* Непериодическая */
      if (last_hist_status <> 3) then /*не "возвращено"*/
        result = result + quantity;
    end
    else /* Периодическая */
    if ((last_hist_status = 1) or (last_hist_status = 2)) then begin /* "выдано" или "в эксплуатации" */
      if (readout_date = '30.12.1899') then
        result = result + quantity;
      else begin
        execute procedure f_calc_dead_date(readout_date, shelflife, shelflife_meas)
        returning_values dead_date;
        if (dead_date > 'TODAY') then
          result = result + quantity;
      end
    end
  end
  /* Теперь прибавим те позиции КТУ, которые были выданы не по нормам */
  for
    select
      och.id, och.quantity, och.isperiodic, och.readout_date, och.shelflife, och.shelflife_meas
    from
      ovr_norm_spec ons
      join ovr_norm_spec_link onsl on onsl.ovr_norm_spec_id = ons.id
      join ovr_card_hist och on och.catalog_id = onsl.catalog_id
    where
      (ons.id = :ovr_norm_spec_id)
      and (och.ovr_card_id = :ovr_card_id)
      and (och.ovr_norm_spec_id = 0)
    into
      :card_hist_id, :quantity, :isperiodic, :readout_date, :shelflife, :shelflife_meas
  do begin
    execute procedure ovr_get_last_hist_status(card_hist_id)
      returning_values last_hist_status;
    if (isperiodic=0) then begin /* Непериодическая */
      if (last_hist_status <> 3) then /*не "возвращено"*/
        result = result + quantity;
    end
    else /* Периодическая */
    if ((last_hist_status = 1) or (last_hist_status = 2)) then begin /* "выдано" или "в эксплуатации" */
      if (readout_date = '30.12.1899') then
        result = result + quantity;
      else begin
        execute procedure f_calc_dead_date(readout_date, shelflife, shelflife_meas)
        returning_values dead_date;
        if (dead_date > 'TODAY') then
          result = result + quantity;
      end
    end
  end
  suspend;
end
^

ALTER PROCEDURE OVR_CALC_PERCENT (
    ADATE TIMESTAMP,
    ASHELFLIFE INTEGER,
    ASHELFLIFE_MEAS INTEGER)
RETURNS (
    "PERCENT" SMALLINT)
AS
DECLARE VARIABLE XDATE TIMESTAMP;
DECLARE VARIABLE YDATE TIMESTAMP;
DECLARE VARIABLE X FLOAT;
DECLARE VARIABLE Y FLOAT;
begin
  /* Procedure Text */
  execute procedure f_calc_dead_date(adate,ashelflife,ashelflife_meas)
  returning_values :xdate;
  if (xdate <= current_date) then "PERCENT" = 100;
  else
    begin
      ydate = current_date;
      x = xdate - adate;
      y = ydate - adate;
      "PERCENT" = y / x * 100;
    end
  suspend;
end
^

ALTER PROCEDURE OVR_GET_LAST_HIST_STATUS (
    CARD_HIST_ID INTEGER)
RETURNS (
    RESULT INTEGER)
AS
DECLARE VARIABLE REMOVE_DATE TIMESTAMP;
DECLARE VARIABLE READOUT_DATE TIMESTAMP;
DECLARE VARIABLE GIVE_DATE TIMESTAMP;
DECLARE VARIABLE REMOVE_TYPE_ID INTEGER;
begin
/**********************************************************
 Автор: Алексей Прокудин
 Создано: 22.04.2002
 Измененено:
 Назначение: определение текущего состояния истории выдачи
   спецодежды
 Используется: сервером приложения
**********************************************************/
  /*
  0 - нет состояния
  1 - Выдано
  2 - В эксплуатации
  3 - Возвращено
  4 - Утеряно
  5 - Списано
  */
  result = 0;
  select och.give_date, och.readout_date, och.remove_date, och.remove_type_id
    from ovr_card_hist och
    where och.id = :card_hist_id
    into :give_date, :readout_date, :remove_date, :remove_type_id;
  if ((give_date is not null) and (give_date <> '30.12.1899')) then
    result = 1;
  if ((readout_date is not null) and (readout_date <> '30.12.1899')) then
    result = 2;
  if ((remove_date is not null) and (remove_date <> '30.12.1899') and (remove_type_id <> 0)) then
    result = remove_type_id;
  suspend;
end
^

ALTER PROCEDURE OVR_P_COPYDETAIL_NORM_HEAD (
    SRCHEAD_ID INTEGER,
    DSTHEAD_ID INTEGER)
AS
begin
  INSERT INTO OVR_NORM_SPEC (ID,OVR_NORM_HEAD_ID,OVR_NORM_SPEC_NAME,MEASURE_UPCODE,CATALOG_GROUPS_TYPE_ID,SHELFLIFE,SHELFLIFE_MEAS,ISBASIC,ISPERIODIC,ISDINCH,DINCH_FORMULA,DOC_NAME,QUANTITY)
  SELECT GEN_ID(OVR_NORM_SPEC_ID_GEN,1),:DSTHEAD_ID,OVR_NORM_SPEC_NAME,MEASURE_UPCODE,CATALOG_GROUPS_TYPE_ID,SHELFLIFE,SHELFLIFE_MEAS,ISBASIC,ISPERIODIC,ISDINCH,DINCH_FORMULA,DOC_NAME,QUANTITY
      FROM OVR_NORM_SPEC
  where OVR_NORM_HEAD_ID=:SRCHEAD_ID;
end
^

ALTER PROCEDURE OVR_WRITEOFF_DETERIORATED (
    WRITEOFF_DATE TIMESTAMP)
AS
DECLARE VARIABLE CARD_HIST_ID INTEGER;
DECLARE VARIABLE LAST_HIST_STATUS INTEGER;
DECLARE VARIABLE READOUT_DATE TIMESTAMP;
DECLARE VARIABLE SHELFLIFE NUMERIC(15,3);
DECLARE VARIABLE SHELFLIFE_MEAS SMALLINT;
DECLARE VARIABLE DEAD_DATE TIMESTAMP;
begin
/**********************************************************
 Автор: Алексей Прокудин
 Создано: 24.05.2002
 Измененено:
 Назначение: списание спецодежды, износ которой достиг 100%
  на дату WRITEOFF_DATE
 Используется:
**********************************************************/
  for
    select
      och.id, och.readout_date, och.shelflife, och.shelflife_meas
    from
      ovr_card_hist och
    into
      :card_hist_id, :readout_date, :shelflife, :shelflife_meas
  do begin
    execute procedure ovr_get_last_hist_status(card_hist_id)
      returning_values last_hist_status;
    if ((last_hist_status = 2) and (shelflife <> 0) and (shelflife_meas <> 0)) then begin
      execute procedure f_calc_dead_date(readout_date, shelflife, shelflife_meas)
        returning_values dead_date;
      if (dead_date <= writeoff_date) then
        update ovr_card_hist och
        set och.remove_date = :writeoff_date,
            och.remove_type_id = 5, /* "списано" */
            och.deterioration = 100
        where (och.id = :card_hist_id);
    end
  end
  suspend;
end
^

ALTER PROCEDURE P_ADD_PRICELISTSPEC_FROM_CAT (
    PRICELISTFOLDER_ID INTEGER,
    CATALOGFOLDER_ID INTEGER,
    ISPRICELISTSPEC SMALLINT)
AS
declare variable PRICELISTHEAD_ID integer;
declare variable CatFolder_Id integer;
declare variable CatFolderName varchar(80);
declare variable CatFolderParent_Id integer;
declare variable CatFolderParentName varchar(80);
declare variable PriceFolderParent_Id integer;
declare variable PriceFolder_Id integer;
declare variable Catalog_Id integer;
declare variable Catalog_Name varchar(80);
declare variable PriceSpec_Id integer;
declare variable Price numeric(15,4);
declare variable LastCost numeric(15,4);
declare variable Canceled smallint;
declare variable ActDate timestamp;
begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 23.12.2000
 Назначение: Создание папок и позиций прайс-листа на основании
 папки КТУ (учитывая и вложенные папки)
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 31.10.2001 Кривопустов Права на создаваемые папки прайса
 06.12.2002 Кривопустов Новая структура позиции - дата и пр.
**********************************************************/
  /**/
  /*Поиск прайс-листа*/
  select prf.pricelisthead_id, plh.createdate
    from pricelistfolder prf, pricelisthead plh
    where (prf.id =:PRICELISTFOLDER_ID) and (plh.id = prf.pricelisthead_id)
  into :PRICELISTHEAD_ID, :actdate;

  /*Цикл по папкам КТУ*/
  for
  select f.folder_id, catf.parent_id, catf.fname
    from F_NESTED_CATFOLDERS_ALL_RECURSE(:CATALOGFOLDER_ID) f
         left join catalogfolder catf on catf.id=f.folder_id
    order by f.folder_id
    into :CatFolder_Id, :CatFolderParent_Id, :CatFolderName
  do begin
    if ((CatFolderParent_Id is null) or (CATALOGFOLDER_ID = :CatFolder_Id)) then begin
        PriceFolderParent_Id = PRICELISTFOLDER_ID;
    end
    else begin
        /*Поиск имени папки-родителя в КТУ*/
        select catf.fname
          from catalogfolder catf
          where (catf.id=:CatFolderParent_Id)
        into :CatFolderParentName;
    
        /*Поиск папки-родителя по имени в прайсе*/
        for
            select prf.id
            from pricelistfolder prf
            where (prf.pricelisthead_id =:PRICELISTHEAD_ID) and
                  (prf.fname =:CatFolderParentName)
            order by prf.id
            into :PriceFolderParent_Id
        do begin
           PriceFolderParent_Id =:PriceFolderParent_Id;
        end
    end

    /*Добавление папки*/
    PriceFolder_Id = gen_id(PRICELISTFOLDER_ID_GEN,1);
    insert into pricelistfolder(ID, PRICELISTHEAD_ID, PARENT_ID, FNAME)
    values
    (:PriceFolder_Id, :PRICELISTHEAD_ID, :PriceFolderParent_Id, :CatFolderName);

    insert into folder_rights(id, group_id, folderpart, folder_id, rights)
    select gen_id(folder_rights_id_gen, 1), f.group_id, 2, :PriceFolder_Id, f.rights
    from folder_rights f
    where (f.folder_id = :CatalogFolder_ID) and (f.folderpart = 1);

    /*Добавление позиций в прайс-лист из КТУ*/
    if (ispricelistspec = 1) then begin
        /*Поиск позиций в КТУ, относящихся к данной папке*/
        for
        select cat.id, cat.cname
          from catalog cat
          where (cat.folder_id=:CatFolder_Id)
        into :Catalog_Id, :Catalog_Name
        do begin
            if (Catalog_Id is not null) then begin
                /*Добавление позиции спецификации прайса*/
                PriceSpec_Id = gen_id(PRICELISTSPEC_ID_GEN,1);
                Price = 0;
                LastCost = 0;
                Canceled = 0;
                insert into pricelistspec(ID, FOLDER_ID, CATALOG_ID, PRICE, LASTCOST, SNAME, CANCELED, PRICELISTHEAD_ID, ACTDATE, ACTDATETILL)
                values (:PriceSpec_Id, :PriceFolder_Id, :Catalog_Id, :Price, :LastCost, :Catalog_Name, :Canceled, :pricelisthead_id, :actdate, '01.01.2100');
            end
        end
    end
  end
/*  suspend;*/
end
^

ALTER PROCEDURE P_ADD_TAXES (
    SECTIONDOC SMALLINT,
    SPEC_ID INTEGER,
    TAXGROUP_ID INTEGER,
    CALCTAXESKIND_ID INTEGER)
AS
DECLARE VARIABLE ID INTEGER;
DECLARE VARIABLE SUMM NUMERIC(15,4);
DECLARE VARIABLE DOCDATE DATE;
begin
  select dh.docdate from dochead dh, docspec ds
  where (dh.id = ds.dochead_id) and (ds.id = :spec_id)
  into :docdate;
  /* delete old taxs */
  delete from taxsumm t where (t.specification_id = :spec_id);
  for
    select t.id, t.summ from tax t, taxlink tl, calctaxeslink ctl
    where ((:docdate between t.activedate and t.deactivatedate)and(tl.taxgroup_id = :taxgroup_id)and(t.id = tl.tax_id)and(ctl.tax_id = t.id)and(ctl.kind_id = :calctaxeskind_id))
    into :id, :summ
  do begin
    insert into taxsumm (specification_id, tax_id, summ) values
    (:spec_id, :id, :summ);
  end
end
^

ALTER PROCEDURE P_ADDWITHMODEL_BANKDOCUMENT (
    AMODEL_ID INTEGER,
    ADOCUMENTHEAD_ID INTEGER,
    MODEL_CLASS_ID INTEGER,
    DOC_CLASS_ID INTEGER)
AS
begin
  execute procedure ref_copy_featurelink(:model_class_id, :doc_class_id, :amodel_id, :adocumenthead_id);
end
^

ALTER PROCEDURE P_ADDWITHMODEL_BILL (
    AMODEL_ID INTEGER,
    ADOCUMENTHEAD_ID INTEGER,
    MODEL_CLASS_ID INTEGER,
    DOC_CLASS_ID INTEGER)
AS
DECLARE VARIABLE ID INTEGER;
DECLARE VARIABLE NEWID INTEGER;
DECLARE VARIABLE CATALOG_ID INTEGER;
DECLARE VARIABLE BESTBEFORE TIMESTAMP;
DECLARE VARIABLE WEIGHT NUMERIC(15,3);
DECLARE VARIABLE VOLUME NUMERIC(15,3);
DECLARE VARIABLE QUANTITY NUMERIC(15,3);
DECLARE VARIABLE QUANTITY2 NUMERIC(15,3);
DECLARE VARIABLE PRICE NUMERIC(15,4);
DECLARE VARIABLE PRICE1 NUMERIC(15,4);
DECLARE VARIABLE SUMMA NUMERIC(15,4);
DECLARE VARIABLE SUMMA1 NUMERIC(15,4);
DECLARE VARIABLE TAXGROUP_ID INTEGER;
DECLARE VARIABLE PRICELISTSPEC_ID INTEGER;
begin
  for select s.id, s.catalog_id, s.bestbefore, s.weight, s.volume,
      s.quantity, s.quantity2, s.price, s.price1,
      s.summa, s.summa1, s.taxgroup_id, s.pricelistspec_id
    from docspecmodel s where (s.dochead_id = :AMODEL_ID)
    into :id, :catalog_id, :bestbefore, :weight, :volume,
      :quantity, :quantity2, :price, :price1,
      :summa, :summa1, :taxgroup_id, :pricelistspec_id
  do begin
    newid = gen_id(docspec_id_gen, 1);
    insert into docspec(ID, DOCHEAD_ID, CATALOG_ID, BESTBEFORE, WEIGHT, VOLUME,
      QUANTITY, quantity2, PRICE, PRICE1,
      SUMMA, SUMMA1, TAXGROUP_ID, PRICELISTSPEC_ID)
    values (:newid, :ADOCUMENTHEAD_ID, :catalog_id, :bestbefore, :weight, :volume,
      :quantity, :quantity2, :price, :price1,
      :summa, :summa1, :taxgroup_id, :pricelistspec_id);
    /* specifications */
    insert into billspec(ID, DOCSPEC_ID, acceptancequan, acceptancesum, acceptancepackingquan,
     price_with_discount, summa_with_discount, cost, discount)
    select gen_id(billspec_id_gen, 1), :newid , acceptancequan, acceptancesum, acceptancepackingquan,
 price_with_discount, summa_with_discount, cost, discount     
    from billspecmodel where (docspecmodel_id = :ID);
    /* package */
    /* comment by OVS, 23.01.2003 */
    /*insert into docspec_package(id, docspecmodel_id, measure_id, quantity, weight, volume)
    select gen_id(docspec_package_id_gen, 1), :newid, dsp.measure_id, dsp.quantity, dsp.weight, dsp.volume
    from docspec_package dsp where dsp.docspec_id = :ID;*/
  end
  /* Feature */
  execute procedure ref_copy_featurelink(:model_class_id, :doc_class_id, :amodel_id, :adocumenthead_id);
end
^

ALTER PROCEDURE P_ADDWITHMODEL_CASHDOCUMENT (
    AMODEL_ID INTEGER,
    ADOCUMENTHEAD_ID INTEGER,
    MODEL_CLASS_ID INTEGER,
    DOC_CLASS_ID INTEGER)
AS
begin
  execute procedure ref_copy_featurelink(:model_class_id, :doc_class_id, :amodel_id, :adocumenthead_id);
end
^

ALTER PROCEDURE P_ADDWITHMODEL_ECONOMICOPER (
    AMODEL_ID INTEGER,
    AECONOMICOPER_ID INTEGER,
    MODEL_CLASS_ID INTEGER,
    DOC_CLASS_ID INTEGER)
AS
begin
  insert into economicspec(id, economicoper_id, accdb_id, anldb1_id, anldb2_id, anldb3_id, anldb4_id, anldb5_id,
    acckt_id, anlkt1_id, anlkt2_id, anlkt3_id, anlkt4_id, anlkt5_id,
    feature1_id, feature2_id, feature3_id, feature4_id, feature5_id,
    quantity,
    summanat, summacur, curcource)
  select gen_id(economicspec_id_gen,1), :AEconomicOper_id, accdb_id, anldb1_id, anldb2_id, anldb3_id, anldb4_id, anldb5_id,
    acckt_id, anlkt1_id, anlkt2_id, anlkt3_id, anlkt4_id, anlkt5_id,
    feature1_id, feature2_id, feature3_id, feature4_id, feature5_id,
    quantity,
    summanat, summacur, curcource
  from economicspecmodel where economicoper_id = :AModel_Id;

  execute procedure ref_copy_featurelink(:model_class_id, :doc_class_id, :amodel_id, :aeconomicoper_id);
end
^

ALTER PROCEDURE P_ADDWITHMODEL_FACTURA (
    AMODEL_ID INTEGER,
    ADOCUMENTHEAD_ID INTEGER,
    MODEL_CLASS_ID INTEGER,
    DOC_CLASS_ID INTEGER)
AS
begin
  insert into docspec(id, dochead_id, catalog_id, bestbefore, weight, volume,
  quantity, quantity2, price, summa, price1, summa1)
  select gen_id(docspec_id_gen, 1), :adocumenthead_id, catalog_id, bestbefore, weight, volume,
  quantity, quantity2, price, summa, price1, summa1
  from docspecmodel where (dochead_id = :amodel_id);
  
  execute procedure ref_copy_featurelink(:model_class_id, :doc_class_id, :amodel_id, :adocumenthead_id);
end
^

ALTER PROCEDURE P_ADDWITHMODEL_FINOPER (
    AMODEL_ID INTEGER,
    AFINOPER_ID INTEGER,
    MODEL_CLASS_ID INTEGER,
    DOC_CLASS_ID INTEGER)
AS
declare variable id integer;
declare variable srcacc_id integer;
declare variable dstacc_id integer;
declare variable srcanl1_id integer;
declare variable srcanl2_id integer;
declare variable srcanl3_id integer;
declare variable srcanl4_id integer;
declare variable srcanl5_id integer;
declare variable dstanl1_id integer;
declare variable dstanl2_id integer;
declare variable dstanl3_id integer;
declare variable dstanl4_id integer;
declare variable dstanl5_id integer;
declare variable sumnat numeric(15,4);                                        
declare variable sumcur numeric(15,4);
declare variable new_id integer;
declare variable planned integer;
begin
  for
    select s.id,s.srcacc_id,s.dstacc_id,s.srcanl1_id,
      s.srcanl2_id,s.srcanl3_id,s.srcanl4_id,s.srcanl5_id,s.dstanl1_id,
      s.dstanl2_id,s.dstanl3_id,s.dstanl4_id,s.dstanl5_id,s.sumnat,s.sumcur,
      s.planned
    from finspecmodel s
    where (s.finoper_id = :amodel_id) and (s.parent_id is null)
    into :id,:srcacc_id,:dstacc_id,:srcanl1_id,
      :srcanl2_id,:srcanl3_id,:srcanl4_id,:srcanl5_id,:dstanl1_id,
      :dstanl2_id,:dstanl3_id,:dstanl4_id,:dstanl5_id,:sumnat,:sumcur,:planned
  do begin

    new_id = gen_id(finspec_id_gen,1);

    insert into finspec ( ID, FINOPER_ID, PARENT_ID, SRCACC_ID, DSTACC_ID, SRCANL1_ID,
      SRCANL2_ID, SRCANL3_ID, SRCANL4_ID, SRCANL5_ID, DSTANL1_ID, DSTANL2_ID,
      DSTANL3_ID, DSTANL4_ID, DSTANL5_ID, SUMNAT, SUMCUR, PLANNED )
    values ( :new_id, :AFINOPER_ID, null, :SRCACC_ID, :DSTACC_ID, :SRCANL1_ID,
      :SRCANL2_ID, :SRCANL3_ID, :SRCANL4_ID, :SRCANL5_ID, :DSTANL1_ID, :DSTANL2_ID,
      :DSTANL3_ID, :DSTANL4_ID, :DSTANL5_ID, :SUMNAT, :SUMCUR, :PLANNED );

    insert into finspec ( ID, FINOPER_ID, PARENT_ID, SRCACC_ID, DSTACC_ID, SRCANL1_ID,
      SRCANL2_ID, SRCANL3_ID, SRCANL4_ID, SRCANL5_ID, DSTANL1_ID, DSTANL2_ID,
      DSTANL3_ID, DSTANL4_ID, DSTANL5_ID, SUMNAT, SUMCUR, PLANNED )
    select gen_id(finspec_id_gen,1), :AFINOPER_ID, :new_id, s.SRCACC_ID, s.DSTACC_ID, s.SRCANL1_ID,
      s.SRCANL2_ID, s.SRCANL3_ID, s.SRCANL4_ID, s.SRCANL5_ID, s.DSTANL1_ID, s.DSTANL2_ID,
      s.DSTANL3_ID, s.DSTANL4_ID, s.DSTANL5_ID, s.SUMNAT, s.SUMCUR, s.PLANNED
    from finspecmodel s
    where (s.finoper_id = :amodel_id) and (s.parent_id = :id);
    
  end /* for select */

  execute procedure ref_copy_featurelink(:model_class_id, :doc_class_id, :amodel_id, :afinoper_id);
end
^

ALTER PROCEDURE P_ADDWITHMODEL_INTERNALACT (
    AMODEL_ID INTEGER,
    ADOCUMENTHEAD_ID INTEGER,
    MODEL_CLASS_ID INTEGER,
    DOC_CLASS_ID INTEGER)
AS
begin
  execute procedure ref_copy_featurelink(:model_class_id, :doc_class_id, :amodel_id, :adocumenthead_id);
end
^

ALTER PROCEDURE P_ADDWITHMODEL_INTERNALINVOICE (
    AMODEL_ID INTEGER,
    ADOCUMENTHEAD_ID INTEGER,
    MODEL_CLASS_ID INTEGER,
    DOC_CLASS_ID INTEGER)
AS
DECLARE VARIABLE ID INTEGER;
DECLARE VARIABLE NEWID INTEGER;
DECLARE VARIABLE CATALOG_ID INTEGER;
DECLARE VARIABLE BESTBEFORE TIMESTAMP;
DECLARE VARIABLE WEIGHT NUMERIC(15,3);
DECLARE VARIABLE VOLUME NUMERIC(15,3);
DECLARE VARIABLE QUANTITY NUMERIC(15,3);
DECLARE VARIABLE QUANTITY2 NUMERIC(15,3);
DECLARE VARIABLE PRICE NUMERIC(15,4);
DECLARE VARIABLE PRICE1 NUMERIC(15,4);
DECLARE VARIABLE SUMMA NUMERIC(15,4);
DECLARE VARIABLE SUMMA1 NUMERIC(15,4);
DECLARE VARIABLE TAXGROUP_ID INTEGER;
DECLARE VARIABLE PRICELISTSPEC_ID INTEGER;
DECLARE VARIABLE MEASURE1_ID INTEGER;
DECLARE VARIABLE MEASURE2_ID INTEGER;
DECLARE VARIABLE COMMENT VARCHAR(256);
DECLARE VARIABLE CONTRACTOR_ID INTEGER;
DECLARE VARIABLE SPEC_CLASS_ID INTEGER;
DECLARE VARIABLE MODEL_SPEC_CLASS_ID INTEGER;
begin
  select ds.spec_class_id
  from docsection ds
  where ds.class_id = :doc_class_id
  into :spec_class_id;

  select ds.model_spec_class_id
  from docsection ds
  where ds.class_id = :doc_class_id
  into :model_spec_class_id;

  for select s.id, s.catalog_id, s.bestbefore, s.weight, s.volume,
      s.quantity, s.quantity2, s.price, s.price1,
      s.summa, s.summa1, s.taxgroup_id, s.pricelistspec_id, s.measure1_id, s.measure2_id,
      comment, contractor_id
    from docspecmodel s where (s.dochead_id = :AMODEL_ID)
    into :id, :catalog_id, :bestbefore, :weight, :volume,
      :quantity, :quantity2, :price, :price1,
      :summa, :summa1, :taxgroup_id, :pricelistspec_id, :measure1_id, :measure2_id,
      :comment, :contractor_id
  do begin
    newid = gen_id(docspec_id_gen, 1);
    insert into docspec(ID, DOCHEAD_ID, CATALOG_ID, BESTBEFORE, WEIGHT, VOLUME,
      QUANTITY, quantity2, PRICE, PRICE1,
      SUMMA, SUMMA1, TAXGROUP_ID, PRICELISTSPEC_ID, measure1_id, measure2_id,
      comment, contractor_id)
    values (:newid, :ADOCUMENTHEAD_ID, :catalog_id, :bestbefore, :weight, :volume,
      :quantity, :quantity2, :price, :price1,
      :summa, :summa1, :taxgroup_id, :pricelistspec_id, :measure1_id, :measure2_id,
      :comment, :contractor_id);
    /* specifications */
    insert into internalinvoicespec(ID, DOCSPEC_ID, requestquan, requestsumma)
    select gen_id(internalinvoicespec_id_gen, 1), :newid, requestquan, requestsumma
    from internalinvoicespecmodel where (docspec_id = :ID);
    /* package */
    /* comment by OVS, 23.01.2003 */
    /*insert into docspec_package(id, docspecmodel_id, measure_id, quantity, weight, volume)
    select gen_id(docspec_package_id_gen, 1), :newid, dsp.measure_id, dsp.quantity, dsp.weight, dsp.volume
    from docspec_package dsp where dsp.docspec_id = :ID;*/
    /* specification features */
    execute procedure ref_copy_featurelink(:model_spec_class_id, :spec_class_id, :id, :newid);
  end
  /* Feature */
  execute procedure ref_copy_featurelink(:model_class_id, :doc_class_id, :amodel_id, :adocumenthead_id);
end
^

ALTER PROCEDURE P_ADDWITHMODEL_INVOICE (
    AMODEL_ID INTEGER,
    ADOCUMENTHEAD_ID INTEGER,
    MODEL_CLASS_ID INTEGER,
    DOC_CLASS_ID INTEGER)
AS
DECLARE VARIABLE ID INTEGER;
DECLARE VARIABLE NEWID INTEGER;
DECLARE VARIABLE CATALOG_ID INTEGER;
DECLARE VARIABLE BESTBEFORE TIMESTAMP;
DECLARE VARIABLE WEIGHT NUMERIC(15,3);
DECLARE VARIABLE VOLUME NUMERIC(15,3);
DECLARE VARIABLE QUANTITY NUMERIC(15,3);
DECLARE VARIABLE QUANTITY2 NUMERIC(15,3);
DECLARE VARIABLE PRICE NUMERIC(15,4);
DECLARE VARIABLE PRICE1 NUMERIC(15,4);
DECLARE VARIABLE PRICE_WITH_DISCOUNT NUMERIC(15,4);
DECLARE VARIABLE SUMMA NUMERIC(15,4);
DECLARE VARIABLE SUMMA1 NUMERIC(15,4);
DECLARE VARIABLE SUMMA_WITH_DISCOUNT NUMERIC(15,4);
DECLARE VARIABLE COST NUMERIC(15,4);
DECLARE VARIABLE DISCOUNT NUMERIC(15,4);
DECLARE VARIABLE TAXGROUP_ID INTEGER;
DECLARE VARIABLE PRICELISTSPEC_ID INTEGER;
begin
  for select s.id, s.catalog_id, s.bestbefore, s.weight, s.volume,
      s.quantity, s.quantity2, s.price, s.price1,
      s.summa, s.summa1, s.taxgroup_id, s.pricelistspec_id
    from docspecmodel s where (s.dochead_id = :AMODEL_ID)
    into :id, :catalog_id, :bestbefore, :weight, :volume,
      :quantity, :quantity2, :price, :price1,
      :summa, :summa1, :taxgroup_id, :pricelistspec_id
  do begin
    newid = gen_id(docspec_id_gen, 1);
    insert into docspec(ID, DOCHEAD_ID, CATALOG_ID, BESTBEFORE, WEIGHT, VOLUME,
      QUANTITY, quantity2, PRICE, PRICE1,
      SUMMA, SUMMA1, TAXGROUP_ID, PRICELISTSPEC_ID)
    values (:newid, :ADOCUMENTHEAD_ID, :catalog_id, :bestbefore, :weight, :volume,
      :quantity, :quantity2, :price, :price1,
      :summa, :summa1, :taxgroup_id, :pricelistspec_id);
    /* specifications */
    insert into invoicespec(ID, DOCSPEC_ID, PRICE_WITH_DISCOUNT, SUMMA_WITH_DISCOUNT, COST, DISCOUNT)
    select gen_id(invoicespec_id_gen, 1), :newid, PRICE_WITH_DISCOUNT, SUMMA_WITH_DISCOUNT, COST, DISCOUNT
    from invoicespecmodel where (docspecmodel_id = :ID);
    /* package */
    /* comment by OVS, 23.01.2003 */
    /*insert into docspec_package(id, docspecmodel_id, measure_id, quantity, weight, volume)
    select gen_id(docspec_package_id_gen, 1), :newid, dsp.measure_id, dsp.quantity, dsp.weight, dsp.volume
    from docspec_package dsp where dsp.docspec_id = :ID;*/
  end
  /* Feature */
  execute procedure ref_copy_featurelink(:model_class_id, :doc_class_id, :amodel_id, :adocumenthead_id);
end
^

ALTER PROCEDURE P_ADDWITHMODEL_ORDER (
    AMODEL_ID INTEGER,
    ADOCUMENTHEAD_ID INTEGER,
    MODEL_CLASS_ID INTEGER,
    DOC_CLASS_ID INTEGER)
AS
DECLARE VARIABLE ID INTEGER;
DECLARE VARIABLE NEWID INTEGER;
DECLARE VARIABLE CATALOG_ID INTEGER;
DECLARE VARIABLE BESTBEFORE TIMESTAMP;
DECLARE VARIABLE WEIGHT NUMERIC(15,3);
DECLARE VARIABLE VOLUME NUMERIC(15,3);
DECLARE VARIABLE QUANTITY NUMERIC(15,3);
DECLARE VARIABLE QUANTITY2 NUMERIC(15,3);
DECLARE VARIABLE PRICE NUMERIC(15,4);
DECLARE VARIABLE PRICE1 NUMERIC(15,4);
DECLARE VARIABLE SUMMA NUMERIC(15,4);
DECLARE VARIABLE SUMMA1 NUMERIC(15,4);
DECLARE VARIABLE TAXGROUP_ID INTEGER;
DECLARE VARIABLE PRICELISTSPEC_ID INTEGER;
DECLARE VARIABLE MEASURE1_ID INTEGER;
DECLARE VARIABLE MEASURE2_ID INTEGER;
DECLARE VARIABLE COMMENT VARCHAR(256);
DECLARE VARIABLE CONTRACTOR_ID INTEGER;
DECLARE VARIABLE SPEC_CLASS_ID INTEGER;
DECLARE VARIABLE MODEL_SPEC_CLASS_ID INTEGER;
begin
  select ds.spec_class_id
  from docsection ds
  where ds.class_id = :doc_class_id
  into :spec_class_id;

  select ds.model_spec_class_id
  from docsection ds
  where ds.class_id = :doc_class_id
  into :model_spec_class_id;

  for select s.id, s.catalog_id, s.bestbefore, s.weight, s.volume,
      s.quantity, s.quantity2, s.price, s.price1,
      s.summa, s.summa1, s.taxgroup_id, s.pricelistspec_id, s.measure1_id, s.measure2_id,
      comment, contractor_id
    from docspecmodel s where (s.dochead_id = :AMODEL_ID)
    into :id, :catalog_id, :bestbefore, :weight, :volume,
      :quantity, :quantity2, :price, :price1,
      :summa, :summa1, :taxgroup_id, :pricelistspec_id, :measure1_id, :measure2_id,
      :comment, :contractor_id
  do begin
    newid = gen_id(docspec_id_gen, 1);
    insert into docspec(ID, DOCHEAD_ID, CATALOG_ID, BESTBEFORE, WEIGHT, VOLUME,
      QUANTITY, quantity2, PRICE, PRICE1,
      SUMMA, SUMMA1, TAXGROUP_ID, PRICELISTSPEC_ID, measure1_id, measure2_id,
      comment, contractor_id)
    values (:newid, :ADOCUMENTHEAD_ID, :catalog_id, :bestbefore, :weight, :volume,
      :quantity, :quantity2, :price, :price1,
      :summa, :summa1, :taxgroup_id, :pricelistspec_id, :measure1_id, :measure2_id,
      :comment, :contractor_id);
    /* specifications */
    insert into orderspec(ID, DOCSPEC_ID, PRICE_WITH_DISCOUNT, SUMMA_WITH_DISCOUNT, COST, DISCOUNT)
    select gen_id(orderspec_id_gen, 1), :newid, PRICE_WITH_DISCOUNT, SUMMA_WITH_DISCOUNT, COST, DISCOUNT
    from orderspecmodel where (docspecmodel_id = :ID);
    /* package */
    /* comment by OVS, 23.01.2003 */
    /*insert into docspec_package(id, docspecmodel_id, measure_id, quantity, weight, volume)
    select gen_id(docspec_package_id_gen, 1), :newid, dsp.measure_id, dsp.quantity, dsp.weight, dsp.volume
    from docspec_package dsp where dsp.docspec_id = :ID;*/
    /* specification features */
    execute procedure ref_copy_featurelink(:model_spec_class_id, :spec_class_id, :id, :newid);
  end
  /* document features */
  execute procedure ref_copy_featurelink(:model_class_id, :doc_class_id, :amodel_id, :adocumenthead_id);
end
^

ALTER PROCEDURE P_ADDWITHMODEL_STOCKDOCUMENT (
    AMODEL_ID INTEGER,
    ADOCUMENTHEAD_ID INTEGER,
    MODEL_CLASS_ID INTEGER,
    DOC_CLASS_ID INTEGER)
AS
DECLARE VARIABLE ID INTEGER;
DECLARE VARIABLE NEWID INTEGER;
DECLARE VARIABLE CATALOG_ID INTEGER;
DECLARE VARIABLE BESTBEFORE TIMESTAMP;
DECLARE VARIABLE WEIGHT NUMERIC(15,3);
DECLARE VARIABLE VOLUME NUMERIC(15,3);
DECLARE VARIABLE QUANTITY NUMERIC(15,3);
DECLARE VARIABLE QUANTITY2 NUMERIC(15,3);
DECLARE VARIABLE PRICE NUMERIC(15,4);
DECLARE VARIABLE PRICE1 NUMERIC(15,4);
DECLARE VARIABLE PRICE_WITH_DISCOUNT NUMERIC(15,4);
DECLARE VARIABLE SUMMA NUMERIC(15,4);
DECLARE VARIABLE SUMMA1 NUMERIC(15,4);
DECLARE VARIABLE SUMMA_WITH_DISCOUNT NUMERIC(15,4);
DECLARE VARIABLE COST NUMERIC(15,4);
DECLARE VARIABLE DISCOUNT NUMERIC(15,4);
DECLARE VARIABLE TAXGROUP_ID INTEGER;
DECLARE VARIABLE PRICELISTSPEC_ID INTEGER;
begin
  for select s.id, s.catalog_id, s.bestbefore, s.weight, s.volume,
      s.quantity, s.quantity2, s.price, s.price1,
      s.summa, s.summa1, s.taxgroup_id, s.pricelistspec_id
    from docspecmodel s where (s.dochead_id = :AMODEL_ID)
    into :id, :catalog_id, :bestbefore, :weight, :volume,
      :quantity, :quantity2, :price, :price1,
      :summa, :summa1, :taxgroup_id, :pricelistspec_id
  do begin
    newid = gen_id(docspec_id_gen, 1);
    insert into docspec(ID, DOCHEAD_ID, CATALOG_ID, BESTBEFORE, WEIGHT, VOLUME,
      QUANTITY, QUANTITY2, PRICE, PRICE1,
      SUMMA, SUMMA1, TAXGROUP_ID, PRICELISTSPEC_ID)
    values (:newid, :ADOCUMENTHEAD_ID, :catalog_id, :bestbefore, :weight, :volume,
      :quantity, :quantity2, :price, :price1,
      :summa, :summa1, :taxgroup_id, :pricelistspec_id);
    /* specifications */
    insert into stockdocumentspec(ID, DOCSPEC_ID, PRICE_WITH_DISCOUNT, SUMMA_WITH_DISCOUNT, COST, DISCOUNT)
    select gen_id(stockdocumentspec_id_gen, 1), :newid, PRICE_WITH_DISCOUNT, SUMMA_WITH_DISCOUNT, COST, DISCOUNT
    from stockdocumentspecmodel where (docspec_id = :ID);
    /* package */
    /* comment by OVS, 23.01.2003 */
    /*insert into docspec_package(id, docspecmodel_id, measure_id, quantity, weight, volume)
    select gen_id(docspec_package_id_gen, 1), :newid, dsp.measure_id, dsp.quantity, dsp.weight, dsp.volume
    from docspec_package dsp where dsp.docspec_id = :ID;*/
  end
  /* Feature */
  execute procedure ref_copy_featurelink(:model_class_id, :doc_class_id, :amodel_id, :adocumenthead_id);
end
^

ALTER PROCEDURE P_APPLY_DISCOUNT (
    DOC_ID INTEGER,
    DISCOUNT NUMERIC(15,5))
AS
begin
  update docspec set
    price = Round4(price * (1 + :discount / 100.0)),
    summa = Round4(summa * (1 + :discount / 100.0))
  where (dochead_id = :doc_id);
end
^

ALTER PROCEDURE P_AUTODISTRIBUTION_FACTSUM (
    FACT_ID INTEGER)
AS
declare variable plan_id integer;
declare variable freesum numeric(15,4);
declare variable dsum numeric(15,4);
declare variable distsum numeric(15,4);
begin
  select factsum - distsum from phaseitemfact 
  where (factsum > distsum) and (id = :fact_id) into :distsum;
  for
    select pip.id, pip.plansum - pip.factsum from phaseitemplan pip, phaseitemfact pif, contractphase cp
    where (pip.plansum > pip.factsum) and (pip.phase_id = cp.id) and (cp.dochead_id = pif.dochead_id) and (pip.kind = pif.kind) and (pif.id = :fact_id)
    into :plan_id, :freesum
  do begin
    if (freesum > distsum) then begin
      dsum = distsum; distsum = 0;
    end
    else begin
      dsum = freesum; distsum = distsum - freesum;
    end
    insert into phaseitem_link(id, planitem_id, factitem_id, distsum)
    values (gen_id(phaseitem_link_id_gen, 1), :plan_id, :fact_id, :dsum);
    if (distsum = 0) then Exit;  
  end
end
^

ALTER PROCEDURE P_BROWSE_STOCKBATCH (
    STOCK_ID INTEGER,
    MOL_ID INTEGER,
    CATALOG_ID INTEGER,
    PROCESS_DATE TIMESTAMP)
RETURNS (
    ID INTEGER,
    CONTRACTOR CHAR(20),
    BEGINQUAN NUMERIC(15,3),
    ENDQUAN NUMERIC(15,3),
    BEGINQUAN2 NUMERIC(15,3),
    ENDQUAN2 NUMERIC(15,3),
    PRICENAT NUMERIC(15,4),
    PRICECUR NUMERIC(15,4),
    CURRENCY_CODE CHAR(5),
    DOCCAPTION VARCHAR(100),
    CREATEDATE TIMESTAMP,
    BESTBEFORE TIMESTAMP,
    MEASURE1 CHAR(5),
    MEASURE2 CHAR(5))
AS
begin
  for
      select sb.id, c.code, sb.beginquan, sb.endquan, sb.beginquan2, sb.endquan2,
        sb.pricenat, sb.pricecur, sb.currency_code, RaiseDocumentCaption(sb.doctype, sb.docnumber, sb.docdate),
        sb.createdate, sb.bestbefore, m1.code, m2.code
      from stockbatch sb join stockcard sc on sb.stockcard_id = sc.id
        left join contractor c on c.id = sb.contractor_id
        left join catalog cat on cat.id = :catalog_id
        left join measure m1 on m1.id = cat.measure1_id
        left join measure m2 on m2.id = cat.measure2_id
      where (sc.stock_id = :stock_id) and ((sc.mol_id = :mol_id) or (sc.mol_id is null) and (:mol_id is null)) and
        (sc.catalog_id = :catalog_id) and (sb.endquan > 0.0) and (sb.createdate <= :process_date)
      into :id, :contractor, :beginquan, :endquan, :beginquan2, :endquan2, :pricenat, :pricecur, :currency_code,
        :doccaption, :createdate, :bestbefore, :measure1, measure2
  do
    suspend;
end
^

ALTER PROCEDURE P_CALC_AMORT (
    INVENTORY_ID INTEGER,
    AMONTH INTEGER,
    BATCH INTEGER)
AS
DECLARE VARIABLE ALG SMALLINT;
BEGIN
  if (exists (
        select * from invhistory h
        where (h.inventory_id = :inventory_id) and (h.kind = 3/*Retire*/)
        )) then exit;

  SELECT ALGORITHM FROM INVENTORY WHERE ID = :INVENTORY_ID INTO :ALG;
  IF (ALG = 0) THEN EXECUTE PROCEDURE P_CALC_AMORT_LINEAR :INVENTORY_ID, :AMONTH, :BATCH;
  ELSE
  IF (ALG = 1) THEN EXECUTE PROCEDURE P_CALC_AMORT_DEPRVAL :INVENTORY_ID, :AMONTH, :BATCH;
  ELSE
  IF (ALG = 2) THEN EXECUTE PROCEDURE P_CALC_AMORT_PERIOD :INVENTORY_ID, :AMONTH, :BATCH;
  ELSE
  IF (ALG = 3) THEN EXECUTE PROCEDURE P_CALC_AMORT_PRODUCTION :INVENTORY_ID, :AMONTH, :BATCH;
END
^

ALTER PROCEDURE P_CALC_AMORT_DEPRVAL (
    INVENTORY_ID INTEGER,
    AMONTH INTEGER,
    BATCH INTEGER)
AS
DECLARE VARIABLE ANS INTEGER;
DECLARE VARIABLE LASTMONTH INTEGER;
DECLARE VARIABLE NUMMONTHS INTEGER;
DECLARE VARIABLE MONTHS INTEGER;
DECLARE VARIABLE SAVEMONTHS INTEGER;
DECLARE VARIABLE INDATE TIMESTAMP;
DECLARE VARIABLE OUTDATE TIMESTAMP;
DECLARE VARIABLE BEGLOSSDATE TIMESTAMP;
DECLARE VARIABLE YEARBEGIN TIMESTAMP;
DECLARE VARIABLE FYEAR INTEGER;
DECLARE VARIABLE DDEPRVAL NUMERIC(15,4);
DECLARE VARIABLE CARDAMRATE NUMERIC(15,4);
DECLARE VARIABLE AMRATE NUMERIC(15,4);
DECLARE VARIABLE AMCODE_id integer;
DECLARE VARIABLE BVALUE NUMERIC(15,4);
DECLARE VARIABLE ENDCOST NUMERIC(15,4);
DECLARE VARIABLE AMVALUE NUMERIC(15,4);
DECLARE VARIABLE FACTOR NUMERIC(15,4);
DECLARE VARIABLE SUMTOTAL NUMERIC(15,4);
declare variable prec smallint;
BEGIN
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: расчет амортизации пропорционально остаточной
             стоимости
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 17.02.2003 Кривопустов Округление до указанного в
                        конфигурации модуля
**********************************************************/
  ans = dm('*** Start P_CALC_AMORT_DEPRVAL');

  select c.currency_prec from acc_config c into :prec;

  ans = wi('inventory_id', INVENTORY_ID);
  SELECT AMCODE_ID, YEARAMORTRATE, BALANCECOST, ENDCOST, FACTOR, INOPERDATE, OUTOPERDATE, beginlossdate
    FROM INVENTORY
    WHERE ID = :INVENTORY_ID
    INTO :amcode_id, :CardAmRate, :BVALUE, :EndCost, :FACTOR, :InDate, :OutDate, :BegLossDate;
  if (EndCost = 0) then begin
    ans = dm('*** Finish P_CALC_AMORT_DEPRVAL - EndCost=0');
    Exit;  
  end
  if ((BegLossDate <> '30.12.1899') and (BegLossDate is not null)) then
    InDate = BegLossDate;
  /* find depreciable value on beginning of the year */
  /*execute procedure F_YEAR('TODAY') returning_values :FYear;*/
  FYear = extract(year from current_timestamp);
  execute procedure EncodeDate(:FYear, 1, 1) returning_values :YearBegin;
  for select h.delta_deprval from invhistory h
        where (h.inventory_id = :Inventory_id) and (h.kind = 1) and (h.act_date >= :YearBegin)
        into :DDeprVal
  do begin
    EndCost = EndCost - DDeprVal;
  end
  ans = wf('EndCost',15,4,EndCost);
  /**/
  execute procedure f_get_num_months(:INVENTORY_ID, :AMONTH, :InDate, :OutDate)
    returning_values :Months, :NumMonths;
  SaveMonths = Months;
  AmValue = 0;
  while (Months > 0) do begin
    /* find appropriate depreciation rate */
    NumMonths = NumMonths + 1;
    execute procedure f_get_amortrate(:amcode_id, :NumMonths)
      returning_values :AmRate;
    ans = wf('AmRate', 15, 4, AmRate);
    /* if rate from dictionary not found use rate from inventory card */
    if (AmRate = 0) then AmRate = CardAmRate;
    /* calc amort value for that month */ 
    AMVALUE = Round(AmValue + (EndCost * AMRATE / 1200.0), :prec);
    Months = Months - 1;
  end
  IF ((FACTOR <> 0) AND (FACTOR IS NOT NULL)) THEN 
    SUMTOTAL = Round(AMVALUE * FACTOR, :prec);
  ELSE SUMTOTAL = AMVALUE;

  if (Round(sumtotal, :prec) > Round(endcost, :prec)) then
    sumtotal = endcost;

  INSERT INTO ACC_AMORTIZATION (ID, INVENTORY_ID, IMONTH, N_MONTHS, BATCH, amcode_id, AMRATE, 
                          SUM_RATE, FACTOR, SUM_TOTAL, BALANCECOST, endcost)
    VALUES (GEN_ID(ACC_AMORTIZATION_ID_GEN,1), :INVENTORY_ID, :AMONTH, :SaveMonths, :BATCH, :amcode_id, :AMRATE, 
         :AMVALUE, :FACTOR, :SUMTOTAL, :BVALUE, :endcost);
  ans = dm('*** Finish P_CALC_AMORT_DEPRVAL');
END
^

ALTER PROCEDURE P_CALC_AMORT_LINEAR (
    INVENTORY_ID INTEGER,
    AMONTH INTEGER,
    BATCH INTEGER)
AS
DECLARE VARIABLE ANS INTEGER;
DECLARE VARIABLE LASTMONTH INTEGER;
DECLARE VARIABLE NUMMONTHS INTEGER;
DECLARE VARIABLE MONTHS INTEGER;
DECLARE VARIABLE SAVEMONTHS INTEGER;
DECLARE VARIABLE INDATE TIMESTAMP;
DECLARE VARIABLE OUTDATE TIMESTAMP;
DECLARE VARIABLE BEGLOSSDATE TIMESTAMP;
DECLARE VARIABLE CARDAMRATE NUMERIC(15,4);
DECLARE VARIABLE AMRATE NUMERIC(15,4);
DECLARE VARIABLE amcode_id integer;
DECLARE VARIABLE ENDCOST NUMERIC(15,4);
DECLARE VARIABLE BVALUE NUMERIC(15,4);
DECLARE VARIABLE AMVALUE NUMERIC(15,4);
DECLARE VARIABLE FACTOR NUMERIC(15,4);
DECLARE VARIABLE SUMTOTAL NUMERIC(15,4);
declare variable prec smallint;
BEGIN
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: расчет амортизации линейным методом
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 17.02.2003 Кривопустов Округление до указанного в
                        конфигурации модуля
**********************************************************/
  ans = dm('*** Start P_CALC_AMORT_LINEAR');

  select c.currency_prec from acc_config c into :prec;

  ans = wi('inventory_id', INVENTORY_ID);
  SELECT amcode_id, YEARAMORTRATE, BALANCECOST, FACTOR, 
         INOPERDATE, OUTOPERDATE, beginlossdate, endcost
    FROM INVENTORY
    WHERE ID = :INVENTORY_ID
    INTO :amcode_id, :CardAmRate, :BVALUE, :FACTOR, 
         :InDate, :OutDate, :BegLossDate, :EndCost;
  if (EndCost = 0) then begin
    ans = dm('*** Finish P_CALC_AMORT_LINEAR - EndCost=0');
    Exit;  
  end
  if ((BegLossDate <> '30.12.1899') and (BegLossDate is not null)) then
    InDate = BegLossDate;
  execute procedure f_get_num_months(:INVENTORY_ID, :AMONTH, :InDate, :OutDate)
    returning_values :Months, :NumMonths;
  SaveMonths = Months;
  AmValue = 0;
  while (Months > 0) do begin
    /* find appropriate depreciation rate */
    NumMonths = NumMonths + 1;
    execute procedure f_get_amortrate(:amcode_id, :NumMonths)
      returning_values :AmRate;
    ans = wf('AmRate', 15, 4, AmRate);
    /* if rate from dictionary not found use rate from inventory card */
    if (AmRate = 0) then AmRate = CardAmRate;
    /* calc amort value for that month */ 
    AMVALUE = Round(AmValue + (BVALUE * AMRATE / 1200.0), :prec);
    Months = Months - 1;
  end
  IF ((FACTOR <> 0) AND (FACTOR IS NOT NULL)) THEN 
    SUMTOTAL = Round(AMVALUE * FACTOR, :prec);
  ELSE SUMTOTAL = AMVALUE;

  if (Round(sumtotal, :prec) > Round(endcost, :prec)) then
    sumtotal = endcost;

  INSERT INTO ACC_AMORTIZATION (ID, INVENTORY_ID, IMONTH, N_MONTHS, BATCH, amcode_id, AMRATE,
                          SUM_RATE, FACTOR, SUM_TOTAL, BALANCECOST, endcost)
    VALUES (GEN_ID(ACC_AMORTIZATION_ID_GEN,1), :INVENTORY_ID, :AMONTH, :SaveMonths, :BATCH, :amcode_id, :AMRATE,
         :AMVALUE, :FACTOR, :SUMTOTAL, :BVALUE, :endcost);
  ans = dm('*** Finish P_CALC_AMORT_LINEAR');
END
^

ALTER PROCEDURE P_CALC_AMORT_PERIOD (
    INVENTORY_ID INTEGER,
    AMONTH INTEGER,
    BATCH INTEGER)
AS
DECLARE VARIABLE ANS INTEGER;
DECLARE VARIABLE PERIODY NUMERIC(15,4);
DECLARE VARIABLE PERIODM NUMERIC(15,4);
DECLARE VARIABLE CARDAMRATE NUMERIC(15,4);
DECLARE VARIABLE LASTMONTH INTEGER;
DECLARE VARIABLE NUMMONTHS INTEGER;
DECLARE VARIABLE MONTHS INTEGER;
DECLARE VARIABLE SAVEMONTHS INTEGER;
DECLARE VARIABLE INDATE TIMESTAMP;
DECLARE VARIABLE OUTDATE TIMESTAMP;
DECLARE VARIABLE BEGLOSSDATE TIMESTAMP;
DECLARE VARIABLE AMRATE NUMERIC(15,4);
DECLARE VARIABLE amcode_id integer;
DECLARE VARIABLE BVALUE NUMERIC(15,4);
DECLARE VARIABLE AMVALUE NUMERIC(15,4);
DECLARE VARIABLE FACTOR NUMERIC(15,4);
DECLARE VARIABLE SUMTOTAL NUMERIC(15,4);
DECLARE VARIABLE ENDCOST NUMERIC(15,4);
declare variable prec smallint;
BEGIN
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: расчет амортизации пропорционально периоду
             эксплуатации
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 17.02.2003 Кривопустов Округление до указанного в
                        конфигурации модуля
**********************************************************/
  ans = dm('*** Start P_CALC_AMORT_PERIOD');

  select c.currency_prec from acc_config c into :prec;

  ans = wi('inventory_id', INVENTORY_ID);
  SELECT amcode_id, EXPLPERIOD_Y, EXPLPERIOD_M, BALANCECOST, FACTOR, 
         INOPERDATE, OUTOPERDATE, beginlossdate, endcost
    FROM INVENTORY
    WHERE ID = :INVENTORY_ID
    INTO :amcode_id, :PeriodY, :PeriodM, :BVALUE, :FACTOR, 
         :InDate, :OutDate, :BegLossDate, :EndCost;
  if (EndCost = 0) then begin
    ans = dm('*** Finish P_CALC_AMORT_PERIOD - EndCost=0');
    Exit;  
  end
  if ((BegLossDate <> '30.12.1899') and (BegLossDate is not null)) then
    InDate = BegLossDate;
  /* get depreciation rate from inventory card */
  CardAmRate = 1200.0 / (PeriodY * 12.0 + PeriodM);
  ans = wf('CardAmRate', 15, 4, CardAmRate);
  /**/
  execute procedure f_get_num_months(:INVENTORY_ID, :AMONTH, :InDate, :OutDate)
    returning_values :Months, :NumMonths;
  SaveMonths = Months;
  AmValue = 0;
  while (Months > 0) do begin
    /* find appropriate depreciation rate */
    NumMonths = NumMonths + 1;
    execute procedure f_get_amortrate(:amcode_id, :NumMonths)
      returning_values :AmRate;
    ans = wf('AmRate', 15, 4, AmRate);
    /* if rate from dictionary not found use rate from inventory card */
    if (AmRate = 0) then AmRate = CardAmRate;
    /* calc amort value for that month */ 
    AMVALUE = Round(AmValue + (BVALUE * AMRATE / 1200.0), :prec);
    Months = Months - 1;
  end
  IF ((FACTOR <> 0) AND (FACTOR IS NOT NULL)) THEN 
    SUMTOTAL = Round(AMVALUE * FACTOR, :prec);
  ELSE SUMTOTAL = AMVALUE;

  if (Round(sumtotal, :prec) > Round(endcost, :prec)) then
    sumtotal = endcost;

  INSERT INTO ACC_AMORTIZATION (ID, INVENTORY_ID, IMONTH, N_MONTHS, BATCH, amcode_id, amrate, 
                          SUM_PERIOD, FACTOR, SUM_TOTAL, BALANCECOST, EXPLPERIOD_Y, EXPLPERIOD_M, endcost)
    VALUES (GEN_ID(ACC_AMORTIZATION_ID_GEN,1), :INVENTORY_ID, :AMONTH, :SaveMonths, :BATCH, :amcode_id, :AMRATE, 
         :AMVALUE, :FACTOR, :SUMTOTAL, :BVALUE, :PeriodY, :PeriodM, :endcost);
  ans = dm('*** Finish P_CALC_AMORT_PERIOD');
END
^

ALTER PROCEDURE P_CALC_AMORT_PRODUCTION (
    INVENTORY_ID INTEGER,
    AMONTH INTEGER,
    BATCH INTEGER)
AS
DECLARE VARIABLE ANS INTEGER;
/*DECLARE VARIABLE LASTMONTH INTEGER;*/
DECLARE VARIABLE NUMMONTHS INTEGER;
DECLARE VARIABLE MONTHS INTEGER;
DECLARE VARIABLE SAVEMONTHS INTEGER;
DECLARE VARIABLE INDATE TIMESTAMP;
DECLARE VARIABLE OUTDATE TIMESTAMP;
DECLARE VARIABLE BEGLOSSDATE TIMESTAMP;
DECLARE VARIABLE BVALUE DOUBLE PRECISION;
DECLARE VARIABLE FACTOR DOUBLE PRECISION;
DECLARE VARIABLE VOL DOUBLE PRECISION;
DECLARE VARIABLE FactVOL DOUBLE PRECISION;
DECLARE VARIABLE TOTALVOL DOUBLE PRECISION; /* sum of Vol if depreciation is calculated over several months */
DECLARE VARIABLE amcode_id integer;
DECLARE VARIABLE ENDCOST DOUBLE PRECISION;
declare variable invhead_id integer;
declare variable SumProd numeric(15,4);
declare variable SumTotal numeric(15,4);
declare variable prec smallint;
BEGIN
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: расчет амортизации по продукции
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 17.02.2003 Кривопустов Округление до указанного в
                        конфигурации модуля
**********************************************************/
  ans = dm('*** Start P_CALC_AMORT_PRODUCTION');

  select c.currency_prec from acc_config c into :prec;

  ans = wi('inventory_id', INVENTORY_ID);
  SELECT amcode_id, INOPERDATE, OUTOPERDATE, FACTOR, BALANCECOST, 
         beginlossdate, endcost, invhead_id
    FROM INVENTORY
    WHERE ID = :INVENTORY_ID
    INTO :amcode_id, :InDate, :OutDate, :Factor, :BValue, 
         :BegLossDate, :EndCost, :invhead_id;
  if (EndCost = 0) then begin
    ans = dm('*** Finish P_CALC_AMORT_PRODUCTION - EndCost=0');
    Exit;  
  end
  if ((BegLossDate <> '30.12.1899') and (BegLossDate is not null)) then
    InDate = BegLossDate;
  execute procedure f_get_num_months(:INVENTORY_ID, :AMONTH, :InDate, :OutDate)
    returning_values :Months, :NumMonths;
  SaveMonths = Months;
  TotalVol = 0;
  while (Months > 0) do begin
    /* find appropriate estimated production volume */
    NumMonths = NumMonths + 1;
    execute procedure f_get_productvolume(:amcode_id, :NumMonths)
      returning_values :Vol;
    ans = wf('EstVol', 15, 4, Vol);
    TotalVol = Round(TotalVol + Vol, 3);
    Months = Months - 1;
  end
  if (SaveMonths > 0) then Vol = Round(TotalVol / SaveMonths, 3);

  select p.production
  from acc_invproduction p where (p.invhead_id = :invhead_id) and (p.amonth = :amonth)
  into :factvol;
  if (factvol is null) then factvol = 0;

  if (Vol <> 0) then
    SumProd = Round(BValue * factvol / Vol, :prec);
  else
    SumProd = 0;

  IF ((FACTOR <> 0) AND (FACTOR IS NOT NULL)) THEN 
    SUMTOTAL = Round(SumProd * FACTOR, :prec);
  ELSE SUMTOTAL = SumProd;

  if (Round(sumtotal, :prec) > Round(endcost, :prec)) then
    sumtotal = endcost;

  INSERT INTO ACC_AMORTIZATION (ID, INVENTORY_ID, IMONTH, N_MONTHS, BATCH, amcode_id, 
                          PRODUCT_EST, product_fact, FACTOR, BalanceCost, endcost, sum_product, sum_total)
  VALUES (GEN_ID(ACC_AMORTIZATION_ID_GEN,1), :INVENTORY_ID, :AMONTH, :SaveMonths, :BATCH, :amcode_id,
         :VOL, :factvol, :FACTOR, :BValue, :endcost, :sumprod, :sumtotal);
  ans = dm('*** Finish P_CALC_AMORT_PRODUCTION');
END
^

ALTER PROCEDURE P_CALC_BILLHEAD_ACCEPTSUM (
    DOC_ID INTEGER)
AS
declare variable summa numeric(15,4);
begin
  select sum(bs.acceptancesum)
  from docspec s, billspec bs
  where (bs.docspec_id = s.id) and
        (s.dochead_id = :doc_id)
  into :summa;
  
  update billhead bh
    set acceptancesum = :summa
    where bh.dochead_id = :doc_id;
end
^

ALTER PROCEDURE P_CALC_PRICE_IN_PRICELISTSPEC (
    BASEPRICE NUMERIC(15,4),
    SPEC_ID INTEGER)
AS
declare variable pricetype_id integer;
declare variable basepricetype_id integer;
declare variable relative_percent numeric(15,5);
begin
  /* get base price type */
  select ph.basepricetype from pricelisthead ph, pricelistfolder pf, pricelistspec ps
  where ((ph.id = pf.pricelisthead_id) and (pf.id = ps.folder_id) and (ps.id = :spec_id))
  into :basepricetype_id;

  update pricelistspec_price set
    price = :baseprice
  where ((pricelistspec_id = :spec_id) and (pricetype_id = :basepricetype_id));

  for
    select p.pricetype_id, l.relative_percent 
    from pricelistspec_price p, pricelist_pricetype_link l, pricelistfolder pf, pricelistspec ps, pricetype pt
    where ((l.pricelist_id = pf.pricelisthead_id) and (pt.id = p.pricetype_id) and (pt.use_relative_percent = 1) and (p.pricetype_id = l.pricetype_id)and (pf.id = ps.folder_id) and (ps.id = :spec_id)and (p.pricetype_id <> :basepricetype_id) and (p.pricelistspec_id = :spec_id))
    into :pricetype_id, :relative_percent 
  do begin
    update pricelistspec_price set
      price = Round4(:baseprice + :baseprice * :relative_percent / 100.0)
    where ((pricelistspec_id = :spec_id) and (pricetype_id = :pricetype_id));
  end /* for */
end
^

ALTER PROCEDURE P_CALCULATE_DOCSUM (
    DOCSECTION INTEGER,
    DOCUMENT_ID INTEGER)
AS
declare variable summacur numeric(15,4);
declare variable summanat numeric(15,4);
declare variable curcource numeric(15,4);
declare variable spec_id integer;
declare variable summa_with_discount numeric(15,4);
declare variable summanat_with_discount numeric(15,4);
declare variable summacur_with_discount numeric(15,4);
begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 15.12.2000
 Измененено:
 Назначение: Пересчет сумм заголовка документа
**********************************************************/
  /*Параметры документа*/
  select d.curcource
    from dochead d
    where d.id=:document_id
  into :curcource;

  /*Параметры спецификации*/
  select sum(ds.summa)
    from docspec ds
    where ds.dochead_id=:document_id
  into :summacur;

  if (summacur is null) then summacur=0;
  summanat = Round4(summacur * curcource);

  update dochead d set
        d.summacur=:summacur,
        d.summanat=:summanat
  where d.id=:document_id;

  /*Суммы без наценок/скидок */
    /*Расходные ордера*/
    if (docsection=17) then begin
      select sum (s.summa_with_discount)
        from stockdocumentspec s
             join docspec ds on ds.id=s.docspec_id
        where ds.dochead_id=:document_id
        into :summa_with_discount;

      if (summa_with_discount is null) then summa_with_discount=0;
      summacur_with_discount=:summa_with_discount;
      summanat_with_discount = Round4(summacur_with_discount * curcource);
      update stockdocumenthead d set
             d.summacur_with_discount=:summacur_with_discount,
             d.summanat_with_discount=:summacur_with_discount
      where d.dochead_id=:document_id;
    end
    /*Заказы покупателей*/
    if (docsection=22) then begin
      select sum (s.summa_with_discount)
        from orderspec s
             join docspec ds on ds.id=s.docspec_id
        where ds.dochead_id=:document_id
        into :summa_with_discount;

      if (summa_with_discount is null) then summa_with_discount=0;
      summacur_with_discount=:summa_with_discount;
      summanat_with_discount = Round4(summacur_with_discount * curcource);
      update orderhead d set
             d.summacur_with_discount=:summacur_with_discount,
             d.summanat_with_discount=:summacur_with_discount
      where d.dochead_id=:document_id;
    end
    /*Исходящие накладные*/
    if (docsection=24) then begin
      select sum (s.summa_with_discount)
        from invoicespec s
             join docspec ds on ds.id=s.docspec_id
        where ds.dochead_id=:document_id
        into :summa_with_discount;

      if (summa_with_discount is null) then summa_with_discount=0;
      summacur_with_discount=:summa_with_discount;
      summanat_with_discount = Round4(summacur_with_discount * curcource);
      update invoicehead d set
             d.summacur_with_discount=:summacur_with_discount,
             d.summanat_with_discount=:summacur_with_discount
      where d.dochead_id=:document_id;
    end
    /*Исходящие счета*/
    if (docsection=26) then begin
      select sum (s.summa_with_discount)
        from billspec s
             join docspec ds on ds.id=s.docspec_id
        where ds.dochead_id=:document_id
        into :summa_with_discount;

      if (summa_with_discount is null) then summa_with_discount=0;
      summacur_with_discount=:summa_with_discount;
      summanat_with_discount = Round4(summacur_with_discount * curcource);
      update billhead d set
             d.summacur_with_discount=:summacur_with_discount,
             d.summanat_with_discount=:summacur_with_discount
      where d.dochead_id=:document_id;
    end
end
^

ALTER PROCEDURE P_CHANGE_ACC_ACCTYPE (
    ACC VARCHAR(20),
    PERIOD_FROM INTEGER,
    PERIOD_TO INTEGER,
    ACCTYPE_TO INTEGER)
AS
begin
  suspend;
end
^

ALTER PROCEDURE P_CHANGE_DBKT_POLICY (
    ACC VARCHAR(20),
    PERIOD_FROM INTEGER,
    PERIOD_TO INTEGER,
    POLICY_TO INTEGER)
AS
begin
  suspend;
end
^

ALTER PROCEDURE P_CHANGE_MEAS_ON_CATALOG (
    MEAS_IN VARCHAR(5),
    MEAS_OUT VARCHAR(5))
RETURNS (
    CATALOG_ID INTEGER,
    CNAME VARCHAR(80),
    MEAS_PROV CHAR(5))
AS
/*declare variable catalog_id integer;
declare variable meas_prov char (5);*/
begin
/*  Now not implemented. 15.06.2001. Comment by OVS */
/**********************************************************
 Автор: Арычков Денис
 Создано: 22.01.2001
 Измененено:
 Назначение: Изменение кода основной единицы измерения у всех
 позиций КТУ.
**********************************************************/
  /**/
  select m.upcode
    from measure m
    where (m.code=:meas_out)
  into :meas_prov;
  if (meas_prov is null) then exit;
  /*suspend;*/

  /*for
  select cat.id, cat.cname
    from catalog cat
        left join measure m on m.upcode = cat.measure1
    where (m.code = :meas_in )
    into :catalog_id, :cname
  do begin
    update catalog cat
      set cat.measure1 = :meas_prov
    where cat.id = :catalog_id;
    suspend;
  end*/
end
^

ALTER PROCEDURE P_CHECK_PERIOD (
    PERIOD_ID INTEGER)
AS
declare variable dateclose  timestamp;
begin
  select p.dateclose from period p where p.id = :period_id
  into :dateclose;
  if (dateclose is not null) then exception E_Period_Is_Close;
end
^

ALTER PROCEDURE P_CHECK_PERIODS_CROSS (
    DATEFROM TIMESTAMP,
    DATETO TIMESTAMP)
AS
DECLARE VARIABLE FINDCOUNT INTEGER;
begin
  select count(*) from period p
  where ((p.datefrom between :datefrom and :dateto) or (p.dateto between :datefrom and :dateto) or (p.datefrom <= :datefrom) and (p.dateto >= :dateto))
  into :findcount;
  if (findcount > 0) then exception E_PERIODS_IS_CROSS;
end
^

ALTER PROCEDURE P_CHECK_PERMISSIBLEACC (
    ACCDB INTEGER,
    ANLDB1 INTEGER,
    ANLDB2 INTEGER,
    ANLDB3 INTEGER,
    ANLDB4 INTEGER,
    ANLDB5 INTEGER,
    ACCKT INTEGER,
    ANLKT1 INTEGER,
    ANLKT2 INTEGER,
    ANLKT3 INTEGER,
    ANLKT4 INTEGER,
    ANLKT5 INTEGER)
AS
declare variable reccount smallint;
begin
  if (not exists (select * from permissibleaccounts)) then Exit;
  /* Search */
  execute procedure F_FIND_PERMISSIBLEACCOUNTS
    :ACCDB, :ANLDB1, :ANLDB2, :ANLDB3, :ANLDB4, :ANLDB5,
    :ACCKT, :ANLKT1, :ANLKT2, :ANLKT3, :ANLKT4, :ANLKT5
  returning_values :reccount;
  if (reccount = 0) then exception E_PermissibleAcc_Wrong;
end
^

ALTER PROCEDURE P_COMMIT_AMORT (
    ABATCH INTEGER,
    AFOLDERID INTEGER,
    USER_ID INTEGER)
AS
declare variable AmsId integer;
declare variable InvId integer;
declare variable SumTotal double precision;
declare variable AbsMonth integer;
declare variable CalcYear integer;
declare variable CalcMonth integer;
declare variable CalcDay integer;
declare variable CalcDate  timestamp;
declare variable Contr integer;
declare variable OperId integer;
declare variable SpecId integer;
begin
  for select distinct i.contractor_id, a.imonth
      from ACC_AMORTIZATION a join inventory i on i.id = a.inventory_id
      where a.batch = :ABatch
      into :Contr, :AbsMonth
  do begin
    /* find date of calculation (last day of month)*/
    CalcYear = Div(AbsMonth, 12);
    CalcMonth = AbsMonth - (CalcYear * 12);
    if (CalcMonth = 0) then begin
      CalcYear = CalcYear - 1;
      CalcMonth = 12;
    end
    /*execute procedure DaysOfMonth(:CalcYear, :CalcMonth)
      returning_values :CalcDay;*/
    CalcDay = DaysPerMonth(CalcMonth, CalcYear);
    execute procedure EncodeDate(:CalcYear, :CalcMonth, :CalcDay)
      returning_values :CalcDate;
    /**/
    OperId = gen_id(economicoper_id_gen, 1);
    insert into economicoper (id, folder_id, comment, keepdate, FROM_ID, insertsign, user_id, contractdate)
      values (:OperId, :AFolderId, 'Начислена амортизация', :CalcDate, :Contr, 1, :user_id, '30.12.1899');
    /**/
    for select a.id, a.inventory_id, a.SUM_TOTAL
        from ACC_AMORTIZATION a join inventory i on i.id = a.inventory_id
        where (a.batch = :ABatch) and
          ((i.contractor_id = :Contr) or ((:Contr is null) and (i.contractor_id is null)))
        into :AmsId, :InvId, :SumTotal 
    do begin
      /* update inventory card */
      update INVENTORY set
        AMORT = AMORT + :SumTotal,
        ENDCOST = ENDCOST - :SumTotal,
        AMORTDATE = :CalcDate
        where id = :InvId;
      /**/
      SpecId = gen_id(ECONOMICSPEC_ID_GEN,1);
      insert into economicspec (id, ECONOMICOPER_ID, 
         ACCDB_id, ANLDB1_ID, ANLDB2_ID, ANLDB3_ID, ANLDB4_ID, ANLDB5_ID,
         ACCKT_id, ANLKT1_ID, ANLKT2_ID, ANLKT3_ID, ANLKT4_ID, ANLKT5_ID,
         CATALOG_ID, QUANTITY, SUMMANAT)
        select :SpecId, :OperId,
            i.ACCDB_id, i.ANLDB1_ID, i.ANLDB2_ID, i.ANLDB3_ID, i.ANLDB4_ID, i.ANLDB5_ID,
            i.ACCKT_id, i.ANLKT1_ID, i.ANLKT2_ID, i.ANLKT3_ID, i.ANLKT4_ID, i.ANLKT5_ID,
            i.CATALOG_ID, 1, :SumTotal
          from inventory i
          where i.id = :InvId;
      /**/
      insert into invhistory (ID, INVENTORY_ID, AMORTIZATION_ID, 
         EO_ID, ES_ID, KIND, DELTA_BALCOST, DELTA_DEPRVAL, 
         REVAL_FACTOR, REVAL_SUM, ACT_DATE)
        values (gen_id(INVHISTORY_ID_GEN,1), :InvId, :AmsId,
         :OperId, :SpecId, 0, null, (-1 * :SumTotal), null, null, :CalcDate);
    end
    /**/
    update ECONOMICOPER set
      INSERTSIGN = 0,
      user_id = :user_id
    where ID = :OperId;
  end
end
^

ALTER PROCEDURE P_COMPLETE_WF_ACTIVITY (
    ID INTEGER,
    LAST_STATE_TIME TIMESTAMP)
AS
begin
  delete from wf_assignment where wf_activity_id = :id;
end
^

ALTER PROCEDURE P_COPY_PRICELISTFOLDER (
    SRCPRLIST INTEGER,
    DSTPRLIST INTEGER,
    SRCPARENT INTEGER,
    DSTPARENT INTEGER)
AS
DECLARE VARIABLE OLDID INTEGER;
DECLARE VARIABLE NEWID INTEGER;
DECLARE VARIABLE OLDPARENT INTEGER;
DECLARE VARIABLE OLDNAME VARCHAR(80);
DECLARE VARIABLE OLDSPECID INTEGER;
DECLARE VARIABLE NEWSPECID INTEGER;
DECLARE VARIABLE OLDCATALOGID INTEGER;
DECLARE VARIABLE OLDPRICE NUMERIC(15,4);
DECLARE VARIABLE OLDLASTCOST NUMERIC(15,4);
DECLARE VARIABLE SNAME VARCHAR(80);
DECLARE VARIABLE CANCELED SMALLINT;
DECLARE VARIABLE ACTDATE TIMESTAMP;
DECLARE VARIABLE ACTDATETILL TIMESTAMP;
begin
  /* Copy price list folder */
  for
    select f.id, f.parent_id, f.fname from pricelistfolder f
    where (f.pricelisthead_id = :srcprlist) and (((f.parent_id is null) and (:srcparent is null)) or (f.parent_id = :srcparent))
    into :oldId, :oldParent, :oldName
  do begin
    newId = gen_id(pricelistfolder_id_gen, 1);
    insert into pricelistfolder(id, pricelisthead_id, parent_id, fname)
    values (:newId, :dstprlist, :dstparent, :oldName);

    /* Copy price list spec */
    for
      select s.id, s.catalog_id, s.price, s.lastcost, s.sname, s.canceled, s.actdate, s.actdatetill
      from pricelistspec s
      where s.folder_id = :oldId
      into :oldSpecId, :oldCatalogId, :oldPrice, :oldLastCost, :sname, :canceled, :actdate, :actdatetill
    do begin
      newSpecId = gen_id(pricelistspec_id_gen, 1);
      
      insert into pricelistspec(id, folder_id, catalog_id, price, lastcost, sname, canceled, pricelisthead_id, actdate, actdatetill)
      values (:newSpecId, :newId, :oldCatalogId, :oldPrice, :oldLastCost, :sname, :canceled, :dstprlist, :actdate, :actdatetill);
      
      execute procedure p_copydetail_pricelistspec :oldSpecId, :newSpecId;
    end

    /* rights  */
    insert into folder_rights(id, group_id, folderpart, folder_id, rights)
    select gen_id(folder_rights_id_gen,1), fr.group_id, fr.folderpart, :newId, fr.rights
    from folder_rights fr
    where (fr.folderpart = 2) and (fr.folder_id = :oldId);
        
    execute procedure p_copy_pricelistfolder :srcprlist, :dstprlist, :oldId, :newId;
  end
end
^

ALTER PROCEDURE P_COPY_RPTBAND (
    SRCRPTID INTEGER,
    DSTRPTID INTEGER,
    SRCPARENTID INTEGER,
    DSTPARENTID INTEGER)
AS
declare variable OldId integer;
declare variable OldCode char(20);
declare variable OldPriority smallint;
declare variable OldParentId integer;
declare variable OldKind smallint;
declare variable OldIsPageHeader smallint;
declare variable OldIsPageBreak smallint;
declare variable OldHide smallint;
declare variable NewId integer;
declare variable NewParentId integer;
declare variable OldQueryId integer;
declare variable NewQueryId integer;
declare variable OldQuery varchar(8182);
declare variable OldQueryCode char(20);
declare variable OldQueryIsAlg smallint;
declare variable OldQueryAlgId integer;
begin
  for
    select b.id, b.code, b.priority, b.parent_id_nn, b.kind, b.ispageheader,
      b.ispagebreak, b.hide
    from rptbands b
    where (b.rpt_id = :SrcRptId) and (b.parent_id_nn = :SrcParentId)
    into :OldId, :OldCode, :OldPriority, :OldParentId, :OldKind, :OldIsPageHeader,
      :OldIsPageBreak, :OldHide
  do begin
    NewId = gen_id(rptbands_id_gen,1);

    if (DstParentId = 0) then NewParentId = null;
    else NewParentId = DstParentId;

    insert into rptbands (id, code, rpt_id, priority, parent_id, parent_id_nn,
      kind, ispageheader, ispagebreak, hide)
    values (:NewId, :OldCode, :DstRptId, :OldPriority, :NewParentId, :DstParentId,
      :OldKind, :OldIsPageHeader, :OldIsPageBreak, :OldHide);

    for
      select q.id, q.query, q.code, q.is_algorithm, q.alg_id
      from rptqueries q
      where (q.band_id = :OldId)
      into :OldQueryId, :OldQuery, :OldQueryCode, :oldqueryisalg, :oldqueryalgid
    do begin
      NewQueryId = gen_id(rptqueries_id_gen,1);

      insert into rptqueries (id, band_id, query, code, is_algorithm, alg_id)
      values (:NewQueryId, :NewId, :OldQuery, :OldQueryCode, :oldqueryisalg, :oldqueryalgid);

      insert into rptfields (id, band_id, query_id, name, kind, masterband_id, masterfield_id,
        prec, setnull, format)
      select gen_id(rptfields_id_gen,1), :NewId, :NewQueryId, f.name, f.kind, null, null,
        f.prec, f.setnull, f.format
      from rptfields f
      where (f.band_id = :OldId) and (f.query_id = :OldQueryId);
    end

    insert into rptparams (id, rpt_id, name, label, ptype, masterband_id, meta, meta_id, priority)
    select gen_id(rptparams_id_gen,1), :DstRptId, p.name, p.label, p.ptype, :NewId, 0, null, p.priority
    from rptparams p
    where (p.rpt_id = :SrcRptId) and (p.masterband_id = :OldId);

    insert into rptfields (id, band_id, query_id, name, kind, masterband_id, masterfield_id,
      prec, setnull, format)
    select gen_id(rptfields_id_gen,1), :NewId, null, f.name, f.kind, null, null,
      f.prec, f.setnull, f.format
    from rptfields f
    where (f.band_id = :OldId) and (f.query_id is null);

    execute procedure p_copy_rptband(:SrcRptId, :DstRptId, :OldId, :NewId);
  end
end
^

ALTER PROCEDURE P_COPYDETAIL_CALCTAXESKIND (
    SRCHEAD_ID INTEGER,
    DSTHEAD_ID INTEGER)
AS
begin
  insert into calctaxeslink (id, kind_id, tax_id, feeorder, included, subject)
  select gen_id(calctaxeslink_id_gen, 1), :DSTHEAD_ID, tax_id, feeorder, included, subject
  from calctaxeslink where (kind_id = :srchead_id);
end
^

ALTER PROCEDURE P_COPYDETAIL_CATALOG (
    SRCHEAD_ID INTEGER,
    DSTHEAD_ID INTEGER)
AS
begin
  /* Packing */
  insert into packing(id, catalog_id, measure_id, priority, weight, weightmeasure_id, volume, volumemeasure_id)
  select gen_id(catalog_id_gen, 1), :dsthead_id, measure_id, priority, weight, weightmeasure_id, volume, volumemeasure_id
  from packing
  where catalog_id = :srchead_id;
  /* SetOfGood */
  insert into setofgood(id, good_id, component_id, quantity, pricerelate)
  select gen_id(setofgood_id_gen, 1), :dsthead_id, component_id, quantity, pricerelate
  from setofgood
  where good_id = :srchead_id;
end
^

ALTER PROCEDURE P_COPYDETAIL_CONTACTHIST (
    SRCHEAD_ID INTEGER,
    DSTHEAD_ID INTEGER)
AS
begin
  /* ContactHistFiles */
  insert into pr_contact_hist_files(id, contacthist_id, filename, fdescription)
  select gen_id(pr_contact_hist_files_id_gen, 1), :dsthead_id, filename, fdescription
  from pr_contact_hist_files
  where contacthist_id = :srchead_id;
end
^

ALTER PROCEDURE P_COPYDETAIL_FEATURE (
    SRCHEAD_ID INTEGER,
    DSTHEAD_ID INTEGER)
AS
begin
  insert into featureval(ID,FEATURE_ID,VAL)
    select gen_id(featureval_id_gen, 1), :dsthead_id, val
    from featureval
    where feature_id = :srchead_id;
end
^

ALTER PROCEDURE P_COPYDETAIL_FINOPER (
    SRCHEAD_ID INTEGER,
    DSTHEAD_ID INTEGER)
AS
declare variable id integer;
declare variable srcacc_id integer;
declare variable dstacc_id integer;
declare variable srcanl1_id integer;
declare variable srcanl2_id integer;
declare variable srcanl3_id integer;
declare variable srcanl4_id integer;
declare variable srcanl5_id integer;
declare variable dstanl1_id integer;
declare variable dstanl2_id integer;
declare variable dstanl3_id integer;
declare variable dstanl4_id integer;
declare variable dstanl5_id integer;
declare variable sumnat numeric(15,4);                                        
declare variable sumcur numeric(15,4);
declare variable planned integer;
declare variable new_id integer;
begin
  for
    select s.id,s.srcacc_id,s.dstacc_id,s.srcanl1_id,
      s.srcanl2_id,s.srcanl3_id,s.srcanl4_id,s.srcanl5_id,s.dstanl1_id,
      s.dstanl2_id,s.dstanl3_id,s.dstanl4_id,s.dstanl5_id,s.sumnat,s.sumcur,s.planned
    from finspec s
    where (s.finoper_id = :srchead_id) and (s.parent_id is null)
    into :id,:srcacc_id,:dstacc_id,:srcanl1_id,
      :srcanl2_id,:srcanl3_id,:srcanl4_id,:srcanl5_id,:dstanl1_id,
      :dstanl2_id,:dstanl3_id,:dstanl4_id,:dstanl5_id,:sumnat,:sumcur,:planned
  do begin

    new_id = gen_id(finspec_id_gen,1);

    insert into finspec ( ID, FINOPER_ID, PARENT_ID, SRCACC_ID, DSTACC_ID, SRCANL1_ID,
      SRCANL2_ID, SRCANL3_ID, SRCANL4_ID, SRCANL5_ID, DSTANL1_ID, DSTANL2_ID,
      DSTANL3_ID, DSTANL4_ID, DSTANL5_ID, SUMNAT, SUMCUR, PLANNED )
    values ( :new_id, :DSTHEAD_ID, null, :SRCACC_ID, :DSTACC_ID, :SRCANL1_ID,
      :SRCANL2_ID, :SRCANL3_ID, :SRCANL4_ID, :SRCANL5_ID, :DSTANL1_ID, :DSTANL2_ID,
      :DSTANL3_ID, :DSTANL4_ID, :DSTANL5_ID, :SUMNAT, :SUMCUR, :PLANNED );

    insert into finspec ( ID, FINOPER_ID, PARENT_ID, SRCACC_ID, DSTACC_ID, SRCANL1_ID,
      SRCANL2_ID, SRCANL3_ID, SRCANL4_ID, SRCANL5_ID, DSTANL1_ID, DSTANL2_ID,
      DSTANL3_ID, DSTANL4_ID, DSTANL5_ID, SUMNAT, SUMCUR, PLANNED )
    select gen_id(finspec_id_gen,1), :DSTHEAD_ID, :new_id, s.SRCACC_ID, s.DSTACC_ID, s.SRCANL1_ID,
      s.SRCANL2_ID, s.SRCANL3_ID, s.SRCANL4_ID, s.SRCANL5_ID, s.DSTANL1_ID, s.DSTANL2_ID,
      s.DSTANL3_ID, s.DSTANL4_ID, s.DSTANL5_ID, s.SUMNAT, s.SUMCUR, s.PLANNED
    from finspec s
    where (s.finoper_id = :srchead_id) and (s.parent_id = :id);
    
  end /* for select */
end
^

ALTER PROCEDURE P_COPYDETAIL_FINOPERMODEL (
    SRCHEAD_ID INTEGER,
    DSTHEAD_ID INTEGER)
AS
declare variable id integer;
declare variable srcacc_id integer;
declare variable dstacc_id integer;
declare variable srcanl1_id integer;
declare variable srcanl2_id integer;
declare variable srcanl3_id integer;
declare variable srcanl4_id integer;
declare variable srcanl5_id integer;
declare variable dstanl1_id integer;
declare variable dstanl2_id integer;
declare variable dstanl3_id integer;
declare variable dstanl4_id integer;
declare variable dstanl5_id integer;
declare variable sumnat numeric(15,4);                                        
declare variable sumcur numeric(15,4);
declare variable planned integer;
declare variable formula varchar(2048);
declare variable alg_id integer;
declare variable new_id integer;
begin
  for
    select s.id,s.srcacc_id,s.dstacc_id,s.srcanl1_id,
      s.srcanl2_id,s.srcanl3_id,s.srcanl4_id,s.srcanl5_id,s.dstanl1_id,
      s.dstanl2_id,s.dstanl3_id,s.dstanl4_id,s.dstanl5_id,s.sumnat,s.sumcur,
      s.planned,s.formula,s.alg_id
    from finspecmodel s
    where (s.finoper_id = :srchead_id) and (s.parent_id is null)
    into :id,:srcacc_id,:dstacc_id,:srcanl1_id,
      :srcanl2_id,:srcanl3_id,:srcanl4_id,:srcanl5_id,:dstanl1_id,
      :dstanl2_id,:dstanl3_id,:dstanl4_id,:dstanl5_id,:sumnat,:sumcur,
      :planned,:formula,:alg_id
  do begin

    new_id = gen_id(finspecmodel_id_gen,1);

    insert into finspecmodel ( ID, FINOPER_ID, PARENT_ID, SRCACC_ID, DSTACC_ID, SRCANL1_ID,
      SRCANL2_ID, SRCANL3_ID, SRCANL4_ID, SRCANL5_ID, DSTANL1_ID, DSTANL2_ID,
      DSTANL3_ID, DSTANL4_ID, DSTANL5_ID, SUMNAT, SUMCUR, PLANNED, FORMULA, ALG_ID )
    values ( :new_id, :DSTHEAD_ID, null, :SRCACC_ID, :DSTACC_ID, :SRCANL1_ID,
      :SRCANL2_ID, :SRCANL3_ID, :SRCANL4_ID, :SRCANL5_ID, :DSTANL1_ID, :DSTANL2_ID,
      :DSTANL3_ID, :DSTANL4_ID, :DSTANL5_ID, :SUMNAT, :SUMCUR, :PLANNED, :FORMULA, :ALG_ID );

    insert into finspecmodel ( ID, FINOPER_ID, PARENT_ID, SRCACC_ID, DSTACC_ID, SRCANL1_ID,
      SRCANL2_ID, SRCANL3_ID, SRCANL4_ID, SRCANL5_ID, DSTANL1_ID, DSTANL2_ID,
      DSTANL3_ID, DSTANL4_ID, DSTANL5_ID, SUMNAT, SUMCUR, PLANNED, FORMULA, ALG_ID )
    select gen_id(finspecmodel_id_gen,1), :DSTHEAD_ID, :new_id, s.SRCACC_ID, s.DSTACC_ID, s.SRCANL1_ID,
      s.SRCANL2_ID, s.SRCANL3_ID, s.SRCANL4_ID, s.SRCANL5_ID, s.DSTANL1_ID, s.DSTANL2_ID,
      s.DSTANL3_ID, s.DSTANL4_ID, s.DSTANL5_ID, s.SUMNAT, s.SUMCUR, S.PLANNED, S.FORMULA, S.ALG_ID
    from finspecmodel s
    where (s.finoper_id = :srchead_id) and (s.parent_id = :id);
    
  end /* for select */
end
^

ALTER PROCEDURE P_COPYDETAIL_GROUP (
    SRCHEAD_ID INTEGER,
    DSTHEAD_ID INTEGER)
AS
declare variable f_part integer;
declare variable f_id integer;
declare variable rights integer;
declare variable ex_id integer;
declare variable ex_rights integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: копирование группы пользователей
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 27.05.2003 Кривопустов Добавлены таблицы rpt_rights,
   pricelisthead_rights, docprocessstage_rights, doctype_rights,
   wh_security
**********************************************************/
  insert into sec_method_access(id, group_id, method_id, permission)
    select gen_id(sec_method_access_id_gen, 1), :dsthead_id, method_id, permission
    from sec_method_access
    where group_id = :srchead_id;

  insert into sec_link_users_groups(id, user_id, group_id)
    select gen_id(sec_link_users_groups_id_gen, 1), l.user_id, :dsthead_id
    from sec_link_users_groups l
    where l.group_id = :srchead_id;

  insert into sec_module_access(id, module_id, group_id)
    select gen_id(sec_module_access_id_gen, 1), l.module_id, :dsthead_id
    from sec_module_access l
    where l.group_id = :srchead_id;

  for
    select r.folderpart, r.folder_id, r.rights
    from folder_rights r
    where r.group_id = :srchead_id
    into :f_part, :f_id, :rights
  do begin
    ex_id = null;
    select r.id, r.rights from folder_rights r
    where (r.group_id = :dsthead_id) and
      (r.folderpart = :f_part) and
      (r.folder_id = :f_id)
    into :ex_id, :ex_rights;

    if (ex_id is null) then
      insert into folder_rights(id, group_id, folderpart, folder_id, rights)
      values (gen_id(folder_rights_id_gen, 1), :dsthead_id, :f_part, :f_id, :rights);
    else if (ex_rights <> rights) then
      update folder_rights set rights = :rights
      where id = :ex_id;
  end

  insert into rpt_rights(id, group_id, rpt_id, rights)
    select gen_id(rpt_rights_id_gen, 1), :dsthead_id, rpt_id, rights
    from rpt_rights
    where group_id = :srchead_id;

  insert into pricelisthead_rights(id, group_id, rec_id, rights)
    select gen_id(pricelisthead_rights_id_gen, 1), :dsthead_id, rec_id, rights
    from pricelisthead_rights
    where group_id = :srchead_id;

  insert into docprocessstage_rights(id, group_id, stage_id, grants)
    select gen_id(docprocessstagerights_id_gen, 1), :dsthead_id, stage_id, grants
    from docprocessstage_rights
    where group_id = :srchead_id;

  insert into doctype_rights(id, group_id, rec_id, rights)
    select gen_id(doctype_rights_id_gen, 1), :dsthead_id, rec_id, rights
    from doctype_rights
    where group_id = :srchead_id;

  insert into wh_security(id, group_id, disable_spec_price, disable_discount, disable_exceed_quant)
    select gen_id(wh_security_id_gen, 1), :dsthead_id, disable_spec_price, disable_discount, disable_exceed_quant
    from wh_security
    where group_id = :srchead_id;
end
^

ALTER PROCEDURE P_COPYDETAIL_INVHEAD (
    SRCHEAD_ID INTEGER,
    DSTHEAD_ID INTEGER)
AS
begin
  insert into invmetal (id, invhead_id, metal_code, mass)
    select gen_id(invmetal_id_gen,1), :dsthead_id, im.metal_code, im.mass 
    from invmetal im
    where (im.invhead_id = :srchead_id);

  insert into inventory (
    id,
    folder_id,
    parent_id,
    groupnum,
    cardnum,
    objnum,
    okof_id,
    invlocation_id,
    manufacturer,
    model,
    serialnum,
    passpnum,
    inoperdocnum,
    inoperdate,
    outoperdocnum,
    outoperdate,
    balancecost,
    begincost,
    beginloss,
    amort,
    endcost,
    beginlossdate,
    amortdate,
    iscomplex,
    iscommon,
    factor,
    algorithm,
    yearamortrate,
    explperiod_y,
    explperiod_m,
    production,
    anl1_id,
    anl2_id,
    anl3_id,
    anl4_id,
    anl5_id,
    anldb1_id,
    anldb2_id,
    anldb3_id,
    anldb4_id,
    anldb5_id,
    anlkt1_id,
    anlkt2_id,
    anlkt3_id,
    anlkt4_id,
    anlkt5_id,
    comment,
    catalog_id,
    initialloss,
    acc_id,
    accdb_id,
    acckt_id,
    contractor_id,
    amcode_id,
    invname,
    incomedocnum,
    incomedate,
    invhead_id,
    acckind_id,
    accgroup_id)
  select
    gen_id(inventory_id_gen, 1),
    i.folder_id,
    i.parent_id,
    i.groupnum,
    i.cardnum,
    i.objnum,
    i.okof_id,
    i.invlocation_id,
    i.manufacturer,
    i.model,
    i.serialnum,
    i.passpnum,
    i.inoperdocnum,
    i.inoperdate,
    i.outoperdocnum,
    i.outoperdate,
    i.balancecost,
    i.begincost,
    i.beginloss,
    i.amort,
    i.endcost,
    i.beginlossdate,
    i.amortdate,
    i.iscomplex,
    i.iscommon,
    i.factor,
    i.algorithm,
    i.yearamortrate,
    i.explperiod_y,
    i.explperiod_m,
    i.production,
    i.anl1_id,
    i.anl2_id,
    i.anl3_id,
    i.anl4_id,
    i.anl5_id,
    i.anldb1_id,
    i.anldb2_id,
    i.anldb3_id,
    i.anldb4_id,
    i.anldb5_id,
    i.anlkt1_id,
    i.anlkt2_id,
    i.anlkt3_id,
    i.anlkt4_id,
    i.anlkt5_id,
    i.comment,
    i.catalog_id,
    i.initialloss,
    i.acc_id,
    i.accdb_id,
    i.acckt_id,
    i.contractor_id,
    i.amcode_id,
    i.invname,
    i.incomedocnum,
    i.incomedate,
    :dsthead_id,
    i.acckind_id,
    i.accgroup_id
    from inventory i
    where (i.invhead_id = :srchead_id);

  update inventory i set
    i.amort = 0,
    i.amortdate = null,
    i.endcost = i.balancecost - i.initialloss - i.beginloss
  where i.invhead_id = :dsthead_id;
end
^

ALTER PROCEDURE P_COPYDETAIL_PARTNER (
    SRCHEAD_ID INTEGER,
    DSTHEAD_ID INTEGER)
AS
begin
  insert into ref_bank_account(id, contractor_id, bank_id, name, account_type_id, curcode, account, is_default)
  select gen_id(ref_bank_account_id_gen, 1), :dsthead_id, bank_id, name, account_type_id, curcode, account, is_default
  from ref_bank_account
  where contractor_id = :srchead_id;

  /* PartnEmpl */
  insert into partnempl(id, partner_id, code, fname, patronymic, surname, address, militia, milit_city, passp_date, passp_num, passp_ser, office, comment)
  select gen_id(partnempl_id_gen, 1), :dsthead_id, code, fname, patronymic, surname, address, militia, milit_city, passp_date, passp_num, passp_ser, office, comment
  from partnempl
  where partner_id = :srchead_id;
end
^

ALTER PROCEDURE P_COPYDETAIL_PRICELIST (
    SRCHEAD_ID INTEGER,
    DSTHEAD_ID INTEGER)
AS
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: 
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 05.03.2005 Кривопустов Новые поля таблицы PRICELIST_PRICETYPE_LINK
**********************************************************/
  /* pricetype */
  insert into pricelist_pricetype_link(pricelist_id, pricetype_id, priority, relative_percent, formula,
    alg_id, id)
  select :dsthead_id, pricetype_id, priority, relative_percent, formula,
    alg_id, gen_id(pricelist_pt_link_id_gen, 1)
  from pricelist_pricetype_link
  where pricelist_id = :srchead_id;
  /* delete price list folder, 23.01.2003 added by OVS */
  delete from pricelistfolder f where f.pricelisthead_id = :dsthead_id;
  /* rights */
  /*insert into pricelisthead_rights(id, rec_id, group_id, rights)
    select gen_id(pricelisthead_rights_id_gen,1), :dsthead_id, r.group_id, r.rights
    from pricelisthead_rights r where r.rec_id = :srchead_id;*/
  /* pricelistfolder */
  execute procedure p_copy_pricelistfolder :srchead_id, :dsthead_id, null, null;
end
^

ALTER PROCEDURE P_COPYDETAIL_PRICELISTSPEC (
    SRCHEAD_ID INTEGER,
    DSTHEAD_ID INTEGER)
AS
declare variable pricetype_id integer;
declare variable price numeric(15,4);
begin
  for
    select pricetype_id, price from pricelistspec_price
    where pricelistspec_id = :srchead_id
    into :pricetype_id, :price
  do
    update pricelistspec_price set
      price = :price
    where (pricelistspec_id = :dsthead_id) and (pricetype_id = :pricetype_id);
end
^

ALTER PROCEDURE P_COPYDETAIL_TAXGROUP (
    SRCHEAD_ID INTEGER,
    DSTHEAD_ID INTEGER)
AS
begin
  insert into taxlink(taxgroup_id, tax_id, feeorder)
  select :dsthead_id, tax_id, feeorder from taxlink
  where taxgroup_id = :srchead_id;
end
^

ALTER PROCEDURE P_CORRECT_REMNACC (
    ACC VARCHAR(20),
    PERIOD_ID INTEGER)
AS
begin
  suspend;
end
^

ALTER PROCEDURE P_CREATE_PERMISACC_FROM_EO (
    BEGINDATE TIMESTAMP,
    ENDDATE TIMESTAMP)
AS
declare variable accdb integer;
declare variable acckt integer;
declare variable anldb1_id integer;
declare variable anldb2_id integer;
declare variable anldb3_id integer;
declare variable anldb4_id integer;
declare variable anldb5_id integer;
declare variable anlkt1_id integer;
declare variable anlkt2_id integer;
declare variable anlkt3_id integer;
declare variable anlkt4_id integer;
declare variable anlkt5_id integer;
declare variable reccount integer;
begin
  for
    select distinct es.accdb_id, es.anldb1_id, es.anldb2_id, es.anldb3_id, es.anldb4_id, es.anldb5_id,
           es.acckt_id, es.anlkt1_id, es.anlkt2_id, es.anlkt3_id, es.anlkt4_id, es.anlkt5_id
    from economicspec es, economicoper eo
    where ((eo.keepdate between :begindate and :enddate) and (eo.id = es.economicoper_id))
    into :accdb, :anldb1_id, :anldb2_id, :anldb3_id, :anldb4_id, :anldb5_id,
         :acckt, :anlkt1_id, :anlkt2_id, :anlkt3_id, :anlkt4_id, :anlkt5_id
  do begin
    /* Search */
    if (not exists(
        select * from permissibleaccounts p
        where
         ( p.accdb_id = :AccDb ) and
         ((p.anldb1_id = :Anldb1_id)or((p.anldb1_id is null)and(:Anldb1_id is null))) and
         ((p.anldb2_id = :Anldb2_id)or((p.anldb2_id is null)and(:Anldb2_id is null))) and 
         ((p.anldb3_id = :Anldb3_id)or((p.anldb3_id is null)and(:Anldb3_id is null))) and 
         ((p.anldb4_id = :Anldb4_id)or((p.anldb4_id is null)and(:Anldb4_id is null))) and 
         ((p.anldb5_id = :Anldb5_id)or((p.anldb5_id is null)and(:Anldb5_id is null))) and
         ( p.acckt_id = :AccKt ) and
         ((p.anlkt1_id = :Anlkt1_id)or((p.anlkt1_id is null)and(:Anlkt1_id is null))) and 
         ((p.anlkt2_id = :Anlkt2_id)or((p.anlkt2_id is null)and(:Anlkt2_id is null))) and 
         ((p.anlkt3_id = :Anlkt3_id)or((p.anlkt3_id is null)and(:Anlkt3_id is null))) and 
         ((p.anlkt4_id = :Anlkt4_id)or((p.anlkt4_id is null)and(:Anlkt4_id is null))) and 
         ((p.anlkt5_id = :Anlkt5_id)or((p.anlkt5_id is null)and(:Anlkt5_id is null)))
       )) then
      insert into permissibleaccounts(ID,ACCDB_ID,ANLDB1_ID,ANLDB2_ID,ANLDB3_ID,ANLDB4_ID,ANLDB5_ID,
        ACCKT_ID,ANLKT1_ID,ANLKT2_ID,ANLKT3_ID,ANLKT4_ID,ANLKT5_ID)
      values (gen_id(permissibleaccounts_id_gen, 1), :accdb, :anldb1_id, :anldb2_id, :anldb3_id, :anldb4_id, :anldb5_id,
        :acckt, :anlkt1_id, :anlkt2_id, :anlkt3_id, :anlkt4_id, :anlkt5_id);
  end
end
^

ALTER PROCEDURE P_CREATE_ROLLBACK_SEGMENT (
    ACTIVITY_ID INTEGER,
    RESOURCE_ID INTEGER,
    ITERATE SMALLINT,
    CREATE_TIME TIMESTAMP)
AS
declare variable comment varchar(256);
declare variable restore INTEGER;
begin
  select wfa.id, wfa.comment from wf_assignment wfa where (wfa.wf_activity_id = :activity_id) and (wfa.wf_resource_id = :resource_id)
  into :restore, :comment;
  if (restore is null) then
    restore = 0;
  else
    restore = 1;
  INSERT INTO WF_ROLLBACK_SEGMENT (ID,WF_RESOURCE_ID,WF_ACTIVITY_ID,ITERATE,COMMENT,CREATE_TIME,RESTORE_ASSIGNMENT)
  VALUES (gen_id(wf_rollback_segment_id_gen, 1),:RESOURCE_ID,:ACTIVITY_ID,:ITERATE,:COMMENT,:CREATE_TIME,:RESTORE);
end
^

ALTER PROCEDURE P_DELETE_LINKSTAGE (
    DOCTYPE_ID INTEGER)
AS
declare variable id integer;
begin
  for
    select id from docprocessstage where doctype_id = :doctype_id
    into :id
  do
    delete from linkstage
    where (prevstage_id = :id) or (nextstage_id = :id);
end
^

ALTER PROCEDURE P_EVALUATE_OUT_COST (
    REMNVAL_ID INTEGER,
    USER_ID INTEGER)
AS
declare variable eo_id integer;
declare variable es_id integer;
declare variable operdate  timestamp;
declare variable contr integer;
declare variable accbatch_id integer;
declare variable acc integer;
declare variable anl1 integer;
declare variable anl2 integer;
declare variable anl3 integer;
declare variable anl4 integer;
declare variable anl5 integer;
declare variable cat_id integer;
declare variable quan numeric(15,3);
declare variable COSTNAT NUMERIC(15, 3);
declare variable COSTCUR NUMERIC(15, 3);
declare variable SUMMANAT NUMERIC(15, 4);
declare variable SUMMACUR NUMERIC(15, 4);
declare variable ACCBATCHHIST_ID INTEGER;
declare variable anlform smallint;
begin
/**********************************************************
 Автор: Олег Сафонов, Константин Кривопустов
 Создано: давно
 Измененено: 28.12.2001
 Назначение: пересчет цен списания
 Используется: сервером приложения
**********************************************************/
  select a.anlform
  from remnval r, accplan a
  where (a.id = r.acc_id) and (r.id = :remnval_id)
  into :anlform;
  if (anlform in (4,6,7)) then exit;

  /* EconomicOper */
  for
    select eo.id, eo.keepdate, eo.from_id
    from economicoper eo, economicspec es
    where (eo.id = es.economicoper_id) and (es.remnvalkt_id = :remnval_id)
    order by eo.keepdate
    into :eo_id, :operdate, :contr
  do begin
    /* Lock */
    update economicoper set
      insertsign = 2,
      user_id = :user_id
    where id = :eo_id;
    /* EconomicSpec */
    for
      select es.id, es.accbatchkt_id, es.acckt_id, es.anlkt1_id, es.anlkt2_id, es.anlkt3_id, es.anlkt4_id, es.anlkt5_id,
        es.catalog_id, es.quantity
      from economicspec es
      where (es.economicoper_id = :eo_id) and (es.remnvalkt_id = :remnval_id)
      into :es_id, :accbatch_id, :acc, :anl1, :anl2, :anl3, :anl4, :anl5,
        :cat_id, :quan
    do begin
      /* Calculate out cost */
      execute procedure F_CALCULATE_OUT_COST(:operdate, :accbatch_id,
        :acc,  :anl1, :anl2, :anl3, :anl4, :anl5, :cat_id, :contr, :quan)
      returning_values :COSTNAT, :COSTCUR, :SUMMANAT, :SUMMACUR, :QUAN, :ACCBATCH_id, :ACCBATCHHIST_id;
      update economicspec set
        summanat = :summanat,
        summacur = :summacur,
        accbatchkt_id = :accbatch_id,
        accbatchhistorykt_id = :ACCBATCHHIST_id
      where id = :es_id;
    end
    /* Unlock */
    update economicoper set
      insertsign = 0,
      user_id = :user_id
    where id = :eo_id;
  end
end
^

ALTER PROCEDURE P_GET_DOCUMENTSPECLIST (
    SECTIONDOC SMALLINT,
    DOCUMENT_ID INTEGER,
    CATID INTEGER,
    ENTRYFOLD INTEGER,
    ENTRYCATTYPE SMALLINT,
    ENTRYTAXGROUP INTEGER)
RETURNS (
    ID INTEGER,
    CATALOG_ID INTEGER)
AS
declare variable IsEntry smallint;
declare variable cattype smallint;
declare variable Fold_Id integer;
declare variable taxgroup integer;
begin
  IsEntry = 1;
  for
    select ds.id, ds.catalog_id, c.folder_id, c.goodtype, ds.taxgroup_id from docspec ds, catalog c
    where (c.id = ds.catalog_id) and (ds.dochead_id = :document_id)
    order by ds.id
    into :id, :catalog_id, :fold_id, :cattype, :taxgroup
  do begin
    IsEntry = 1;
    if (entryfold is not null) then
      execute procedure f_catfold_is_branch_member (:fold_id, :entryfold)
      returning_values :IsEntry;
    if ((ENTRYCATTYPE <> -1 ) and (ENTRYCATTYPE <> cattype)) then IsEntry = 0;
    if (catid is not null) then
      if (catid <> catalog_id) then IsEntry = 0;
    if (entrytaxgroup is not null) then
      if ((entrytaxgroup <> taxgroup) or (taxgroup is null)) then IsEntry = 0;
    if (IsEntry = 1) then suspend;
  end
end
^

ALTER PROCEDURE P_LIST_DOCSPEC_FOR_DOCPROCESS (
    DOCACTION_ID INTEGER,
    DOCUMENT_ID INTEGER)
RETURNS (
    ID INTEGER,
    CATALOG_ID INTEGER,
    CODE CHAR(20),
    CNAME VARCHAR(80),
    PRICE NUMERIC(15,4),
    FREEQUAN NUMERIC(15,3),
    FREEQUAN2 NUMERIC(15,3),
    FREESUMMA NUMERIC(15,4),
    READYQUAN NUMERIC(15,3),
    READYQUAN2 NUMERIC(15,3),
    READYSUMMA NUMERIC(15,4),
    MEASURE_CONTROL SMALLINT,
    MEASURE1_CODE CHAR(5),
    MEASURE2_CODE CHAR(5),
    MEASURE1_ID INTEGER,
    MEASURE2_ID INTEGER)
AS
declare variable zero_docsum smallint;
begin
/**********************************************************
 Автор: Олег Сафонов, Константин Кривопустов
 Создано: давно
 Назначение: возвращает список спецификаций для частичной
   отработки документа.
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 15.02.2001 ?
 09.01.2003 Кривопустов Оптимизирован SELECT
 13.01.2003 Кривопустов Добавлен ORDER BY DS.ID
**********************************************************/
  for
    select ds.id, c.id, c.code, c.cname, ds.price, ds.quantity, ds.quantity2, ds.summa,
      c.measure_control, c.measure1_id, c.measure2_id, m1.code, m2.code
    from docspec ds left join catalog c on c.id = ds.catalog_id
      left join measure m1 on m1.id = c.measure1_id
      left join measure m2 on m2.id = c.measure2_id
    where (ds.dochead_id = :document_id)
    order by ds.id
    into :id, :catalog_id, :code, :cname, :price, :freequan, :freequan2, :freesumma,
      :measure_control, :measure1_id, :measure2_id, :measure1_code, :measure2_code
  do begin
    if (freesumma = 0.0) then zero_docsum = 1;
    else zero_docsum = 0;
    if (docaction_id <> 0) then
      select sum(dss.readysumma), sum(dss.readyquantity), sum(dss.readyquantity2)
      from docspecstate dss, docheadstate dhs
      where (dss.docheadstate_id = dhs.id)and
            (dhs.docaction_id = :docaction_id)and
            (dss.docspec_id = :id)
      into :readysumma, :readyquan, :readyquan2;
    else begin
      readysumma = 0; readyquan = 0; readyquan2 = 0;
    end
    if (readysumma is not null) then freesumma = freesumma - readysumma;
    if (readyquan is not null) then freequan = freequan - readyquan;
    if (readyquan2 is not null) then freequan2 = freequan2 - readyquan2;
    if ((Round4(freesumma) <> 0.0) or
        ((zero_docsum = 1) and (freequan <> 0.0)) or
        /* учетно - весовые, надо проверить и вторую ЕИ */
        ((zero_docsum = 1) and (measure_control = 2) and ((freequan <> 0.0) or (freequan2 <> 0.0)))) then
      suspend;
  end
end
^

ALTER PROCEDURE P_MERGE_DOCHEAD_ADD_EXPENSES (
    SRC_DOCSECTION SMALLINT,
    SRC_ID INTEGER,
    DST_DOCSECTION SMALLINT,
    DST_ID INTEGER)
AS
declare variable src_sum numeric (15,4);
begin
  if (not (dst_docsection in (16,21,23,25)) or not (src_docsection in (16,21,23,25))) then
    Exit;

  if (src_docsection = 16) then
    select d.add_expenses from stockdocumenthead d
    where d.dochead_id = :src_id into :src_sum;
  else if (src_docsection = 21) then
    select d.add_expenses from orderhead d
    where d.dochead_id = :src_id into :src_sum;
  else if (src_docsection = 23) then
    select d.add_expenses from invoicehead d
    where d.dochead_id = :src_id into :src_sum;
  else if (src_docsection = 25) then
    select d.add_expenses from billhead d
    where d.dochead_id = :src_id into :src_sum;

  if (dst_docsection = 16) then
    update stockdocumenthead d set
      d.add_expenses = d.add_expenses + :src_sum
    where d.dochead_id = :dst_id;
  else if (dst_docsection = 21) then
    update orderhead d set
      d.add_expenses = d.add_expenses + :src_sum
    where d.dochead_id = :dst_id;
  else if (dst_docsection = 23) then
    update invoicehead d set
      d.add_expenses = d.add_expenses + :src_sum
    where d.dochead_id = :dst_id;
  else if (dst_docsection = 25) then
    update billhead d set
      d.add_expenses = d.add_expenses + :src_sum
    where d.dochead_id = :dst_id;
end
^

ALTER PROCEDURE P_MERGE_DOCHEAD_TD (
    SRC_DOCSECTION SMALLINT,
    SRC_ID INTEGER,
    DST_DOCSECTION SMALLINT,
    DST_ID INTEGER,
    FIRST_CALL SMALLINT)
AS
declare variable src_from integer;
declare variable src_to integer;
declare variable src_curr_code char(5);
declare variable dst_from integer;
declare variable dst_to integer;
declare variable dst_curr_code char(5);
declare variable sumcur numeric(15,4);
declare variable sumnat numeric(15,4);
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 19.12.2000
 Измененено: 28.03.2001 (контрагенты)
 Назначение: используется при создании денежного документа
 на основании нескольких товарных документов
**********************************************************/
  select d.from_id, d.to_id, d.currency_code, d.summacur, d.summanat
  from dochead d
  where d.id = :src_id
  into :src_from, :src_to, :src_curr_code, :sumcur, :sumnat;

  select d.from_id, d.to_id, d.currency_code
  from dochead d
  where d.id = :dst_id
  into :dst_from, :dst_to, :dst_curr_code;

  if (first_call = 0/*не первый вызов*/) then begin
    /*проверяем, можно ли объединить документы*/
    if (((src_from <> dst_from)and(src_from is not null)and(dst_from is not null)) or
        ((src_to <> dst_to)and(src_to is not null)and(dst_to is not null)) or
        (src_curr_code <> dst_curr_code)) then
      exception E_DOCUMENTS_CANNOT_BE_MERGED;
  end

  update dochead d set
    d.from_id = :src_from,
    d.to_id = :src_to,
    d.currency_code = :src_curr_code,
    d.summacur = d.summacur + :sumcur,
    d.summanat = d.summanat + :sumnat
  where d.id = :dst_id;

  /* вставляем связь */
  insert into document_link(id, doc_id, basedoc_id)
  values (gen_id(DOCUMENT_LINK_ID_GEN, 1), :dst_id, :src_id);

end
^

ALTER PROCEDURE P_MERGE_DOCHEAD_TT (
    SRC_DOCSECTION SMALLINT,
    SRC_ID INTEGER,
    DST_DOCSECTION SMALLINT,
    DST_ID INTEGER,
    FIRST_CALL SMALLINT)
AS
declare variable src_from integer;
declare variable src_to integer;
declare variable src_curr_code char(5);
declare variable dst_from integer;
declare variable dst_to integer;
declare variable dst_curr_code char(5);
declare variable bankreq_from integer;
declare variable bankreq_to integer;
declare variable src_spec_ID integer;
declare variable src_CATALOG_ID integer;
declare variable src_PRICELISTSPEC_ID integer;
declare variable src_QUANTITY numeric(15,3);
declare variable src_quantity2 numeric(15,3);
declare variable src_PRICE numeric(15,4);
declare variable src_SUMMA numeric(15,4);
declare variable src_PRICE1 numeric(15,4);
declare variable src_SUMMA1 numeric(15,4);
declare variable src_WEIGHT numeric(15,3);
declare variable src_VOLUME numeric(15,3);
declare variable src_BESTBEFORE timestamp;
declare variable src_TAXGROUP_ID integer;
declare variable src_SHELFLIFE numeric(15,3);
declare variable src_SHELFLIFE_MEAS integer;
declare variable src_PRODUCTIONDATE timestamp;
declare variable src_measure1_id integer;
declare variable src_measure2_id integer;
declare variable dst_spec_id integer;
declare variable taxes_sum numeric(15,4);
declare variable calctaxeskind_id integer;
declare variable spec_sum numeric(15,4);
declare variable spec_price numeric(15,4);
declare variable sumcur numeric(15,4);
declare variable pricecur numeric(15,4);
declare variable quan numeric(15,3);
declare variable prec integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 19.12.2000
 Измененено: 16.02.01, 28.03.2001(контрагенты), 25.04.2002
 Назначение: используется при создании товарного документа
   на основании нескольких товарных документов
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 06.05.2003 Кривопустов Точность из настройки модуля "Склады"
 04.10.2004 Кривопустов Замена BANKREQUIS на REF_BANK_ACCOUNT
**********************************************************/
  select c.currency_prec from wh_config c into :prec;

  select d.from_id, d.to_id, d.currency_code
  from dochead d
  where d.id = :src_id
  into :src_from, :src_to, :src_curr_code;

  select d.from_id, d.to_id, d.currency_code, d.calctaxeskind_id
  from dochead d
  where d.id = :dst_id
  into :dst_from, :dst_to, :dst_curr_code, :calctaxeskind_id;

  if (first_call = 0/*не первый вызов*/) then begin
    /*проверяем, можно ли объединить документы*/
    if (((src_from <> dst_from)and(src_from is not null)and(dst_from is not null)) or
        ((src_to <> dst_to)and(src_to is not null)and(dst_to is not null)) or
        (src_curr_code <> dst_curr_code)) then
      exception E_DOCUMENTS_CANNOT_BE_MERGED;
  end
  else begin
    /* первый вызов */
    update dochead d set
      d.from_id = :src_from,
      d.to_id = :src_to,
      d.currency_code = :src_curr_code
    where d.id = :dst_id;

    if (dst_docsection in (12,13,23,24,25,26)) then begin
      /* проставляем банковские реквизиты */
      select b.id
      from partner p, ref_bank_account b
      where (p.contractor_id = :src_from) and (b.contractor_id = p.contractor_id) and (b.is_default = 1)
      into :bankreq_from;

      select b.id
      from partner p, ref_bank_account b
      where (p.contractor_id = :src_to) and (b.contractor_id = p.contractor_id) and (b.is_default = 1)
      into :bankreq_to;

      if (dst_docsection in (12,13)) then
        update facturahead f set
          f.partnerfrom_bankreq = :bankreq_from,
          f.partnerto_bankreq = :bankreq_to
        where f.dochead_id = :dst_id;

      if (dst_docsection in (23,24)) then
        update invoicehead i set
          i.partnerfrom_bankreq = :bankreq_from,
          i.partnerto_bankreq = :bankreq_to
        where i.dochead_id = :dst_id;

      if (dst_docsection in (25,26)) then
        update billhead b set
          b.partnerfrom_bankreq = :bankreq_from,
          b.partnerto_bankreq = :bankreq_to
        where b.dochead_id = :dst_id;
    end
  end

  /* накладные расходы */
  execute procedure p_merge_dochead_add_expenses(:src_docsection, :src_id,
    :dst_docsection, :dst_id);

  for
    select s.ID, s.CATALOG_ID, s.PRICELISTSPEC_ID, s.QUANTITY, s.quantity2, s.PRICE, s.SUMMA,
      s.PRICE1, s.SUMMA1, s.WEIGHT, s.VOLUME, s.BESTBEFORE,
      s.TAXGROUP_ID, s.SHELFLIFE, s.SHELFLIFE_MEAS, s.PRODUCTIONDATE,
      s.measure1_id, s.measure2_id
    from docspec s
    where s.dochead_id = :src_id
    into :src_spec_ID, :src_CATALOG_ID, :src_PRICELISTSPEC_ID, :src_QUANTITY, :src_quantity2, :src_PRICE, :src_SUMMA,
      :src_PRICE1, :src_SUMMA1, :src_WEIGHT, :src_VOLUME, :src_BESTBEFORE,
      :src_TAXGROUP_ID, :src_SHELFLIFE, :src_SHELFLIFE_MEAS, :src_PRODUCTIONDATE,
      :src_measure1_id, :src_measure2_id
  do begin
    dst_spec_id = null;
    for
      select s.id
      from docspec s
      where (s.dochead_id = :dst_id) and
        (s.catalog_id = :src_catalog_id) and
        (s.price = :src_price) and
        (s.price1 = :src_price1)
      into :dst_spec_id
    do dst_spec_id = dst_spec_id;

    if (dst_spec_id is null) then begin
      /*добавляем спецификацию*/
      dst_spec_id = gen_id(docspec_id_gen, 1);
      insert into docspec (id,dochead_id,catalog_id,pricelistspec_id,quantity,quantity2,price,summa,price1,summa1,weight,volume,bestbefore,taxgroup_id,shelflife,shelflife_meas,productiondate,measure1_id,measure2_id)
      values (:dst_spec_id,:dst_id,:src_catalog_id,:src_pricelistspec_id,:src_quantity,:src_quantity2,:src_price,:src_summa,:src_price1,:src_summa1,:src_weight,:src_volume,:src_bestbefore,:src_taxgroup_id,:src_shelflife,:src_shelflife_meas,:src_productiondate,:src_measure1_id,:src_measure2_id);

      pricecur = src_price1;
      sumcur = src_summa1;
      quan = src_quantity;

      execute procedure p_merge_docspec_add(:src_docsection, :src_spec_id,
        :dst_docsection, :dst_spec_id);
    end
    else begin
      /*изменяем спецификацию*/
      update docspec s set
        s.dochead_id = :dst_id,
        s.quantity = s.quantity + :src_quantity,
        s.summa1 = s.summa1 + :src_summa1,
        s.weight = s.weight + :src_weight,
        s.volume = s.volume + :src_volume
      where (s.id = :dst_spec_id);

      select s.price1, s.summa1, s.quantity
      from docspec s
      where (s.id = :dst_spec_id)
      into :pricecur, :sumcur, :quan;

      execute procedure p_merge_docspec_update(:src_docsection, :src_spec_id,
        :dst_docsection, :dst_spec_id);
    end
    /* добавляем/изменяем налоги */
    execute procedure P_Add_Taxes(:dst_docsection, :dst_spec_id, :src_taxgroup_id, :calctaxeskind_id);
    execute procedure p_update_taxes_summ(:dst_docsection, :dst_spec_id, :calctaxeskind_id, :pricecur, :sumcur, :quan, 0);
    execute procedure f_taxes_sum(1, 0, 0, :calctaxeskind_id, :dst_docsection, :dst_spec_id)
      returning_values :taxes_sum;
    spec_sum = Round(sumcur + taxes_sum, prec);
    if (quan = 0) then quan = 1; /* Konst-16.02.01 */
    spec_price = Round(pricecur + taxes_sum / quan, prec);
    update docspec s set
      s.summa = :spec_sum,
      s.price = :spec_price
    where s.id = :dst_spec_id;

  end  /* for/select */

  /* сумма получившегося документа */
  execute procedure p_calculate_docsum(:dst_docsection, :dst_id);
  /* вставляем связь */
  insert into document_link(id, doc_id, basedoc_id)
  values (gen_id(DOCUMENT_LINK_ID_GEN, 1), :dst_id, :src_id);

end
^

ALTER PROCEDURE P_MERGE_DOCSPEC_ADD (
    SRC_DOCSECTION SMALLINT,
    SRC_SPEC_ID INTEGER,
    DST_DOCSECTION SMALLINT,
    DST_SPEC_ID INTEGER)
AS
declare variable new_id integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 19.12.2000
 Измененено: 13.03.2001
 Назначение: используется при создании документа на
 основании нескольких
**********************************************************/
  if (src_docsection in (23,24)/*накладные*/) then begin

    if (dst_docsection in (25,26)/*счета*/) then begin
      new_id = gen_id(billspec_id_gen, 1);
      insert into billspec (id,docspec_id,discount,price_with_discount,summa_with_discount,cost)
      select :new_id, :dst_spec_id, s.discount, s.price_with_discount, s.summa_with_discount, s.cost
      from invoicespec s
      where s.docspec_id = :src_spec_id;
    end
    else if (dst_docsection in (16,17)/*складские ордера*/) then begin
      new_id = gen_id(stockdocumentspec_id_gen, 1);
      insert into stockdocumentspec (id,docspec_id,discount,price_with_discount,summa_with_discount,cost)
      select :new_id, :dst_spec_id, s.discount, s.price_with_discount, s.summa_with_discount, s.cost
      from invoicespec s
      where s.docspec_id = :src_spec_id;
    end
    else if (dst_docsection in (23,24)/*накладные*/) then begin
      new_id = gen_id(invoicespec_id_gen, 1);
      insert into invoicespec (id,docspec_id,discount,price_with_discount,summa_with_discount,cost)
      select :new_id, :dst_spec_id, s.discount, s.price_with_discount, s.summa_with_discount, s.cost
      from invoicespec s
      where s.docspec_id = :src_spec_id;
    end
    else if (not (dst_docsection in (12,13)/*счета-фактуры*/)) then
      exception E_MERGE_INCOMPATIBLE_DOCSECTION;

  end
  else if (src_docsection in (16,17)/*складские ордера*/) then begin

    if (dst_docsection in (25,26)/*счета*/) then begin
      new_id = gen_id(billspec_id_gen, 1);
      insert into billspec (id,docspec_id,discount,price_with_discount,summa_with_discount,cost)
      select :new_id, :dst_spec_id, s.discount, s.price_with_discount, s.summa_with_discount, s.cost
      from stockdocumentspec s
      where s.docspec_id = :src_spec_id;
    end
    else if (dst_docsection in (16,17)/*складские ордера*/) then begin
      new_id = gen_id(stockdocumentspec_id_gen, 1);
      insert into stockdocumentspec (id,docspec_id,discount,price_with_discount,summa_with_discount,cost)
      select :new_id, :dst_spec_id, s.discount, s.price_with_discount, s.summa_with_discount, s.cost
      from stockdocumentspec s
      where s.docspec_id = :src_spec_id;
    end
    else if (dst_docsection in (23,24)/*накладные*/) then begin
      new_id = gen_id(invoicespec_id_gen, 1);
      insert into invoicespec (id,docspec_id,discount,price_with_discount,summa_with_discount,cost)
      select :new_id, :dst_spec_id, s.discount, s.price_with_discount, s.summa_with_discount, s.cost
      from stockdocumentspec s
      where s.docspec_id = :src_spec_id;
    end
    else if (not (dst_docsection in (12,13)/*счета-фактуры*/)) then
      exception E_MERGE_INCOMPATIBLE_DOCSECTION;

  end
  else if (src_docsection in (25,26)/*счета*/) then begin

    if (dst_docsection in (25,26)/*счета*/) then begin
      new_id = gen_id(billspec_id_gen, 1);
      insert into billspec (id,docspec_id,discount,price_with_discount,summa_with_discount,cost,acceptancequan,acceptancesum,acceptancepackingquan)
      select :new_id, :dst_spec_id, s.discount, s.price_with_discount, s.summa_with_discount, s.cost, s.acceptancequan, s.acceptancesum, s.acceptancepackingquan
      from billspec s
      where s.docspec_id = :src_spec_id;
    end
    else if (dst_docsection in (16,17)/*складские ордера*/) then begin
      new_id = gen_id(stockdocumentspec_id_gen, 1);
      insert into stockdocumentspec (id,docspec_id,discount,price_with_discount,summa_with_discount,cost)
      select :new_id, :dst_spec_id, s.discount, s.price_with_discount, s.summa_with_discount, s.cost
      from billspec s
      where s.docspec_id = :src_spec_id;
    end
    else if (dst_docsection in (23,24)/*накладные*/) then begin
      new_id = gen_id(invoicespec_id_gen, 1);
      insert into invoicespec (id,docspec_id,discount,price_with_discount,summa_with_discount,cost)
      select :new_id, :dst_spec_id, s.discount, s.price_with_discount, s.summa_with_discount, s.cost
      from billspec s
      where s.docspec_id = :src_spec_id;
    end
    else if (not (dst_docsection in (12,13)/*счета-фактуры*/)) then
      exception E_MERGE_INCOMPATIBLE_DOCSECTION;

  end
  else
    exception E_MERGE_INCOMPATIBLE_DOCSECTION;
end
^

ALTER PROCEDURE P_MERGE_DOCSPEC_UPDATE (
    SRC_DOCSECTION SMALLINT,
    SRC_SPEC_ID INTEGER,
    DST_DOCSECTION SMALLINT,
    DST_SPEC_ID INTEGER)
AS
declare variable src_sum_with_discount numeric(15,4);
declare variable src_acceptancequan numeric(15,3);
declare variable src_acceptancesum numeric(15,4);
declare variable src_acceptancepackingquan numeric(15,3);
declare variable src_cost numeric(15,4);
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 19.12.2000
 Измененено: 13.03.2000
 Назначение: используется при создании документа на
 основании нескольких
**********************************************************/
  if (src_docsection in (23,24)/*накладные*/) then begin

    if (dst_docsection in (25,26)/*счета*/) then begin
      select s.summa_with_discount, s.cost
      from invoicespec s
      where s.docspec_id = :src_spec_id
      into :src_sum_with_discount, :src_cost;

      update billspec s set
        s.summa_with_discount = s.summa_with_discount + :src_sum_with_discount,
        s.cost = s.cost + :src_cost
      where s.docspec_id = :dst_spec_id;
    end
    else if (dst_docsection in (16,17)/*складские ордера*/) then begin
      select s.summa_with_discount, s.cost
      from invoicespec s
      where s.docspec_id = :src_spec_id
      into :src_sum_with_discount, :src_cost;

      update stockdocumentspec s set
        s.summa_with_discount = s.summa_with_discount + :src_sum_with_discount,
        s.cost = s.cost + :src_cost
      where s.docspec_id = :dst_spec_id;
    end
    else if (dst_docsection in (23,24)/*накладные*/) then begin
      select s.summa_with_discount, s.cost
      from invoicespec s
      where s.docspec_id = :src_spec_id
      into :src_sum_with_discount, :src_cost;

      update invoicespec s set
        s.summa_with_discount = s.summa_with_discount + :src_sum_with_discount,
        s.cost = s.cost + :src_cost
      where s.docspec_id = :dst_spec_id;
    end
    else if (not (dst_docsection in (12,13)/*счета-фактуры*/)) then
      exception E_MERGE_INCOMPATIBLE_DOCSECTION;

  end
  else if (src_docsection in (16,17)/*складские ордера*/) then begin

    if (dst_docsection in (25,26)/*счета*/) then begin
      select s.summa_with_discount, s.cost
      from stockdocumentspec s
      where s.docspec_id = :src_spec_id
      into :src_sum_with_discount, :src_cost;

      update billspec s set
        s.summa_with_discount = s.summa_with_discount + :src_sum_with_discount,
        s.cost = s.cost + :src_cost
      where s.docspec_id = :dst_spec_id;
    end
    else if (dst_docsection in (16,17)/*складские ордера*/) then begin
      select s.summa_with_discount, s.cost
      from stockdocumentspec s
      where s.docspec_id = :src_spec_id
      into :src_sum_with_discount, :src_cost;

      update stockdocumentspec s set
        s.summa_with_discount = s.summa_with_discount + :src_sum_with_discount,
        s.cost = s.cost + :src_cost
      where s.docspec_id = :dst_spec_id;
    end
    else if (dst_docsection in (23,24)/*накладные*/) then begin
      select s.summa_with_discount, s.cost
      from stockdocumentspec s
      where s.docspec_id = :src_spec_id
      into :src_sum_with_discount, :src_cost;

      update invoicespec s set
        s.summa_with_discount = s.summa_with_discount + :src_sum_with_discount,
        s.cost = s.cost + :src_cost
      where s.docspec_id = :dst_spec_id;
    end
    else if (not (dst_docsection in (12,13)/*счета-фактуры*/)) then
      exception E_MERGE_INCOMPATIBLE_DOCSECTION;

  end
  else if (src_docsection in (25,26)/*счета*/) then begin

    if (dst_docsection in (25,26)/*счета*/) then begin
      select s.summa_with_discount, s.acceptancequan, s.acceptancesum, s.acceptancepackingquan, s.cost
      from billspec s
      where s.docspec_id = :src_spec_id
      into :src_sum_with_discount, :src_acceptancequan, :src_acceptancesum, :src_acceptancepackingquan, :src_cost;

      update billspec s set
        s.summa_with_discount = s.summa_with_discount + :src_sum_with_discount,
        s.acceptancequan = s.acceptancequan + :src_acceptancequan,
        s.acceptancesum = s.acceptancesum + :src_acceptancesum,
        s.acceptancepackingquan = s.acceptancepackingquan + :src_acceptancepackingquan,
        s.cost = s.cost + :src_cost
      where s.docspec_id = :dst_spec_id;
    end
    else if (dst_docsection in (16,17)/*складские ордера*/) then begin
      select s.summa_with_discount, s.cost
      from billspec s
      where s.docspec_id = :src_spec_id
      into :src_sum_with_discount, :src_cost;

      update stockdocumentspec s set
        s.summa_with_discount = s.summa_with_discount + :src_sum_with_discount,
        s.cost = s.cost + :src_cost
      where s.docspec_id = :dst_spec_id;
    end
    else if (dst_docsection in (23,24)/*накладные*/) then begin
      select s.summa_with_discount, s.cost
      from billspec s
      where s.docspec_id = :src_spec_id
      into :src_sum_with_discount, :src_cost;

      update invoicespec s set
        s.summa_with_discount = s.summa_with_discount + :src_sum_with_discount,
        s.cost = s.cost + :src_cost
      where s.docspec_id = :dst_spec_id;
    end
    else if (not (dst_docsection in (12,13)/*счета-фактуры*/)) then
      exception E_MERGE_INCOMPATIBLE_DOCSECTION;

  end
  else
    exception E_MERGE_INCOMPATIBLE_DOCSECTION;
end
^

ALTER PROCEDURE P_OUT_GOOD_WITH_ACCBATCH (
    ACCBATCH_ID INTEGER,
    ACCBATCHHISTORY_ID INTEGER,
    ESQUAN NUMERIC(15,3))
AS
declare variable ans smallint;
begin
  ans = dm('...p_out_good_with_accbatch...');
  if (ACCBATCHHISTORY_ID is null) then exception E_Invalid_AccBatchHistory;
  update accbatchhistory
  set
    quantity = quantity + :esquan
  where id = :accbatchhistory_id;
  if (accbatch_id is null) then exception E_Invalid_AccBatch;
  update accbatch
  set
    endquan = endquan - :esquan
  where id = :accbatch_id;
end
^

ALTER PROCEDURE P_OVERESTIMATION_PRICELIST (
    PRICELIST_ID INTEGER,
    "PERCENT" NUMERIC(15,5),
    PRECESION INTEGER)
AS
declare variable pricespec_id integer;
begin
  for
    select ps.id from pricelistspec ps, pricelistfolder pf
    where (ps.folder_id = pf.id) and (pf.pricelisthead_id = :PRICELIST_ID)
    into :pricespec_id
  do begin
    /* update pricelistspec */
    update pricelistspec set
      price = Round((price + price * :"PERCENT" / 100.0), :precesion)
    where id = :pricespec_id;
    /* update pricelist_price */
    /* commented by Konst 05.09.00
    update pricelistspec_price set
      price = Round((price + price * :percent / 100.0), :precesion)
    where pricelistspec_id = :pricespec_id;*/
  end
end
^

ALTER PROCEDURE P_PERIODS_BETWEEN (
    ID1 INTEGER,
    ID2 INTEGER)
RETURNS (
    ID INTEGER)
AS
declare variable dat1  timestamp;
declare variable dat2  timestamp;
begin
select p.datefrom from period p where p.id = :id1
into :dat1;
select p.datefrom from period p where p.id = :id2
into :dat2;
for
select p.id
from period p
where p.datefrom between :dat1 and :dat2
into :id
do
suspend;
end
^

ALTER PROCEDURE P_RESTORE_TAXESSUM_FROM_BUFFER (
    SPEC_ID INTEGER)
AS
begin
  delete from taxsumm tb where (tb.specification_id = :spec_id);
  insert into taxsumm(specification_id, tax_id, summ)
    select t.specification_id, t.tax_id, t.summ
    from taxsum_buf t where (t.specification_id = :spec_id);
  delete from taxsum_buf tb where (tb.specification_id = :spec_id);
end
^

ALTER PROCEDURE P_ROLLBACK_DOCPROCESS (
    FULLROLLBACK SMALLINT,
    DOCACTION_ID INTEGER,
    DOCHEADSTATE_ID INTEGER)
AS
begin
  if (fullrollback = 1) then
    delete from docaction where id = :docaction_id;
  else begin
    delete from docheadstate where id = :docheadstate_id;
    update docaction set
      stagestate = 1 /* partition */
    where id = :docaction_id;
  end
end
^

ALTER PROCEDURE P_RPTBAND_CHANGE_PRIORITY (
    AID INTEGER,
    AINC SMALLINT)
AS
 declare variable RptId integer;
 declare variable ParentId integer;
 declare variable Priority integer;
 declare variable SiblingId integer;
 declare variable b_id integer;
 declare variable pr integer;
begin
  select rpt_id, parent_id, priority from rptbands
    where id = :AId
    into :RptId, :ParentId, :Priority;

  pr = 0;
  for
    select b.id
    from rptbands b
    where (b.rpt_id = :RptId) and
          ((b.parent_id = :ParentId) or ((b.parent_id is null) and (:ParentId is null)))
    order by b.priority
    into :b_id
  do begin
    update rptbands b set b.priority = :pr where b.id = :b_id;
    pr = pr + 1;
  end

  if (AInc = 1) then begin
    select id from rptbands
      where (rpt_id = :RptId) and 
        ((parent_id = :ParentId) or ((parent_id is null) and (:ParentId is null))) and 
        (priority = :Priority - 1)
      into :SiblingId;
    if (SiblingId is not null) then begin
      update rptbands set priority = priority + 1 where id = :SiblingId;
      update rptbands set priority = priority - 1 where id = :AId;
    end
  end
  else begin
    select id from rptbands
      where (rpt_id = :RptId) and 
        ((parent_id = :ParentId) or ((parent_id is null) and (:ParentId is null))) and 
        (priority = :Priority + 1)
      into :SiblingId;
    if (SiblingId is not null) then begin
      update rptbands set priority = priority - 1 where id = :SiblingId;
      update rptbands set priority = priority + 1 where id = :AId;
    end
  end
end
^

ALTER PROCEDURE P_SAVE_STOCKDOCUMENT_DISCOUNT (
    SPEC_ID INTEGER,
    DOC_DISCOUNT DOUBLE PRECISION,
    LINE_DISCOUNT DOUBLE PRECISION,
    CALCTAXESKIND INTEGER)
AS
declare variable taxes double precision;
declare variable price1 double precision;
declare variable summa1 double precision;
declare variable quantity double precision;
declare variable price_w_discount double precision;
declare variable summa_w_discount double precision;
declare variable price double precision;
declare variable summa double precision;
declare variable price_all_discount double precision;
declare variable summa_all_discount double precision;
begin
  select ds.price1, ds.summa1, ds.quantity
    from docspec ds
    where ds.id = :spec_id
    into :price1, :summa1, :quantity;
  
  price_w_discount = Round4(price1 + line_discount);
  summa_w_discount = Round4(price_w_discount * quantity);

  update stockdocumentspec s set
     s.price_with_discount = :price_w_discount,
     s.summa_with_discount = :summa_w_discount,
     s.discount = (:line_discount * 100.0) / :price1
    where s.docspec_id = :spec_id;
 
  price_all_discount = Round4(price_w_discount + (price1 * doc_discount / 100.0));
  summa_all_discount = Round4(price_all_discount * quantity);
  
  execute procedure p_update_taxes_summ(17, :spec_id, :calctaxeskind,
    :price_all_discount, :summa_all_discount, :quantity, 0);    

  execute procedure f_taxes_sum(1, null, 0, :calctaxeskind, 17, :spec_id)
    returning_values :taxes;
    
  summa = Round4(summa_w_discount + summa1 / 100.0 * doc_discount + taxes);
  if (quantity <> 0) then price = Round4(summa / quantity);
  else price = summa;
  
  update docspec s set
     s.summa = :summa,
     s.price = :price
    where s.id = :spec_id;
 
end
^

ALTER PROCEDURE P_SAVE_TAXESSUM_IN_BUFFER (
    SPEC_ID INTEGER)
AS
begin
  insert into taxsum_buf(specification_id, tax_id, summ)
  select t.specification_id, t.tax_id, t.summ
  from taxsumm t where (t.specification_id = :spec_id);
end
^

ALTER PROCEDURE P_SET_SOFTWARE_PRECISION (
    PREC INTEGER)
AS
declare variable a integer;
begin
  a = SetSoftwarePrecesion(:prec);
end
^

ALTER PROCEDURE P_START_WF_PROCESS (
    ID INTEGER,
    LAST_STATE_TIME TIMESTAMP)
AS
declare variable process_mgr_id integer;
declare variable amid integer;
declare variable amname varchar(80);
declare variable amkey char(20);
declare variable amprior smallint;
declare variable amdesc varchar(256);
begin
  /* create WfActivity */
  select process_mgr_id from wf_process where id = :id into :process_mgr_id;
  for
    select am.id, am.amname, am.amkey, am.priority, am.description from wf_activity_model am
    where am.wf_process_mgr_id = :process_mgr_id
    into :amid, :amname, :amkey, :amprior, :amdesc
  do begin
    /* state: open.not_running.not_started */
    insert into wf_activity (id, wf_process_id, model_id, workflow_state, while_open, why_not_running, aname, akey, priority, description, last_state_time)
    values (gen_id(wf_activity_id_gen, 1), :id, :amid, 0, 0, 0, :amname, :amkey, :amprior, :amdesc, :last_state_time);
  end
end
^

ALTER PROCEDURE P_STOCK_ADD_EXPENCES (
    DOCUMENT_ID INTEGER,
    KIND INTEGER)
RETURNS (
    DOCUMENT_SUMM NUMERIC(15,4),
    STOCK_ID INTEGER,
    MOL_ID INTEGER,
    KOEF DOUBLE PRECISION,
    STOCK_SUMM NUMERIC(18,4),
    ENDQUAN NUMERIC(18,4),
    PRICENAT NUMERIC(15,4),
    NEW_PRICENAT NUMERIC(15,4),
    BATCH_ID INTEGER)
AS
/*declare variable stock_summ numeric (18,4);*/
/*declare variable koef double precision;*/
/*declare variable batch_id integer;*/

begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 25.11.00
 Измененено:
 Назначение: Изменение цены партии товара на складе
 пропорционально стоимости несписанных партий на
 сумму документа.
 Может использоваться в настраиваемом этапе документооборота.
**********************************************************/
  /**/
    /*Параметры документа*/
    select d.stockdest, d.moldest, d.summanat
    from dochead d
    where d.id=:document_id
    into :stock_id, :mol_id, :document_summ;
    
    /*Сумма позиций, хранящихся на складе*/
    stock_summ = 0;
    for
    select sb.endquan, sb.pricenat
    from stockcard s
        left join stockbatch sb on sb.stockcard_id=s.id
    where (s.stock_id = :STOCK_ID) and
          (s.mol_id = :MOL_ID) and
          (sb.endquan <> 0)
    into :endquan, :pricenat
    do begin
      stock_summ = (endquan * pricenat) + stock_summ;
    end

    if (kind <> 1) then stock_summ = stock_summ - document_summ;
    if (stock_summ <> 0) then koef = :document_summ / :stock_summ;
    
    /*Нахождение новой цены*/
    /*kind = 1 - умножение цены на коэффициент*/
    /*kind = 2 - деление цены на коэффициент*/
  for
  select sb.id, sb.endquan, sb.pricenat
    from stockcard s
        left join stockbatch sb on sb.stockcard_id=s.id
    where (s.stock_id = :STOCK_ID) and
          (s.mol_id = :MOL_ID) and
          (sb.endquan <> 0)
    into :batch_id, :endquan, :pricenat
  do begin
    if (kind = 1) then
        new_pricenat= ((pricenat * endquan) * (1 + koef))/endquan;
    else
        new_pricenat= ((pricenat * endquan) / (1 + koef))/endquan;
        
    update stockbatch sb set sb.pricenat=:new_pricenat
    where sb.id=:batch_id;
    /*suspend;*/
  end
end
^

ALTER PROCEDURE P_STOCK_ADD_EXPENCES_CATALOG (
    DOCUMENT_ID INTEGER,
    SPEC_ID INTEGER,
    KIND INTEGER)
RETURNS (
    SPEC_SUMM NUMERIC(15,4),
    STOCK_ID INTEGER,
    MOL_ID INTEGER,
    CATALOG_ID INTEGER,
    KOEF DOUBLE PRECISION,
    STOCK_SUMM NUMERIC(18,4),
    ENDQUAN NUMERIC(18,4),
    PRICENAT NUMERIC(15,4),
    NEW_PRICENAT NUMERIC(15,4),
    BATCH_ID INTEGER)
AS
/*declare variable stock_summ numeric (18,4);*/
/*declare variable koef double precision;*/
/*declare variable batch_id integer;*/

begin
/**********************************************************
 Автор: Арычков Денис
 Создано: 25.11.00
 Измененено:
 Назначение: Изменение цены партии товара на складе
 пропорционально стоимости несписанных партий на сумму
 позиции спецификации.
 Может использоваться в настраиваемом этапе документооборота.
**********************************************************/
  /**/
    /*Параметры документа*/
    select d.stockdest, d.moldest, s.summa, s.catalog_id
    from dochead d
        left join docspec s on s.dochead_id=d.id
    where (d.id=:document_id) and
          (s.id=:spec_id)
    into :stock_id, :mol_id, :spec_summ, :catalog_id;

    /*Сумма позиций, хранящихся на складе*/
    stock_summ = 0;
    for
    select sb.id, sb.endquan, sb.pricenat
    from stockcard s
        left join stockbatch sb on sb.stockcard_id=s.id
    where (s.stock_id = :STOCK_ID) and
          (s.mol_id = :MOL_ID) and
          (s.catalog_id = :catalog_id) and
          (sb.endquan <> 0)
    into :batch_id, :endquan, :pricenat
    do begin
      stock_summ = (endquan * pricenat) + stock_summ;
    end

    if (kind <> 1) then stock_summ = stock_summ - spec_summ;
    if (stock_summ <> 0) then koef = :spec_summ / :stock_summ;

    /*Нахождение новой цены*/
    /*kind = 1 - умножение цены на коэффициент*/
    /*kind = 2 - деление цены на коэффициент*/
  for
  select sb.id, sb.endquan, sb.pricenat
    from stockcard s
        left join stockbatch sb on sb.stockcard_id=s.id
    where (s.stock_id = :STOCK_ID) and
          (s.mol_id = :MOL_ID) and
          (s.catalog_id = :catalog_id) and
          (sb.endquan <> 0)
    into :batch_id, :endquan, :pricenat
  do begin
    if (kind = 1) then
        new_pricenat= ((pricenat * endquan) * (1 + koef))/endquan;
    else
        new_pricenat= ((pricenat * endquan) / (1 + koef))/endquan;
        
    update stockbatch sb set sb.pricenat=:new_pricenat
    where sb.id=:batch_id;
    suspend;
  end
end
^

ALTER PROCEDURE P_UPDATE_TAXES_SUMM (
    SECTIONDOC SMALLINT,
    SPEC_ID INTEGER,
    CALCTAXESKIND_ID INTEGER,
    PRICE NUMERIC(15,4),
    SUMM NUMERIC(15,4),
    QUANTITY NUMERIC(15,3),
    TOTAL SMALLINT)
AS
declare variable tax_id integer;
declare variable taxtype smallint;
declare variable directrate numeric(15,5);
declare variable inverserate numeric(15,5);
declare variable totaltaxsum numeric(15,4);
declare variable totaltaxprice numeric(15,4);
declare variable taxsumm numeric(15,4);
declare variable taxprice numeric(15,4);
declare variable oldlayer smallint;
declare variable layer smallint;
declare variable layersumm numeric(15,4);
declare variable tmpsumm numeric(15,4);
declare variable layerprice numeric(15,4);
declare variable tmpprice numeric(15,4);
declare variable totallayerrate numeric(15,4);
declare variable ans integer;
declare variable subj smallint;
begin
/**********************************************************
 Автор: Олег Сафонов
 Создано: давно
 Назначение: пересчет налогов в спецификации документа
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 16.01.2003 Кривопустов Убраны вызовы Round4
**********************************************************/
  /* delete taxes */
  if (calctaxeskind_id = 0) then begin
    delete from taxsumm where (specification_id = :spec_id);
    Exit;
  end
  if (total = 0) then begin
    if ((quantity is null) or (quantity = 0)) then quantity = 1;
    oldlayer = -1; layersumm = 0; tmpsumm = summ; layerprice = 0; tmpprice = price;
    for
      select ts.tax_id, t.taxtype, t.directrate, t.inverserate, t.summ, ctl.feeorder, ctl.subject
      from taxsumm ts, tax t, calctaxeslink ctl 
      where ((ts.tax_id = t.id)and(ctl.included = 0)and(ts.specification_id = :spec_id)and
            (ctl.kind_id = :calctaxeskind_id)and(ctl.tax_id = t.id))
      order by ctl.feeorder
      into :tax_id, :taxtype, :directrate, :inverserate, :taxsumm, :layer, :subj
    do begin
      if (oldlayer = -1) then oldlayer = layer; /* first enter */
      /* if goto on next layer then change summ */
      if (layer > oldlayer) then begin
        tmpsumm = tmpsumm + layersumm;
        tmpprice = tmpprice + layerprice;
      end
      /*ans=WF('TmpPrice',15,5,tmpprice);
      ans=WF('TmpSum',15,5,tmpsumm);*/
      /* if percent */
      if (taxtype = 0) then begin
        taxprice = tmpprice * directrate / 100.0;
        taxsumm = tmpsumm * directrate / 100.0;
      end
      else taxprice = taxsumm / quantity;
      /*ans=WF('TaxPrice',15,5,taxprice);
      ans=WF('TaxSum',15,5,taxsumm);*/
      if (oldlayer = layer) then begin
        layerprice = layerprice + taxprice;
        layersumm = layersumm + taxsumm;
      end
      else begin 
        layerprice = taxprice; layersumm = taxsumm; 
        oldlayer = layer; 
      end
      if (subj = 1) then begin
        totaltaxsum = taxprice * quantity; totaltaxprice = taxprice;
      end
      else begin
        totaltaxsum = taxsumm; totaltaxprice = totaltaxsum / quantity;
      end
      /* update */
      update taxsumm set
        summ = :totaltaxsum,
        price = :totaltaxprice
      where (specification_id = :spec_id)and(tax_id = :tax_id);
    end /* for do */
    /* calculate included taxes */
    oldlayer = -1; layersumm = 0; tmpsumm = summ; layerprice = 0; tmpprice = price;
    for
      select ts.tax_id, t.taxtype, t.directrate, t.inverserate, t.summ, ctl.feeorder, ctl.subject
      from taxsumm ts, tax t, calctaxeslink ctl 
      where ((ts.tax_id = t.id)and(ctl.included = 1)and(ts.specification_id = :spec_id)and
            (ctl.kind_id = :calctaxeskind_id)and(ctl.tax_id = t.id))
      order by ctl.feeorder desc
      into :tax_id, :taxtype, :directrate, :inverserate, :taxsumm, :layer, :subj
    do begin
      select sum(t.directrate) from tax t, calctaxeslink ctl, taxsumm ts 
      where (ts.specification_id = :spec_id) and (ts.tax_id = t.id) and
            (t.id = ctl.tax_id) and (ctl.kind_id = :calctaxeskind_id) and (ctl.feeorder = :layer) and (ctl.included = 1)
      into :totallayerrate;
      if (oldlayer = -1) then oldlayer = layer; /* first enter */
      /* if goto on next leyer then change summ */
      if (layer < oldlayer) then begin
        tmpprice = tmpprice - layerprice;
        tmpsumm = tmpsumm - layersumm;
      end
      /* if percent */
      if (taxtype = 0) then begin
        taxprice = tmpprice - tmpprice / (100.0 / totallayerrate + 1.0);
        taxprice = taxprice * directrate / 100.0;
        taxsumm = tmpsumm - tmpsumm / (100.0 / totallayerrate + 1.0);
        taxsumm = taxsumm * directrate / 100.0;
      end
      /*else taxprice = Round4(taxsumm / quantity); */
      /*ans=WF('TaxPriceInc',15,5,taxprice);
      ans=WF('TaxSumInc',15,5,taxsumm);*/
      if (oldlayer = layer) then begin
        layerprice = layerprice + taxprice;
        layersumm = layersumm + taxsumm;
      end
      else begin 
        layerprice = taxprice; layersumm = taxsumm; 
        oldlayer = layer; 
      end
      if (subj = 1) then begin
        totaltaxsum = taxprice * quantity; totaltaxprice = taxprice;
      end
      else begin
        totaltaxsum = taxsumm; totaltaxprice = totaltaxsum / quantity;
      end
      /* update */
      update taxsumm set
        summ = :totaltaxsum,
        price = :totaltaxprice
      where (specification_id = :spec_id)and(tax_id = :tax_id);
    end
  end
  else begin
    oldlayer = -1; layersumm = 0; tmpsumm = summ; layerprice = 0; tmpprice = price;
    for
      select ts.tax_id, t.taxtype, t.directrate, t.inverserate, t.summ, ctl.feeorder, ctl.subject
      from taxsumm ts, tax t, calctaxeslink ctl 
      where ((ts.tax_id = t.id)and(ctl.included = 0)and(ts.specification_id = :spec_id)and
            (ctl.kind_id = :calctaxeskind_id)and(ctl.tax_id = t.id))
      order by ctl.feeorder desc
      into :tax_id, :taxtype, :directrate, :inverserate, :taxsumm, :layer, :subj
    do begin
      select sum(t.directrate) from tax t, calctaxeslink ctl, taxsumm ts 
      where (ts.specification_id = :spec_id) and (ts.tax_id = t.id) and
            (t.id = ctl.tax_id) and (ctl.kind_id = :calctaxeskind_id) and (ctl.feeorder = :layer) and (ctl.included = 0)
      into :totallayerrate;
      if (oldlayer = -1) then oldlayer = layer; /* first enter */
      /* if goto on next layer then change summ */
      if (layer < oldlayer) then begin
        tmpprice = tmpprice - layerprice;
        tmpsumm = tmpsumm - layersumm;
      end
      /* if percent */
      if (taxtype = 0) then begin
        taxprice = tmpprice - tmpprice / (100.0 / totallayerrate + 1);
        taxprice = taxprice * directrate / 100.0;
        taxsumm = tmpsumm - tmpsumm / (100.0 / totallayerrate + 1);
        taxsumm = taxsumm * directrate / 100.0;
      end
      else taxprice = taxsumm / quantity;
      if (oldlayer = layer) then begin
        layerprice = layerprice + taxprice;
        layersumm = layersumm + taxsumm;
      end
      else begin 
        layerprice = taxprice; layersumm = taxsumm; 
        oldlayer = layer; 
      end
      if (subj = 1) then begin
        totaltaxsum = taxprice * quantity; totaltaxprice = taxprice;
      end
      else begin
        totaltaxsum = taxsumm; totaltaxprice = totaltaxsum / quantity;
      end
      /* update */
      update taxsumm set
        summ = :totaltaxsum,
        price = :totaltaxprice
      where (specification_id = :spec_id)and(tax_id = :tax_id);
    end
  end
end
^

ALTER PROCEDURE P_UPDATE_TAXES_SUMM_PREC (
    SPEC_ID INTEGER,
    CALCTAXESKIND_ID INTEGER,
    PRICE NUMERIC(15,4),
    SUMM NUMERIC(15,4),
    QUANTITY NUMERIC(15,3),
    TOTAL SMALLINT,
    PREC SMALLINT)
AS
DECLARE VARIABLE TAX_ID INTEGER;
DECLARE VARIABLE TAXTYPE SMALLINT;
DECLARE VARIABLE DIRECTRATE DOUBLE PRECISION;
DECLARE VARIABLE INVERSERATE DOUBLE PRECISION;
DECLARE VARIABLE TOTALTAXSUM DOUBLE PRECISION;
DECLARE VARIABLE TOTALTAXPRICE DOUBLE PRECISION;
DECLARE VARIABLE TAXSUMM DOUBLE PRECISION;
DECLARE VARIABLE TAXPRICE DOUBLE PRECISION;
DECLARE VARIABLE OLDLAYER SMALLINT;
DECLARE VARIABLE LAYER SMALLINT;
DECLARE VARIABLE LAYERSUMM DOUBLE PRECISION;
DECLARE VARIABLE TMPSUMM DOUBLE PRECISION;
DECLARE VARIABLE LAYERPRICE DOUBLE PRECISION;
DECLARE VARIABLE TMPPRICE DOUBLE PRECISION;
DECLARE VARIABLE TOTALLAYERRATE DOUBLE PRECISION;
DECLARE VARIABLE ANS INTEGER;
DECLARE VARIABLE SUBJ SMALLINT;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 06.02.2003
 Назначение: аналог P_UPDATE_TAXES_SUMM:
             пересчет налогов в спецификации документа с
             округлением до PREC знаков после запятой.
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 08.12.2003 Кривопустов Bug#2090, корректный расчет включенных
                        налогов при пересчете от итоговой суммы
 19.02.2004 Кривопустов Bug#2151, увеличение точности
                        промежуточных итогов
 26.03.2004 Кривопустов Bug#2190. Промежуточные итоги переведены
                        в double precision
 22.06.2006 Arychkov    Defects ##3342, 3362 fixed
**********************************************************/
  /* delete taxes */
  if (calctaxeskind_id = 0) then begin
    delete from taxsumm where (specification_id = :spec_id);
    Exit;
  end
  if (total = 0) then begin
    if ((quantity is null) or (quantity = 0)) then quantity = 1;
    oldlayer = -1; layersumm = 0; tmpsumm = summ; layerprice = 0; tmpprice = price;
    for
      select ts.tax_id, t.taxtype, t.directrate, t.inverserate, t.summ, ctl.feeorder, ctl.subject
      from taxsumm ts, tax t, calctaxeslink ctl 
      where ((ts.tax_id = t.id)and(ctl.included = 0)and(ts.specification_id = :spec_id)and
            (ctl.kind_id = :calctaxeskind_id)and(ctl.tax_id = t.id))
      order by ctl.feeorder
      into :tax_id, :taxtype, :directrate, :inverserate, :taxsumm, :layer, :subj
    do begin
      if (oldlayer = -1) then oldlayer = layer; /* first enter */
      /* if goto on next layer then change summ */
      if (layer > oldlayer) then begin
        tmpsumm = tmpsumm + layersumm;
        tmpprice = tmpprice + layerprice;
      end
      /*ans=WF('TmpPrice',15,5,tmpprice);
      ans=WF('TmpSum',15,5,tmpsumm);*/
      /* if percent */
      if (taxtype = 0) then begin
        taxprice = tmpprice * directrate / 100.0;
        taxsumm = tmpsumm * directrate / 100.0;
        if (subj = 1) then begin
          totaltaxsum = Round(taxprice, prec) * quantity;
          totaltaxprice = Round(taxprice, prec);
        end
        else begin
          totaltaxsum = Round(taxsumm, prec);
          totaltaxprice = Round(totaltaxsum, prec) / quantity;
        end
      end
      else taxprice = taxsumm / quantity;
      /*ans=WF('TaxPrice',15,5,taxprice);
      ans=WF('TaxSum',15,5,taxsumm);*/
      if (oldlayer = layer) then begin
        layerprice = layerprice + taxprice;
        layersumm = layersumm + taxsumm;
      end
      else begin 
        layerprice = taxprice; layersumm = taxsumm; 
        oldlayer = layer; 
      end
      /* update */
      update taxsumm set
        summ = Round(:totaltaxsum, :prec),
        price = Round(:totaltaxprice, :prec)
      where (specification_id = :spec_id)and(tax_id = :tax_id);
    end /* for do */
  end
  else begin
    oldlayer = -1; layersumm = 0; tmpsumm = summ; layerprice = 0; tmpprice = price;
    for
      select ts.tax_id, t.taxtype, t.directrate, t.inverserate, t.summ, ctl.feeorder, ctl.subject
      from taxsumm ts, tax t, calctaxeslink ctl 
      where ((ts.tax_id = t.id)and(ctl.included = 0)and(ts.specification_id = :spec_id)and
            (ctl.kind_id = :calctaxeskind_id)and(ctl.tax_id = t.id))
      order by ctl.feeorder desc
      into :tax_id, :taxtype, :directrate, :inverserate, :taxsumm, :layer, :subj
    do begin
      select sum(t.directrate) from tax t, calctaxeslink ctl, taxsumm ts 
      where (ts.specification_id = :spec_id) and (ts.tax_id = t.id) and
            (t.id = ctl.tax_id) and (ctl.kind_id = :calctaxeskind_id) and (ctl.feeorder = :layer) and (ctl.included = 0)
      into :totallayerrate;
      if (oldlayer = -1) then oldlayer = layer; /* first enter */
      /* if goto on next layer then change summ */
      if (layer < oldlayer) then begin
        tmpprice = tmpprice - layerprice;
        tmpsumm = tmpsumm - layersumm;
      end
      /* if percent */
      if (taxtype = 0) then begin
        taxprice = tmpprice - tmpprice / (100.0 / totallayerrate + 1);
        taxprice = taxprice * directrate / 100.0;
        taxsumm = tmpsumm - tmpsumm / (100.0 / totallayerrate + 1);
        taxsumm = taxsumm * directrate / 100.0;
        if (subj = 1) then begin
          totaltaxsum = taxprice * quantity;
          totaltaxprice = taxprice;
        end
        else begin
          totaltaxsum = taxsumm;
          totaltaxprice = totaltaxsum / quantity;
        end
      end
      else taxprice = taxsumm / quantity;
      if (oldlayer = layer) then begin
        layerprice = layerprice + taxprice;
        layersumm = layersumm + taxsumm;
      end
      else begin 
        layerprice = taxprice; layersumm = taxsumm; 
        oldlayer = layer; 
      end
      /* update */
      update taxsumm set
        summ = Round(:totaltaxsum, :prec),
        price = Round(:totaltaxprice, :prec)
      where (specification_id = :spec_id)and(tax_id = :tax_id);
    end
  end
  /* calculate included taxes */
  oldlayer = -1; layersumm = 0; tmpsumm = summ; layerprice = 0; tmpprice = price;
  for
    select ts.tax_id, t.taxtype, t.directrate, t.inverserate, t.summ, ctl.feeorder, ctl.subject
    from taxsumm ts, tax t, calctaxeslink ctl 
    where ((ts.tax_id = t.id)and(ctl.included = 1)and(ts.specification_id = :spec_id)and
          (ctl.kind_id = :calctaxeskind_id)and(ctl.tax_id = t.id))
    order by ctl.feeorder desc
    into :tax_id, :taxtype, :directrate, :inverserate, :taxsumm, :layer, :subj
  do begin
    select sum(t.directrate) from tax t, calctaxeslink ctl, taxsumm ts 
    where (ts.specification_id = :spec_id) and (ts.tax_id = t.id) and
          (t.id = ctl.tax_id) and (ctl.kind_id = :calctaxeskind_id) and (ctl.feeorder = :layer) and (ctl.included = 1)
    into :totallayerrate;
    if (oldlayer = -1) then oldlayer = layer; /* first enter */
    /* if goto on next leyer then change summ */
    if (layer < oldlayer) then begin
      tmpprice = tmpprice - layerprice;
      tmpsumm = tmpsumm - layersumm;
    end
    /* if percent */
    if (taxtype = 0) then begin
      taxprice = tmpprice - tmpprice / (100.0 / totallayerrate + 1.0);
      taxprice = taxprice * directrate / 100.0;
      taxsumm = tmpsumm - tmpsumm / (100.0 / totallayerrate + 1.0);
      taxsumm = taxsumm * directrate / 100.0;
    end
    /*else taxprice = Round4(taxsumm / quantity); */
    /*ans=WF('TaxPriceInc',15,5,taxprice);
    ans=WF('TaxSumInc',15,5,taxsumm);*/
    if (oldlayer = layer) then begin
      layerprice = layerprice + taxprice;
      layersumm = layersumm + taxsumm;
    end
    else begin 
      layerprice = taxprice; layersumm = taxsumm; 
      oldlayer = layer; 
    end
    if (subj = 1) then begin
      totaltaxsum = taxprice * quantity; totaltaxprice = taxprice;
    end
    else begin
      totaltaxsum = taxsumm; totaltaxprice = totaltaxsum / quantity;
    end
    /* update */
    update taxsumm set
      summ = Round(:totaltaxsum, :prec),
      price = Round(:totaltaxprice, :prec)
    where (specification_id = :spec_id)and(tax_id = :tax_id);
  end
end
^

ALTER PROCEDURE PREF_SET_BASIC_IDENTDOC (
    PERSONNEL_ID INTEGER,
    DOCUMENT_ID INTEGER)
AS
begin
  update pref_identdoc pi
  set pi.is_basic = 0
  where pi.personnel_id = :personnel_id;

  update pref_identdoc pi
  set pi.is_basic = 1
  where pi.id = :document_id;

  suspend;
end
^

ALTER PROCEDURE REF_COPY_FEATURELINK (
    SRC_CLASS_ID INTEGER,
    DST_CLASS_ID INTEGER,
    SRC_REC_ID INTEGER,
    DST_REC_ID INTEGER)
AS
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: давно
 Назначение: переносит доп.признаки между БК с учетом
   их входимости
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 25.12.2003 Кривопустов Исправлена ошибка, приводящая к
                        переносу лишних доп.признаков, не
                        принадлежащих БК-приемнику
**********************************************************/
  insert into featurelink (id, class_id, rec_id, feature_id,
    val, val_folder, val_text)
  select gen_id(featurelink_id_gen,1), :dst_class_id, :dst_rec_id, fl1.feature_id,
    fl1.val, fl1.val_folder, fl1.val_text
  from featurelink fl1
  where (fl1.rec_id = :src_rec_id) and (fl1.class_id = :src_class_id) and
    (exists (
      select * from featurelink fl2
      where (fl2.rec_id is null) and (fl2.class_id = :dst_class_id) and (fl2.feature_id = fl1.feature_id)
    ));
end
^

ALTER PROCEDURE REF_COPY_FEATURELINK_SAME_CLASS (
    CLASS_ID INTEGER,
    SRC_REC_ID INTEGER,
    DST_REC_ID INTEGER)
AS
begin
  insert into featurelink (id, class_id, rec_id, feature_id, val, val_folder, val_text)
  select gen_id(featurelink_id_gen,1), :class_id, :dst_rec_id, feature_id, val, val_folder, val_text
  from featurelink
  where (rec_id = :src_rec_id) and (class_id = :class_id);
end
^

ALTER PROCEDURE REF_F_GET_MEASURE_CONV_ALG (
    MEASURE1 INTEGER,
    MEASURE2 INTEGER,
    CATALOG_ID INTEGER)
RETURNS (
    ALGORITHM_ID INTEGER)
AS
begin
  if (catalog_id is not null) then begin
    select ar.id from ref_measure_conv mc
    left join alg_repository ar on ar.id = mc.conv_algorithm_id
    where (mc.measure_from_id = :measure1) and (mc.measure_to_id = :measure2) and (mc.catalog_id = :catalog_id)
    into :algorithm_id;
    if (algorithm_id is not null) then begin
      suspend;
      exit;
    end
  end
  select ar.id from ref_measure_conv mc
  left join alg_repository ar on ar.id = mc.conv_algorithm_id
  where (mc.measure_from_id = :measure1) and (mc.measure_to_id = :measure2) and (mc.catalog_id is null)
  into :algorithm_id;
  suspend;
  exit;
end
^

ALTER PROCEDURE REF_GET_CATFOLDER_CODE_PATH (
    FOLDER_ID INTEGER,
    ROOT_ID INTEGER)
RETURNS (
    CODE VARCHAR(20),
    ID INTEGER)
AS
declare variable is_member smallint;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 05.03.2003

 Назначение: выдает список папок каталога - путь к указанной папке
   FOLDER_ID - указываемая папка
   ROOT_ID - папка, начиная с которой выдавать путь

 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------

**********************************************************/
  for
    select f.id, f.groupcode from catalogfolder f
    where (f.id >= :root_id)
    order by f.id
    into :id, :code
  do begin
    execute procedure f_catfold_is_branch_member(:folder_id, :id)
      returning_values :is_member;
    if (is_member = 1) then
      suspend;
  end
end
^

ALTER PROCEDURE REF_GET_FOLDER_PATH (
    KIND SMALLINT,
    FOLDER_TYPE INTEGER,
    FOLDER_ID INTEGER,
    ROOT_ID INTEGER)
RETURNS (
    NAME VARCHAR(80))
AS
declare variable f_id integer;
declare variable is_member smallint;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 23.05.2002
 Измененено:

 Назначение: выдает список папок - путь к указанной папке
   KIND - таблица, содержащая папки:
          0 - folder
          1 - catalogfolder
          2 - contractor
   FOLDER_TYPE - тип папок (для таблицы folder)
   FOLDER_ID - указываемая папка
   ROOT_ID - папка, начиная с которой выдавать путь

 Используется: процедурами OLAP, возможно отчетами
**********************************************************/
  if (kind = 0) then begin
    for
      select f.id, f.fname from folder f
      where (f.id >= :root_id)
      order by f.id
      into :f_id, :name
    do begin
      execute procedure f_fold_is_branch_member(:folder_id, :f_id, :folder_type)
        returning_values :is_member;
      if (is_member = 1) then
        suspend;
    end
  end
  else if (kind = 1) then begin
    for
      select f.id, f.fname from catalogfolder f
      where (f.id >= :root_id)
      order by f.id
      into :f_id, :name
    do begin
      execute procedure f_catfold_is_branch_member(:folder_id, :f_id)
        returning_values :is_member;
      if (is_member = 1) then
        suspend;
    end
  end
  else if (kind = 2) then begin
    for
      select f.id, StrCopy(f.cname,1,80) from contractor f
      where (f.id >= :root_id)
      order by f.id
      into :f_id, :name
    do begin
      execute procedure f_orgunit_is_branch_member(:folder_id, :f_id)
        returning_values :is_member;
      if (is_member = 1) then
        suspend;
    end
  end
end
^

ALTER PROCEDURE REF_GET_NESTED_FOLDERS (
    KIND SMALLINT,
    HEAD_ID INTEGER,
    FOLDER_TYPE INTEGER)
RETURNS (
    FOLDER_ID INTEGER)
AS
declare variable is_member smallint;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 23.05.2002
 Измененено:

 Назначение: выдает список ID папок, являющихся вложенными
 в указанную папку
   KIND - таблица, содержащая папки:
          0 - folder
          1 - catalogfolder
          2 - contractor
   FOLDER_TYPE - тип папок (для таблицы folder)
   HEAD_ID - указываемая папка

 Используется: процедурами OLAP, возможно отчетами
**********************************************************/
  if (kind = 0) then begin
    for select f.id from folder f
      where f.foldertype = :folder_type
      order by f.id
      into :folder_id
    do begin
      execute procedure f_fold_is_branch_member(:folder_id, :head_id, :folder_type)
        returning_values :is_member;
      if (is_member = 1) then suspend;
    end
  end
  else if (kind = 1) then begin
    for select f.id from catalogfolder f
      order by f.id
      into :folder_id
    do begin
      execute procedure f_catfold_is_branch_member(:folder_id, :head_id)
        returning_values :is_member;
      if (is_member = 1) then suspend;
    end
  end
  else if (kind = 2) then begin
    for 
      select o.id 
      from contractor o 
      where o.kind = 1
      order by o.id
      into :folder_id
    do begin
      execute procedure f_orgunit_is_branch_member(:folder_id, :head_id)
        returning_values :is_member;
      if (is_member = 1) then suspend;
    end
  end
end
^

ALTER PROCEDURE SEC_CREATE_METHOD (
    BEAN_NAME VARCHAR(128),
    CORBA_NAME VARCHAR(80),
    DESCRIPTION VARCHAR(80))
AS
declare variable method_id integer;
declare variable class_id integer;
begin
  select c.id from sys_class c where c.bean_name=:bean_name
  into :class_id;
  method_id = gen_id(sys_method_id_gen, 1);
  insert into sys_method (id, class_id, corba_name, description)
  values (:method_id, :class_id, :corba_name, :description);
  insert into sec_method_access (id, group_id, method_id, permission)
  values (gen_id(sec_method_access_id_gen, 1), 1, :method_id, 1);
end
^

ALTER PROCEDURE SEC_CREATE_METHOD_CUR_CLASS (
    CORBA_NAME VARCHAR(80),
    DESCRIPTION VARCHAR(80))
AS
declare variable method_id integer;
declare variable curclass integer;
begin
  curclass = gen_id(sys_class_id_gen, 0);
  method_id = gen_id(sys_method_id_gen, 1);
  insert into sys_method (id, class_id, corba_name, description)
  values (:method_id, :curclass, :corba_name, :description);
  insert into sec_method_access (id, group_id, method_id, permission)
  values (gen_id(sec_method_access_id_gen, 1), 1, :method_id, 1);
end
^

ALTER PROCEDURE SEC_CREATE_METHOD_FOR_ALL (
    BEAN_NAME VARCHAR(128),
    CORBA_NAME VARCHAR(80),
    DESCRIPTION VARCHAR(80))
AS
DECLARE VARIABLE METHOD_ID INTEGER;
DECLARE VARIABLE CLASS_ID INTEGER;
DECLARE VARIABLE G_ID INTEGER;
begin
  select c.id from sys_class c where c.bean_name=:bean_name
  into :class_id;
  
  if (not exists(select * from sys_method
                 where class_id = :class_id and corba_name = :corba_name)) then begin
      method_id = gen_id(sys_method_id_gen, 1);
      insert into sys_method (id, class_id, corba_name, description)
      values (:method_id, :class_id, :corba_name, :description);
      
      for 
        select g.id
        from sec_groups g
        into :g_id
      do begin
        insert into sec_method_access (id, group_id, method_id, permission)
        values (gen_id(sec_method_access_id_gen, 1), :g_id, :method_id, 1);
      end
  end
end
^

ALTER PROCEDURE SEC_CREATE_METHODS_CUR_CLASS
AS
declare variable curclass integer;
begin
  curclass = gen_id(sys_class_id_gen, 0);
  execute procedure sec_p_create_bean_method(:curclass, 'mebLoad', 'Просмотр');
  execute procedure sec_p_create_bean_method(:curclass, 'mebLoadBrowse', 'Просмотр списка');
  execute procedure sec_p_create_bean_method(:curclass, 'mebCreate', 'Создание');
  execute procedure sec_p_create_bean_method(:curclass, 'mebRemove', 'Удаление');
  execute procedure sec_p_create_bean_method(:curclass, 'mebStore', 'Изменение');
  execute procedure sec_p_create_bean_method(:curclass, 'mebChangeParent', 'Изменение папки');
  execute procedure sec_p_create_bean_method(:curclass, 'mebHierarchy', 'Изменение иерархии');
end
^

ALTER PROCEDURE SEC_P_CREATE_ADMIN_PERMISS
AS
declare variable class_id integer;
begin
  for
    select id from sys_class
    where (description is not null)
    into :class_id
  do begin
    execute procedure sec_p_create_bean_method :class_id, 'mebLoad', 'Просмотр';
    execute procedure sec_p_create_bean_method :class_id, 'mebLoadBrowse', 'Просмотр списка';
    execute procedure sec_p_create_bean_method :class_id, 'mebCreate', 'Создание';
    execute procedure sec_p_create_bean_method :class_id, 'mebRemove', 'Удаление';
    execute procedure sec_p_create_bean_method :class_id, 'mebStore', 'Изменение';
    execute procedure sec_p_create_bean_method :class_id, 'mebChangeParent', 'Изменение папки';
    execute procedure sec_p_create_bean_method :class_id, 'mebHierarchy', 'Изменение иерархии';
  end
end
^

ALTER PROCEDURE SEC_P_CREATE_BEAN_METHOD (
    CLASS_ID INTEGER,
    CORBA_NAME VARCHAR(80),
    DESCRIPTION VARCHAR(80))
AS
declare variable method_id integer;
begin
  method_id = gen_id(sys_method_id_gen, 1);
  insert into sys_method (id, class_id, corba_name, description)
  values (:method_id, :class_id, :corba_name, :description);
  insert into sec_method_access (id, group_id, method_id, permission)
  values (gen_id(sec_method_access_id_gen, 1), 1, :method_id, 1);
end
^

ALTER PROCEDURE SEC_P_CREATE_SYSPART_METHODS (
    SYSPART_ID INTEGER)
AS
begin
  execute procedure sec_p_create_bean_method :syspart_id, 'mebLoad', 'Просмотр';
  execute procedure sec_p_create_bean_method :syspart_id, 'mebLoadBrowse', 'Просмотр списка';
  execute procedure sec_p_create_bean_method :syspart_id, 'mebCreate', 'Создание';
  execute procedure sec_p_create_bean_method :syspart_id, 'mebRemove', 'Удаление';
  execute procedure sec_p_create_bean_method :syspart_id, 'mebStore', 'Изменение';
  /*execute procedure sec_p_create_bean_method :syspart_id, 'mebClone', 'Копирование';*/
  execute procedure sec_p_create_bean_method :syspart_id, 'mebChangeParent', 'Изменение папки';
  execute procedure sec_p_create_bean_method :syspart_id, 'mebHierarchy', 'Изменение иерархии';
end
^

ALTER PROCEDURE STL_GET_BASE_DOCS (
    CONTRACTORCARD_ID INTEGER)
RETURNS (
    BASEDOCTYPE CHAR(15),
    BASEDOCNUMBER CHAR(20),
    BASEDOCDATE TIMESTAMP,
    CURRENCY_CODE CHAR(20),
    SUMMANAT NUMERIC(15,4),
    SUMMACUR NUMERIC(15,4))
AS
declare variable cntr_id integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 09.06.2001
 Измененено: 18.06.2001, 12.10.2001
 Назначение: список базовых документов по карточке контрагента
 Используется: сервером приложения
**********************************************************/
  for
    select distinct c.contractor_id, d.basedoctype, d.basedocnumber, d.basedocdate, d.currency_code
    from contractorcardhist ch, contractorcard c, dochead d/*, dochead d1*/
    where (c.id = :contractorcard_id) and  (ch.dochead_id=d.id) and
      (ch.contractorcard_id = c.id) and
      /*(d1.doctype = d.basedoctype) and (d1.docnumber = d.basedocnumber) and
      (d1.docdate = d.basedocdate) and (d1.to_id = c.contractor_id) and*/
      (d.basedoctype is not null)
    into :cntr_id, :basedoctype, :basedocnumber, :basedocdate, :currency_code
  do begin
    select sum(d.summanat), sum(d.summacur)
    from dochead d
    where (d.doctype = :basedoctype) and
      (d.docnumber = :basedocnumber) and
      (d.docdate = :basedocdate) and
      ((d.to_id = :cntr_id) or (d.from_id = :cntr_id))
    into :summanat, :summacur;

    suspend;
  end
end
^

ALTER PROCEDURE STL_GET_CONTRACTS (
    CONTRACTORCARD_ID INTEGER)
RETURNS (
    CONTRACTTYPE CHAR(15),
    CONTRACTNUMBER CHAR(20),
    CONTRACTDATE TIMESTAMP,
    CURRENCY_CODE CHAR(20),
    SUMMANAT NUMERIC(15,4),
    SUMMACUR NUMERIC(15,4))
AS
declare variable cntr_id integer;
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 09.06.2001
 Измененено: 18.06.2001, 12.10.2001
 Назначение: список договоров по карточке контрагента
 Используется: сервером приложения
**********************************************************/
  for
    select distinct c.contractor_id, d.contracttype, d.contractnumber, d.contractdate, d.currency_code
    from contractorcardhist ch, contractorcard c, dochead d/*, dochead d1*/
    where (c.id = :contractorcard_id) and  (ch.dochead_id=d.id) and
      (ch.contractorcard_id = c.id) and
      /*(d1.doctype = d.contracttype) and (d1.docnumber = d.contractnumber) and
      (d1.docdate = d.contractdate) and (d1.to_id = c.contractor_id) and*/
      (d.contracttype is not null)
    into :cntr_id, :contracttype, :contractnumber, :contractdate, :currency_code
  do begin
    select sum(d.summanat), sum(d.summacur)
    from dochead d
    where (d.doctype = :contracttype) and
      (d.docnumber = :contractnumber) and
      (d.docdate = :contractdate) and
      ((d.to_id = :cntr_id) or (d.from_id = :cntr_id))
    into :summanat, :summacur;

    suspend;
  end
end
^

ALTER PROCEDURE SYS_CREATE_CLASS (
    MODULE VARCHAR(80),
    BEAN_NAME VARCHAR(128),
    DESCRIPTION VARCHAR(256))
AS
declare variable class_id integer;
begin
  select c.id from sys_class c where c.bean_name = :bean_name into :class_id;

  if (class_id is not null) then
    update sys_class c set c.description = :description where c.id = :class_id;
  else begin
    insert into sys_class (
      id,
      module_id,
      bean_name,
      description)
    values (
      gen_id(sys_class_id_gen,1),
      (select m.id from sys_module m where m.name = :module),
      :bean_name,
      :description);
    execute procedure sec_create_methods_cur_class;
  end
end
^

ALTER PROCEDURE WF_SET_ACTIVITY_RESULT (
    PROCESS_ID INTEGER,
    ACTIVITY_ID INTEGER,
    THE_NAME CHAR(20),
    THE_VALUE VARCHAR(100))
AS
DECLARE VARIABLE RES_ID INTEGER;
begin
  select id from wf_result where (process_id = :process_id) and (activity_id = :activity_id) and (the_name = :the_name)
  into :res_id;
  if (res_id is null) then begin
    INSERT INTO WF_RESULT (ID,PROCESS_ID,ACTIVITY_ID,THE_NAME,THE_VALUE)
    VALUES (gen_id(wf_result_id_gen, 1),:PROCESS_ID,:ACTIVITY_ID,:THE_NAME,:THE_VALUE);
  end
  else begin
    UPDATE WF_RESULT SET
      THE_VALUE = :the_value
    WHERE ID = :res_id;
  end
end
^

ALTER PROCEDURE WF_SET_PROCESS_RESULT (
    PROCESS_ID INTEGER,
    THE_NAME CHAR(20),
    THE_VALUE VARCHAR(100))
AS
declare variable id integer;
begin
  select id from wf_result
  where (process_id = :process_id) and (the_name = :the_name) and (activity_id is null)
  into :id;
  if (id is null) then
    INSERT INTO WF_RESULT (ID,PROCESS_ID,THE_NAME,THE_VALUE)
    VALUES (gen_id(wf_result_id_gen, 1),:PROCESS_ID,:THE_NAME,:THE_VALUE);
  else
    update wf_result set
      the_value = :the_value
    where id = :id;
end
^

ALTER PROCEDURE WH_ALLOT_ADDEXPENSES (
    DOC_ID INTEGER,
    DS_ID INTEGER,
    EXPEN_SUM NUMERIC(15,4))
AS
declare variable id integer;
declare variable price numeric(15,4);
declare variable summa numeric(15,4);
declare variable quantity numeric(15,3);
declare variable docsum numeric(15,4);
begin
  select sum(summa) from docspec where dochead_id = :doc_id
  into :docsum;
  for
    select d.id, d.price, d.summa, d.quantity
    from docspec d where (dochead_id = :doc_id)
    into :id, :price, :summa, :quantity
  do
    if (expen_sum = 0) then begin
      if (ds_id = 16) then
        update stockdocumentspec set
          cost = :summa
        where docspec_id = :id;
      else if (ds_id = 21) then
        update orderspec set
          cost = :summa
        where docspec_id = :id;
      else if (ds_id = 23) then
        update invoicespec set
          cost = :summa
        where docspec_id = :id;
      else if (ds_id = 25) then
        update billspec set
          cost = :summa
        where docspec_id = :id;
    end
    else begin
      if (ds_id = 16) then
        update stockdocumentspec set
          cost = :summa + :summa * :expen_sum / :docsum
        where docspec_id = :id; 
      else if (ds_id = 21) then
        update orderspec set
          cost = :summa + :summa * :expen_sum / :docsum
        where docspec_id = :id; 
      else if (ds_id = 23) then
        update invoicespec set
          cost = :summa + :summa * :expen_sum / :docsum
        where docspec_id = :id; 
      else if (ds_id = 25) then
        update billspec set
          cost = :summa + :summa * :expen_sum / :docsum
        where docspec_id = :id; 
    end
end
^

ALTER PROCEDURE WH_F_BROWSE_BATCH_BIN_LOCATION (
    BATCH_ID INTEGER)
RETURNS (
    ID INTEGER,
    CODE CHAR(20),
    QUANTITY NUMERIC(15,3))
AS
DECLARE VARIABLE IN_QUAN NUMERIC(15,3);
DECLARE VARIABLE OUT_QUAN NUMERIC(15,3);
begin
  for
    select bl.id, bl.code from wh_bin_location bl, wh_bin_location_detail bld, stockbatch sb
    where (bl.id = bld.bin_location_id) and (sb.id = bld.stockbatch_id) and
      (sb.endquan > 0) and (bld.kind = 0) and (bld.stockbatch_id = :batch_id)
    into :id, :code
  do begin
    select sum(bld0.quantity) from wh_bin_location_detail bld0
    where (bld0.bin_location_id = :id) and (bld0.kind = 0) and (bld0.stockbatch_id = :batch_id)
    into :in_quan;
    select sum(bld1.quantity) from wh_bin_location_detail bld1
    where (bld1.bin_location_id = :id) and (bld1.kind = 1) and (bld1.stockbatch_id = :batch_id)
    into :out_quan;
    if (in_quan is null) then
      in_quan = 0;
    if (out_quan is null) then
      out_quan = 0;
    quantity = in_quan - out_quan;
    if (quantity > 0) then
      suspend;
  end
end
^

ALTER PROCEDURE WH_F_COPY_SER_NUM_FROM_DOCSPEC (
    DOCSPEC_ID INTEGER,
    STOCKBATCH_ID INTEGER)
RETURNS (
    SERNUM_COUNT INTEGER)
AS
begin
/**********************************************************
 Автор: Oleg V. Safonov
 Создано: 22.06.2001
 Измененено:
 Назначение: копирование серийных номеров из спецификаций документа
 Используется: сервером приложения
**********************************************************/
  INSERT INTO WH_SERIAL_NUM (ID,BATCH_ID,SERIAL_NUM,INCOME_DOCSPEC_ID)
  SELECT gen_id(WH_SERIAL_NUM_ID_GEN, 1), :STOCKBATCH_ID, dsn.SERIAL_NUM, :DOCSPEC_ID
  from DOC_SPEC_SERIAL_NUM dsn
  where dsn.docspec_id = :DOCSPEC_ID;

  select count(*) from doc_spec_serial_num
  where docspec_id = :docspec_id
  into :sernum_count;
  suspend;
end
^

ALTER PROCEDURE WH_F_INCOME_TO_STOCK (
    STOCKBATCH_ID INTEGER,
    CREATEDATE TIMESTAMP,
    DOC_ID INTEGER,
    DOCSPEC_ID INTEGER,
    QUAN NUMERIC(18,6),
    QUAN2 NUMERIC(18,6),
    PREV_SBH INTEGER)
RETURNS (
    STOCKBATCHHIST_ID INTEGER)
AS
begin
  /* add stockbatchhistory */
  STOCKBATCHHIST_ID = gen_id(stockbatchhistory_id_gen, 1);
  insert into stockbatchhistory(id, stockbatch_id, kind, document_id, documentspec_id, quantity, quantity2, processdate, prev_stockbatchhist_id)
  values(:STOCKBATCHHIST_ID, :stockbatch_id, 0, :doc_id, :docspec_id, :quan, :quan2, :createdate, :prev_sbh);
  suspend;
end
^

ALTER PROCEDURE WH_F_LOAD_BATCH_BIN_LOCATION (
    STOCKBATCHHIST_ID INTEGER)
RETURNS (
    BIN_CODE VARCHAR(20),
    QUAN NUMERIC(15,3),
    BATCH_ID INTEGER,
    PREVHIST_ID INTEGER)
AS
begin
  select sbh.prev_stockbatchhist_id, sbh.stockbatch_id from stockbatchhistory sbh
  where sbh.id = :stockbatchhist_id
  into :prevhist_id, :batch_id;
   for
    /* defect 2486 fixed */
    select wbl.code, wblh.quantity -
      (select ROUND(sum(wblh2.quantity), 3) from wh_bin_location_detail wblh2
       where (wblh2.bin_location_id = wblh.bin_location_id) and (wblh2.kind = 1) and (wblh2.stockbatch_id = :batch_id))
    from wh_bin_location wbl, wh_bin_location_detail wblh
    where (wbl.id = wblh.bin_location_id) and (wblh.kind = 0) and (wblh.stockbatch_id = :batch_id)
    into :bin_code, :quan
  do
    if (quan > 0) then begin
      suspend;
    end
end
^

ALTER PROCEDURE WH_F_LOAD_BATCH_SERIAL_NUM (
    STOCKBATCHHIST_ID INTEGER)
RETURNS (
    SERIAL_NUM VARCHAR(50))
AS
DECLARE VARIABLE SCARD_ID INTEGER;
begin
/**********************************************************
 Автор: Oleg V. Safonov
 Создано: 22.06.2001
 Измененено:
 Назначение: загрузка серийных номеров из складских партий
 Используется: сервером приложения
**********************************************************/
  /*select sbh.prev_stockbatchhist_id, sbh.stockbatch_id from stockbatchhistory sbh
  where sbh.id = :stockbatchhist_id
  into :prevhist_id, :batch_id;
  if (prevhist_id is null) then begin
    for
      select wsn.serial_num from wh_serial_num wsn
      where wsn.batch_id = :batch_id
      into :serial_num
    do
      suspend;
  end
  else begin
    for
      select lsn.serial_num from WH_F_LOAD_BATCH_SERIAL_NUM(:prevhist_id) lsn
      into :serial_num
    do
      suspend;
  end*/
  /* показываем список всех серийных номеров не списанных со склада,
     а не только те которые относятся к текущему списыванию, иначе
     требуются серьезные изменения в списании */
  select sb.stockcard_id from stockbatch sb, stockbatchhistory sbh
  where (sbh.stockbatch_id = sb.id) and (sbh.id = :STOCKBATCHHIST_ID)
  into :scard_id;
  for
    select wsn.serial_num from wh_serial_num wsn, stockbatch sb
    where (wsn.batch_id = sb.id) and (sb.stockcard_id = :scard_id) and (wsn.outcome_docspec_id is null)
    into :serial_num
  do
    suspend;
end
^

ALTER PROCEDURE WH_GET_RESERVE (
    WARECARD_ID INTEGER)
RETURNS (
    SYSDATETIME TIMESTAMP,
    PROCESSDATE TIMESTAMP,
    FROM_CODE CHAR(20),
    TO_CODE CHAR(20),
    QUANTITY NUMERIC(15,3),
    QUANTITY2 NUMERIC(15,3),
    DOCTYPE CHAR(15),
    DOCNUMBER CHAR(20),
    DOCDATE TIMESTAMP,
    DOCUMENT_ID INTEGER,
    DOCSECTION INTEGER,
    BASEDOCTYPE CHAR(15),
    BASEDOCNUMBER CHAR(20),
    BASEDOCDATE TIMESTAMP)
AS
declare variable qty numeric(15,3);
declare variable qty2 numeric(15,3);
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 15.01.2003
 Назначение: резервирование по скл.карточке.
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 28.01.2003 Кривопустов Добавлены поля в рез.набор
**********************************************************/
  for
    select h.sysdatetime, h.processdate,
      h.quantity, h.quantity2, d.doctype, d.docnumber, d.docdate, d.id, d.docsection,
      d.basedoctype, d.basedocnumber, d.basedocdate, c1.code from_code, c2.code to_code
    from stockplanhistory h
      left join dochead d on d.id = h.dochead_id
      left join contractor c1 on c1.id = d.from_id
      left join contractor c2 on c2.id = d.to_id
    where (h.stockcard_id = :warecard_id) and
      (h.kind = 2)
    order by h.processdate
    into :sysdatetime,:processdate,
      :quantity,:quantity2,:doctype,:docnumber,:docdate,:document_id,:docsection,
      :basedoctype, :basedocnumber, :basedocdate, :from_code, :to_code
  do begin
    qty = 0;
    qty2 = 0;
    select sum(h1.quantity), sum(h1.quantity2) from stockplanhistory h1
    where (h1.stockcard_id = :warecard_id) and
          (h1.kind = 3) and
          (h1.dochead_id = :document_id)
    into :qty, :qty2;
    if (qty is null) then qty = 0;
    if (qty2 is null) then qty2 = 0;

    quantity = quantity - qty;
    quantity2 = quantity2 - qty2;
    if ((quantity > 0) or (quantity2 > 0)) then
      suspend;
  end
end
^

ALTER PROCEDURE WH_P_CREATE_OUT_BIN_DETAIL (
    WAREHOUSE_ID INTEGER,
    BIN_CODE CHAR(20),
    QUAN NUMERIC(15,3),
    BATCH_ID INTEGER,
    DOCSPEC_ID INTEGER)
AS
declare variable bin_id integer;
begin
  select wbl.id from wh_bin_location wbl
  where (wbl.warehouse_id = :warehouse_id) and (wbl.code = :bin_code)
  into :bin_id;
  INSERT INTO WH_BIN_LOCATION_DETAIL (
    ID,
    BIN_LOCATION_ID,
    STOCKBATCH_ID,
    QUANTITY,
    DOCSPEC_ID,
    KIND)
  VALUES (
    gen_id(wh_bin_location_detail_id_gen, 1),
    :BIN_ID,
    :BATCH_ID,
    :QUAN,
    :DOCSPEC_ID,
     1);
end
^

ALTER PROCEDURE WH_P_OUTCOME_SERIAL_NUM (
    SPEC_ID INTEGER,
    BATCHHIST_ID INTEGER,
    SERIAL_NUM VARCHAR(50))
AS
DECLARE VARIABLE SER_ID INTEGER;
DECLARE VARIABLE SCARD_ID INTEGER;
begin
/**********************************************************
 Автор: Oleg V. Safonov
 Создано: 22.06.2001
 Измененено:
 Назначение: копирование серийных номеров из спецификаций документа
 Используется: сервером приложения
**********************************************************/
  /* get stockcard id */
  select sb.stockcard_id from stockbatch sb, stockbatchhistory sbh
  where (sb.id = sbh.stockbatch_id) and (sbh.id = :batchhist_id)
  into :scard_id;
  /* copy serial numbers in document spec, set docspec_id */
  for
    select wsn.id from wh_serial_num wsn, stockbatch sb
    where (wsn.serial_num = :serial_num) and (sb.stockcard_id = :scard_id) and (sb.id = wsn.batch_id)
    into :ser_id
  do begin
    insert into doc_spec_serial_num(id, docspec_id, serial_num)
    values (gen_id(doc_spec_serial_num_id_gen, 1), :spec_id, :serial_num);
    update wh_serial_num set
      outcome_docspec_id = :spec_id
    where id = :ser_id;
  end
end
^

ALTER PROCEDURE WH_QUAN_ON_WAREHOUSES (
    CATALOG_ID INTEGER,
    USER_ID INTEGER)
RETURNS (
    WH_CODE CHAR(20),
    FACT NUMERIC(18,3),
    PLAN_IN NUMERIC(18,3),
    PLAN_OUT NUMERIC(18,3),
    RESERVE NUMERIC(18,3),
    AVAILABLE NUMERIC(18,3),
    FACT2 NUMERIC(18,3),
    PLAN_IN2 NUMERIC(18,3),
    PLAN_OUT2 NUMERIC(18,3),
    RESERVE2 NUMERIC(18,3),
    AVAILABLE2 NUMERIC(18,3))
AS
declare variable wh_upcode char(20);
begin
/**********************************************************
 Автор: Константин Кривопустов
 Создано: 08.06.2001
 Назначение: количество на складах с учетом прав пользователя
 Используется: сервером приложения
 ---------------------------------------------------------
 Изменения:
 Дата       Сотрудник   Комментарии
 ---------------------------------------------------------
 29.03.2002 Кривопустов Количество во 2-ой ЕИ
 14.05.2003 Кривопустов Корректная работа с учетом вхождения пользователя в несколько групп
**********************************************************/
  for
    select c.code, c.upcode
    from contractor c, orgunit o, wh_warehouse wh
    where (o.contractor_id = c.id) and (wh.contractor_id = c.id) and
      exists(select * from sec_link_users_groups s, folder_rights r
             where(s.user_id = :user_id) and
                  (r.folder_id = c.id) and (r.group_id = s.group_id) and (r.folderpart = 4) and (r.rights = 1))
    into :WH_CODE, :wh_upcode
  do begin
    select f.FACT, f.PLAN_IN, f.PLAN_OUT, f.RESERVE, f.AVAILABLE,
      f.FACT2, f.PLAN_IN2, f.PLAN_OUT2, f.RESERVE2, f.AVAILABLE2
    from f_quan_on_stock(:wh_upcode, null, :catalog_id, :user_id) f
    into :FACT, :PLAN_IN, :PLAN_OUT, :RESERVE, :AVAILABLE,
      :FACT2, :PLAN_IN2, :PLAN_OUT2, :RESERVE2, :AVAILABLE2;
    suspend;
  end
end
^

ALTER PROCEDURE WH_F_GET_QUAN_IN_SECTION (
    SECTION_CODE CHAR(20),
    WAREHOUSE_ID INTEGER,
    CATALOG_CODE CHAR(20))
RETURNS (
    QUANTITY NUMERIC(15,3))
AS
DECLARE VARIABLE IN_QUAN NUMERIC(15,3);
DECLARE VARIABLE OUT_QUAN NUMERIC(15,3);
begin
  select sum(wbld.quantity) from wh_bin_location wbl
  left join wh_bin_location_detail wbld on (wbl.id = wbld.bin_location_id and wbl.warehouse_id = :warehouse_id)
  left join stockbatch sb on sb.id = wbld.stockbatch_id
  left join stockcard sc on sc.id = sb.stockcard_id
  left join catalog c on c.id = sc.catalog_id
  where (wbl.code = :section_code) and (wbld.kind = 0) and (c.upcode = :catalog_code)
  into :in_quan;
  if (in_quan is null) then in_quan = 0;

  select sum(wbld.quantity) from wh_bin_location wbl
  left join wh_bin_location_detail wbld on (wbl.id = wbld.bin_location_id and wbl.warehouse_id = :warehouse_id)
  left join stockbatch sb on sb.id = wbld.stockbatch_id
  left join stockcard sc on sc.id = sb.stockcard_id
  left join catalog c on c.id = sc.catalog_id
  where (wbl.code =:section_code) and (wbld.kind = 1) and (c.upcode = :catalog_code)
  into :out_quan;
  if (out_quan is null) then out_quan = 0;

  quantity = in_quan - out_quan;
  suspend;
end
^

ALTER PROCEDURE WH_F_CHECK_BIN_LOCATION_SIZE (
    CATALOG_CODE CHAR(20),
    BIN_CODE CHAR(20),
    WAREHOUSE_ID INTEGER,
    QUANTITY NUMERIC(15,3),
    BIN_SIZING SMALLINT)
RETURNS (
    BIN_ID INTEGER,
    STOCK_CODE CHAR(20),
    RESULT SMALLINT)
AS
DECLARE VARIABLE QUAN NUMERIC(15,3);
begin
  quan = 0;
  FOR
    select distinct wbl.id, c.upcode from wh_bin_location wbl
    left join wh_bin_location_detail wbld on wbl.id = wbld.bin_location_id
    left join stockbatch sb on sb.id = wbld.stockbatch_id
    left join stockcard sc on sc.id = sb.stockcard_id
    left join catalog c on c.id = sc.catalog_id
    where (wbl.code = :bin_code) and (wbl.warehouse_id = :warehouse_id)
    into :bin_id, :stock_code
  DO BEGIN
    execute procedure WH_F_GET_QUAN_IN_SECTION(:bin_code, :warehouse_id, :stock_code)
      returning_values :quan;
    if (quan > 0) then
     break;
  END

  result = 0;
  if (bin_id is null) then begin
    result = 1; /* not found */
    suspend;
  end else
  if ((stock_code is not null) and (stock_code <> :CATALOG_CODE)) then begin
      /*execute procedure WH_F_GET_QUAN_IN_SECTION(:bin_code, :warehouse_id, :stock_code)
      returning_values :quan;*/
      if (quan > 0) then result = 3; /* Секция занята */
      suspend;
  end else begin
    /* do not check size. OVS(in future implementation) */
    result = 0;
    suspend;
  end
END
^