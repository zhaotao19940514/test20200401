select * from QTK_CUSTOMERINFO where customer_type='26';

select * from QTK_MONITOR_TRANSINFACE_DETAIL ORDER by CREATE_TIME desc;


-- 查3.15之前的黑名单
select * from QTK_CARD_BLACK_LIST where card_id='52011328220201767319';


select * from QTK_CHARGE_DETAIL where charge_id='252010106004042200022018061209555638' ; 

update QTK_CHARGE_DETAIL
 set status = '0'
 where charge_id='252010106004042200022018061209555638' ;
commit;



select * from qtk_cardinfo;