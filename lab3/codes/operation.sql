-- select name,discount of customer 1

SELECT name,discount 
FROM customer,mydiscount 
WHERE customer.account = 
	(SELECT account FROM customer ORDER BY age DESC LIMIT 1) AND
customer.account = mydiscount.customer_account;

-- select name,discount of customer 2

SELECT name,discount 
FROM customer,mydiscount 
WHERE customer.account = 
	(SELECT account FROM customer ORDER BY age ASC LIMIT 1) AND
customer.account = mydiscount.customer_account;
-------------------------------
-- customer 1 buy his goods
-------------------------------

--select account of customer 1 
SELECT account FROM customer ORDER BY age DESC LIMIT 1;

-- select cheapest goods from 4 different type
SELECT saller_account,goods_goodsid FROM store 
WHERE goods_goodsid in
	(SELECT goodsid FROM goods WHERE type = '0001')
ORDER BY price*discount1 ASC LIMIT 1;

SELECT saller_account,goods_goodsid FROM store 
WHERE goods_goodsid in
	(SELECT goodsid FROM goods WHERE type = '0010')
ORDER BY price*discount1 ASC LIMIT 1;

SELECT saller_account,goods_goodsid FROM store 
WHERE goods_goodsid in
	(SELECT goodsid FROM goods WHERE type = '0100')
ORDER BY price*discount1 ASC LIMIT 1;

SELECT saller_account,goods_goodsid FROM store 
WHERE goods_goodsid in
	(SELECT goodsid FROM goods WHERE type = '1000')
ORDER BY price*discount1 ASC LIMIT 1;

-- select original remainings
SELECT saller_account,goods_goodsid,remainings FROM store 
WHERE (saller_account = 'seller5' AND goods_goodsid = 'goods68' ) OR
(saller_account = 'seller10' AND goods_goodsid = 'goods150') OR
(saller_account = 'seller3' AND goods_goodsid = 'goods41') OR
(saller_account = 'seller2' AND goods_goodsid = 'goods31') ;


INSERT INTO ORDERINGS values('OD00001',0,'2017-11-03',NULL,NULL,'200240','DONGCHUAN ROAD NUM800',NULL,NULL,NULL,NULL,NULL,'customer1');

INSERT INTO `ORDERINGS CONTENT` values('OD00001','seller5','goods68',5);
INSERT INTO `ORDERINGS CONTENT` values('OD00001','seller10','goods150',4);
INSERT INTO `ORDERINGS CONTENT` values('OD00001','seller3','goods41',6);
INSERT INTO `ORDERINGS CONTENT` values('OD00001','seller2','goods31',3);

SELECT * FROM ORDERINGS WHERE orderid = 'OD00001';

SELECT saller_account,goods_goodsid,remainings FROM store 
WHERE (saller_account = 'seller5' AND goods_goodsid = 'goods68' ) OR
(saller_account = 'seller10' AND goods_goodsid = 'goods150') OR
(saller_account = 'seller3' AND goods_goodsid = 'goods41') OR
(saller_account = 'seller2' AND goods_goodsid = 'goods31') ;

-------------------------------
-- customer 2 buy his goods
-------------------------------
--select account of customer 2
SELECT account FROM customer ORDER BY age ASC LIMIT 1;

SELECT saller_account,goods_goodsid FROM store
WHERE goods_goodsid in
	(SELECT goodsid FROM goods WHERE type = '0001')
ORDER BY remainings DESC LIMIT 1;

SELECT saller_account,goods_goodsid FROM store
WHERE goods_goodsid in
	(SELECT goodsid FROM goods WHERE type = '0010')
ORDER BY remainings DESC LIMIT 1;

SELECT saller_account,goods_goodsid FROM store
WHERE goods_goodsid in
	(SELECT goodsid FROM goods WHERE type = '0100')
ORDER BY remainings DESC LIMIT 1;

SELECT saller_account,goods_goodsid FROM store
WHERE goods_goodsid in
	(SELECT goodsid FROM goods WHERE type = '1000')
ORDER BY remainings DESC LIMIT 1;

SELECT saller_account,goods_goodsid,remainings FROM store 
WHERE (saller_account = 'seller6' AND goods_goodsid = 'goods84' ) OR
(saller_account = 'seller8' AND goods_goodsid = 'goods119') OR
(saller_account = 'seller5' AND goods_goodsid = 'goods76') OR
(saller_account = 'seller4' AND goods_goodsid = 'goods64') ;

INSERT INTO ORDERINGS values('OD00002',0,'2017-11-03',NULL,NULL,'200240','DONGCHUAN ROAD NUM800',NULL,NULL,NULL,NULL,NULL,'customer75');

INSERT INTO `ORDERINGS CONTENT` values('OD00002','seller4','goods64',28);
INSERT INTO `ORDERINGS CONTENT` values('OD00002','seller5','goods76',29);
INSERT INTO `ORDERINGS CONTENT` values('OD00002','seller6','goods84',29);
INSERT INTO `ORDERINGS CONTENT` values('OD00002','seller8','goods119',30);


SELECT saller_account,goods_goodsid,remainings FROM store 
WHERE (saller_account = 'seller6' AND goods_goodsid = 'goods84' ) OR
(saller_account = 'seller8' AND goods_goodsid = 'goods119') OR
(saller_account = 'seller5' AND goods_goodsid = 'goods76') OR
(saller_account = 'seller4' AND goods_goodsid = 'goods64') ;

SELECT * from ORDERINGS WHERE orderid = 'OD00002';

----------------------------
-- update order state of customer 1 as '已退货'
----------------------------
UPDATE ORDERINGS SET order_state ='已退货',`back reason` = '无良商家'
WHERE orderid = 'OD00001'; 

SELECT saller_account,goods_goodsid,remainings FROM store 
WHERE (saller_account = 'seller5' AND goods_goodsid = 'goods68' ) OR
(saller_account = 'seller10' AND goods_goodsid = 'goods150') OR
(saller_account = 'seller3' AND goods_goodsid = 'goods41') OR
(saller_account = 'seller2' AND goods_goodsid = 'goods31') ;

----------------------------
-- update order state of customer2 as '已完成'
----------------------------

SELECT account,totalpoint FROM customer
WHERE account = 'customer75';

UPDATE ORDERINGS SET order_state = '已完成',`order point` = 5,`comment` = '五星好评',`comment date` = '2017-11-05',`comment score`= 5 
WHERE orderid = 'OD00002' ; 

SELECT account,totalpoint FROM customer
WHERE account = 'customer75';