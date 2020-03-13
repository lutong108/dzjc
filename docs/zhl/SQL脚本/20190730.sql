CREATE OR REPLACE VIEW V_JC_TONGJI_ORG AS
SELECT t1.ID as ORG_ID,             --id
       t1.CODE AS ORG_CODE,      --机构编码
       t1.NAME AS ORG_NAME,    --机构名称
       t1.SN AS ORDER_NUM,       --排序
       t1.PID as p_id,                      --父id
      /* t2.CODE as p_code,              --父编码*/
       SUBSTR(t1.CODE,0,2) ||'0000000000' AS PROVINCE_CODE,    --省级编码
       DECODE(SUBSTR(t1.CODE,3,2),'00','000000000000',SUBSTR(t1.CODE,0,4) ||'00000000') AS CITY_CODE,  -- 市级编码
       DECODE(SUBSTR(t1.CODE,5,2),'00','000000000000',SUBSTR(t1.CODE,0,6) ||'000000') AS County_CODE,  -- 县区级编码
       DECODE(SUBSTR(t1.CODE,7,3),'000','000000000000',SUBSTR(t1.CODE,0,9) || '000') AS STREET_CODE,   -- 街道编码
       DECODE(SUBSTR(t1.CODE,10,3),'000','000000000000',SUBSTR(t1.CODE,0,12)) AS VILLAGE_CODE,         --社区编码
       CASE WHEN SUBSTR(t1.CODE,13,9) ='000000000'
       THEN
           CASE WHEN SUBSTR(t1.CODE,7,6) ='000000'
                 THEN
                     CASE WHEN SUBSTR(t1.CODE,5,2) ='00'
                         THEN
                                CASE WHEN SUBSTR(t1.CODE,3,2) ='00'
                                THEN '1' ELSE '2' END
                         ELSE '3' END
                ELSE '4' END
            ELSE '5' END AS ORG_LEVEL,  --级别 1：省 2：市 3：区（县）4：乡镇（街道）5：机构
        CASE WHEN (SUBSTR(t1.CODE,3,2) != '99' OR  SUBSTR(t1.CODE,5,20) != '00000000000000000000')  THEN
           CASE WHEN  SUBSTR(t1.CODE,5,2) = '01' AND SUBSTR(t1.CODE,7,18) ='000000000000000000'
           THEN '2' ELSE '9' END
      ELSE '1' END AS ORG_THIS_LEVEL   -- 本级 1：省本级 2： 市本级 9: 其他
       FROM v_jc_org t1 /*left join v_jc_org t2 on t1.PID = t2.ID    */where t1.CODE <> '3'
;


alter table TA_JC_FILTER_ORG
  add constraint Unique_TA_JC_FILTER_ORG_ID unique (ORG_ID);
