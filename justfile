list-mails:
  curl http://localhost:8025/api/v1/messages  | jq ".messages"

query-mails:
  curl http://localhost:8025/api/v1/messages  | jq ".messages" | duckdb  -c "SELECT Subject, Snippet, Created, struct_extract(\"From\",'Address') as sender, struct_extract(array_extract(\"To\",1),'Address') as receiver FROM read_json('/dev/stdin')"
