
CREATE OR REPLACE VIEW TA_JC_COUNT_INSTANCE_INFO1 AS
SELECT
    C.INSTANCE_ID,              --办件实例ID
    '1' as type_sort,
    (select x.supervise_info_id
          from (select supervise_info_id
                  from ta_jc_supervise_info f
                 where f.business_id =C.instance_id and f.state ='Y' and f.supervise_type_no in ('1011','1014')
                 order by f.supervise_result_no desc, f.supervise_time desc) x
         where rownum = 1) SUPERVISE_ID,--最高一次监察结果ID

    C.Instance_Name,          --项目名称
    C.Time_Use as WASTING_TIME,            --所用时间
    C.Instance_Source,            --数TA_JC_COUNT_INSTANCE_INFO1据来源
    C.Project_State as PROCESSING_STATE,       --办理状态  0：初始化 1：在办 2：已办
    C.Apply_Name AS USER_NAME,    --申请人/申请单位
    C.ACCEPT_TIME,              --受理时间
    C.notaccept_reason,          --不予受理或补正补齐原因
    C.End_Time as FINISH_TIME,                   --办结时间
    A.APPROVE_ID ,              -- 事项ID
    B.ORG_ID,                       --机构编码
    B.ORG_CODE AS AREA_ORG_CODE,    --区域机构编码
    B.ORG_NAME AS AREA_ORG_NAME,    --区域机构名称
    B.PROVINCE_CODE,                --省级编码
    B.CITY_CODE,                    --市级编码
    B.County_CODE,                  --区（县）编码
    B.STREET_CODE,                  --街道办事处编码
    B.VILLAGE_CODE,                 --社区编码
    B.ORG_LEVEL,                    --区域机构级别 1：省 2：市 3：区（县）4：乡镇（街道）5：机构
    B.ORG_THIS_LEVEL,               -- 1,省本级， 2，市本级， 9其他
    A.APPROVE_CODE,            --事项编码
    A.APPROVE_NAME,            --事项名称
    A.TRANS_TYPE_CODE,          --事项类型；0：即办件，1：承诺件，2：上报件，3：并联审批件
    A.APPROVE_TYPE_CODE,        --事项类别；1：行政许可事项，2：非行政许可事项，9：便民服务事项
    F.TRANS_TYPE_NAME,          --事项类型名称
    A.TIME_LIMIT,              --审批时限
    A.PROMISES_LIMIT,            --承诺时限
    A.IS_CHARGE,
    1 AS SUPERVISE_TYPE,
    A.TYPE_CODE

FROM TA_SPXM A
INNER JOIN V_TD_SM_ORGANIZATION1 B ON A.ORG_ID=B.ORG_ID
INNER JOIN TA_SP_INSTANCE C ON A.APPROVE_ID = C.APPROVE_ID
INNER JOIN TA_JC_DIC_TRANSTYPE F ON trim(A.TRANS_TYPE_CODE) = trim(F.TRANS_TYPE_CODE)
WHERE C.ACCEPT_TIME >= TO_DATE('2015-04-01 00:00:00', 'yyyy-mm-dd hh24:mi:ss')

UNION ALL

--- 咨询

SELECT
    C.Consult_Id as INSTANCE_ID,              --办件实例ID
    '2' as type_sort,
    (select x.supervise_info_id
          from (select *
                  from ta_jc_supervise_info f
                 where f.business_id =C.Consult_Id and f.state ='Y' and f.supervise_type_no in ('1013','1015')
                 order by f.supervise_result_no desc, f.supervise_time desc) x
         where rownum = 1) SUPERVISE_ID,--最高一次监察结果ID

    C.CONSULT_TITLE as Instance_Name,          --项目名称
    0 as WASTING_TIME,            --所用时间
    C.Consult_Source as Instance_Source,            --数据来源
    '' as PROCESSING_STATE,       --办理状态  0：初始化 1：在办 2：已办
    C.Apply_Name AS USER_NAME,    --申请人/申请单位
    C.Consult_Time as ACCEPT_TIME,              --受理时间
    '' as  notaccept_reason,          --不予受理或补正补齐原因
    C.Reply_Time as FINISH_TIME,                   --办结时间
    A.APPROVE_ID ,              -- 事项ID
    B.ORG_ID,                       --机构编码
    B.ORG_CODE AS AREA_ORG_CODE,    --区域机构编码
    B.ORG_NAME AS AREA_ORG_NAME,    --区域机构名称
    B.PROVINCE_CODE,                --省级编码
    B.CITY_CODE,                    --市级编码
    B.County_CODE,                  --区（县）编码
    B.STREET_CODE,                  --街道办事处编码
    B.VILLAGE_CODE,                 --社区编码
    B.ORG_LEVEL,                    --区域机构级别 1：省 2：市 3：区（县）4：乡镇（街道）5：机构
    B.ORG_THIS_LEVEL,               -- 1,省本级， 2，市本级， 9其他
    A.APPROVE_CODE,            --事项编码
    A.APPROVE_NAME,            --事项名称
    A.TRANS_TYPE_CODE,          --事项类型；0：即办件，1：承诺件，2：上报件，3：并联审批件
    A.APPROVE_TYPE_CODE,        --事项类别；1：行政许可事项，2：非行政许可事项，9：便民服务事项
    F.TRANS_TYPE_NAME,          --事项类型名称
    A.TIME_LIMIT,              --审批时限
    A.PROMISES_LIMIT,            --承诺时限
    A.IS_CHARGE,
    2 AS SUPERVISE_TYPE,
    A.TYPE_CODE

