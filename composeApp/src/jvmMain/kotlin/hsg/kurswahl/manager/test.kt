package hsg.kurswahl.manager
//
//import io.github.smyrgeorge.sqlx4k.QueryExecutor
//import io.github.smyrgeorge.sqlx4k.ResultSet
//import io.github.smyrgeorge.sqlx4k.Statement
//
//object Sqlx {
//
//    private data class ParsedParams(
//        val named: Set<String>,
//        val positionalCount: Int
//    )
//
//
//    private fun parseParams(sql: String): ParsedParams {
//        val named = linkedSetOf<String>()
//        var positional = 0
//
//        var i = 0
//        var inSingle = false
//        var inDouble = false
//        var inBacktick = false
//        var inLineComment = false
//        var inBlockComment = false
//
//        fun isIdentStart(c: Char) = c.isLetter() || c == '_'
//        fun isIdentPart(c: Char) = c.isLetterOrDigit() || c == '_'
//
//        while (i < sql.length) {
//            val c = sql[i]
//
//            // comments
//            if (inLineComment) {
//                if (c == '\n') inLineComment = false
//                i++
//                continue
//            }
//            if (inBlockComment) {
//                if (c == '*' && i + 1 < sql.length && sql[i + 1] == '/') {
//                    inBlockComment = false
//                    i += 2
//                    continue
//                }
//                i++
//                continue
//            }
//
//            // strings/quotes
//            if (inSingle) {
//                if (c == '\'') {
//                    // SQL escape: '' inside single-quoted string
//                    if (i + 1 < sql.length && sql[i + 1] == '\'') {
//                        i += 2
//                        continue
//                    }
//                    inSingle = false
//                }
//                i++
//                continue
//            }
//            if (inDouble) {
//                if (c == '"') inDouble = false
//                i++
//                continue
//            }
//            if (inBacktick) {
//                if (c == '`') inBacktick = false
//                i++
//                continue
//            }
//
//            // start comments?
//            if (c == '-' && i + 1 < sql.length && sql[i + 1] == '-') {
//                inLineComment = true
//                i += 2
//                continue
//            }
//            if (c == '#') {
//                inLineComment = true
//                i++
//                continue
//            }
//            if (c == '/' && i + 1 < sql.length && sql[i + 1] == '*') {
//                inBlockComment = true
//                i += 2
//                continue
//            }
//
//            // start quotes?
//            if (c == '\'') { inSingle = true; i++; continue }
//            if (c == '"') { inDouble = true; i++; continue }
//            if (c == '`') { inBacktick = true; i++; continue }
//
//            // positional
//            if (c == '?') {
//                positional++
//                i++
//                continue
//            }
//
//            if (c == ':') {
//                val prev = if (i > 0) sql[i - 1] else '\u0000'
//                if (prev == ':') { i++; continue }
//
//                val start = i + 1
//                if (start < sql.length && isIdentStart(sql[start])) {
//                    var j = start + 1
//                    while (j < sql.length && isIdentPart(sql[j])) j++
//                    named += sql.substring(start, j)
//                    i = j
//                    continue
//                }
//            }
//
//            i++
//        }
//
//        return ParsedParams(named, positional)
//    }
//
//    fun statement(
//        sql: String,
//        named: Map<String, Any?> = emptyMap(),
//        positional: List<Any?> = emptyList()
//    ): Statement {
//        val parsed = parseParams(sql)
//
//        val missing = parsed.named - named.keys
//        require(missing.isEmpty()) { "Missing named params: $missing in SQL: $sql" }
//
//        require(positional.size == parsed.positionalCount) {
//            "Expected ${parsed.positionalCount} positional params, got ${positional.size} in SQL: $sql"
//        }
//
//        var st = Statement.create(sql)
//        positional.forEachIndexed { idx, v -> st = st.bind(idx, v) }
//        named.forEach { (k, v) -> st = st.bind(k, v) }
//        return st
//    }
//
//    suspend fun execute(
//        db: QueryExecutor,
//        sql: String,
//        named: Map<String, Any?> = emptyMap(),
//        positional: List<Any?> = emptyList()
//    ): Result<Long> =
//        db.execute(statement(sql, named, positional))
//
//    suspend fun fetchAll(
//        db: QueryExecutor,
//        sql: String,
//        named: Map<String, Any?> = emptyMap(),
//        positional: List<Any?> = emptyList()
//    ): Result<ResultSet> =
//        db.fetchAll(statement(sql, named, positional))
//}
