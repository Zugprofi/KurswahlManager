fun runSqlSmart(sql: String): List<Map<String, Any?>> {
    return try {
        db.fetchAll(sql)
            .getOrThrow()
            .map { row ->
                row.columns.associateWith { col ->
                    row.get(col).value
                }
            }
    } catch (e: Exception) {
        db.execute(sql).getOrThrow()
        emptyList()
    }
}

//port 22
//host wetzlich.name
//ticketsystem
//C@0.n!0PSy)ixVEu

val result = runSqlSmart("SELECT * FROM users")

for (row in result) {
    println(row["id"])
    println(row["name"])
}
