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