FROM TA_SPXM A
INNER JOIN V_TD_SM_ORGANIZATION1 B ON A.ORG_ID=B.ORG_ID
INNER JOIN ta_sp_consult C ON A.APPROVE_ID = C.APPROVE_ID
INNER JOIN TA_JC_DIC_TRANSTYPE F ON trim(A.TRANS_TYPE_CODE) = trim(F.TRANS_TYPE_CODE)
WHERE C.Consult_Time >= TO_DATE('2015-04-01 00:00:00', 'yyyy-mm-dd hh24:mi:ss')


UNION ALL

--- 投诉

SELECT
    C.Complain_Id as INSTANCE_ID,              --办件实例ID
    '3' as type_sort,
    (select x.supervise_info_id
          from (select *
                  from ta_jc_supervise_info f
                 where f.business_id =C.Complain_Id and f.state ='Y' and f.supervise_type_no in ('1012')
                 order by f.supervise_result_no desc, f.supervise_time desc) x
         where rownum = 1) SUPERVISE_ID,--最高一次监察结果ID

    C.Complain_Title as Instance_Name,          --项目名称
    0 as WASTING_TIME,            --所用时间
    '' as Instance_Source,            --数据来源
    '' as PROCESSING_STATE,       --办理状态  0：初始化 1：在办 2：已办
    C.Complain_User_Name AS USER_NAME,    --申请人/申请单位
    C.Accept_Time as ACCEPT_TIME,              --受理时间
    '' as  notaccept_reason,          --不予受理或补正补齐原因
    C.Reply_Time as FINISH_TIME,                   --办结时间
    '' as APPROVE_ID ,              -- 事项ID
    B.ORG_ID,                       --机构编码
    B.ORG_CODE AS AREA_ORG_CODE,    --区域机构编码
    B.ORG_NAME AS AREA_ORG_NAME,    --区域机构名称
    B.PROVINCE_CODE,                --省级编码
    B.CITY_CODE,                    --市级编码
    B.County_CODE,                  --区（县）编码
    B.STREET_CODE,                  --街道办事处编码
    B.VILLAGE_CODE,                 --社区编码
    B.ORG_LEVEL,                    --区域机构级别 1：省 2：市 3：区（县）4：乡镇（街道）5：机构
    B.ORG_THIS_LEVEL,               -- 1,省本级， 2，市本级， 9其他
    '' as APPROVE_CODE,
    '' as APPROVE_NAME,
    '' as TRANS_TYPE_CODE,
    '' as APPROVE_TYPE_CODE,
    '' as TRANS_TYPE_NAME,
    0 as TIME_LIMIT,
    0 as PROMISES_LIMIT,
    '' as IS_CHARGE,
    3 AS SUPERVISE_TYPE,
    '' AS TYPE_CODE
FROM ta_JC_complain_baseinfo C
INNER JOIN V_TD_SM_ORGANIZATION1 B ON C.ACCEPT_ORG_ID=B.ORG_ID

WHERE C.Accept_Time >= TO_DATE('2015-04-01 00:00:00', 'yyyy-mm-dd hh24:mi:ss')
;



CREATE OR REPLACE VIEW VA_TA_JC_ZWFW_INSTANCE_DETAIL AS
SELECT t1.instance_id,
       T1.ORG_NAME,
       T1.INSTANCE_NAME,
       T1.INSTANCE_CODE,
       T1.APPLY_NAME,
       T1.APPROVE_NAME,
       T1.QUERY_CODE,
       T1.PROJECT_STATE,
       T1.ACCEPT_TIME,
       T1.END_TIME,
       T2.APPROVE_LIMIT    FDQX,
       T2.COMMITMENT_LIMIT CNQX,
       t1.time_use,
       t3.supervise_result_no,
       t4.supervise_result_name
  FROM TA_SP_INSTANCE T1
  LEFT JOIN hncs_approve.TA_SP_SERVICES_GUIDE T2 ON T1.APPROVE_ID = T2.APPROVE_ID
  LEFT JOIN ta_jc_count_instance_info1 t5 ON t1.instance_id = t5.INSTANCE_ID
  LEFT JOIN TA_JC_SUPERVISE_INFO T3 ON t5.SUPERVISE_ID = t3.supervise_info_id
  LEFT JOIN ta_jc_dic_supervise_result t4 ON t3.supervise_result_no = t4.supervise_result_code;