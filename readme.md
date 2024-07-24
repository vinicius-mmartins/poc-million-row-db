
### comandos

~~~
docker exec -it 1millionrow-postgres-1 bash
psql -p 5432 -h localhost -U postgres
~~~

ver erro no container:
docker logs --tail 50 --follow --timestamps mediawiki_web_1

~~~
\l   #list dbs
\c <db name>: connect to a certain database
\dt: list all tables in the current database using your search_path
\dt *. list all tables in the current database regardless your search_path
~~~

### TODO
- Fazer o teste da stream com jpa.
- Impl csv só com Java. Arquivo com separador, escrevendo em chunkz.