--老办事指南表
create or replace view v_jc_ta_sp_services_guide_old as
select 
t.*
from approve.ta_sp_services_guide t;



--新办事指南表
create or replace view v_jc_ta_sp_services_guide as
select 
t.*
from hncs_approve.ta_sp_services_guide t;



--场景分类字典表
create or replace view v_jc_ta_dic_approvetype as
select 
t.*
from hncs_approve.ta_dic_approvetype t;