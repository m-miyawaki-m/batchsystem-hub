### 調査に使うSQL


```SQL
--tableのカラム名とコメントを取得
select
    a.column_name
    , a.data_type
    , c.comments
from
    all_tab_columns a
join
    all_col_comments c
on a.table_name = c.table_name
    and a.column_name = c.column_name
where a.table_name = UPPER('JOBS') AND a.owner = UPPER('HR');

--任意のカラム名を持つテーブルを検索
select *
from all_tab_columns
where owner=UPPER('HR')
and column_name like UPPER('%JOB_%')
order by table_name desc
;

--任意のコメントを持つテーブルを検索
select table_name,comments
from all_tab_comments
where comments  like UPPER('%key%') 
AND owner = UPPER('HR');

--任意のテーブルのカラム数を取得
SELECT COUNT(*) AS column_count
FROM all_tab_columns
WHERE table_name = UPPER('JOBS') AND owner = UPPER('HR');


--任意のスキーマのテーブルのカラム数一覧を取得
SELECT table_name, COUNT(column_name) AS column_count
FROM all_tab_columns
WHERE owner = UPPER('HR')
GROUP BY table_name;

--任意のテーブルの型の種類を取得
SELECT DISTINCT data_type
FROM all_tab_columns
WHERE table_name = UPPER('JOBS') AND owner = UPPER('HR');
```