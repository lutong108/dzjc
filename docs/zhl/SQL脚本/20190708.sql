create or replace view va_ta_jc_zwfw_instance as
select t1.instance_id as instance_id,
       t4.supervise_result_name as supervise_result_name,
       t3.supervise_type_name as supervise_type_name,
       t1.area_org_name as area_org_name,
       t1.instance_name as instance_name,
       t1.instance_code as instance_code,
       t1.accept_time as accept_time,
       t1.finish_time as finish_time,
       t1.processing_state as processing_state,
       t1.type_code as approve_type_code,
       t1.type_name as approve_type_code_name,
       t2.supervise_type_no as supervise_type_no,
       t2.supervise_result_no as supervise_result_no,
       t2.state as state,
       t1.org_id as org_id,
       t1.approve_name as approve_name
  from
  (
      select c.instance_id as instance_id,
             (
               select x.supervise_info_id
                  from (select supervise_info_id
                          from ta_jc_supervise_info f
                         where f.business_id = c.instance_id
                           and f.state = 'Y'
                           and f.supervise_type_no in ('1011', '1014')
                         order by f.supervise_result_no desc, f.supervise_time desc) x
                 where rownum = 1
               ) supervise_id,
             c.instance_name as instance_name,
             c.project_state as processing_state,
             c.accept_time as accept_time,
             c.end_time as finish_time,
             b.org_id as org_id,
             b.org_name as area_org_name,
             a.approve_name as approve_name,
             a.type_code as type_code,
             a.type_name as type_name,
             c.instance_code as instance_code
        from ta_spxm a
       inner join v_td_sm_organization1 b
          on a.org_id = b.org_id
       inner join ta_sp_instance c
          on a.approve_id = c.approve_id
       --inner join ta_jc_dic_transtype f
       -- on trim(a.trans_type_code) = trim(f.trans_type_code)
       where c.accept_time >= to_date('2015-04-01 00:00:00', 'yyyy-mm-dd hh24:mi:ss')
  ) t1
  left join ta_jc_supervise_info t2 on t1.supervise_id = t2.supervise_info_id
  left join ta_jc_dic_supervise_type t3 on t2.supervise_type_no = t3.supervise_type_code
  left join ta_jc_dic_supervise_result t4 on t2.supervise_result_no = t4.supervise_result_code
;
