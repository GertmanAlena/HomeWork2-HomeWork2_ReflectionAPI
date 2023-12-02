package task3Seminar2;

import java.lang.reflect.Field;
import java.util.UUID;

public class QueryBuilder {

    public String buildInsertQuery(Object obj) throws IllegalAccessException {

        Class<?> clazz = obj.getClass();    //берём описание класса входящего объекта
        StringBuilder query = new StringBuilder("INSERT INTO ");

        // содержится ли аннотация в этом типе
        if(clazz.isAnnotationPresent(Table.class)){
            Table tableAnnotation = clazz.getAnnotation(Table.class); //получаем саму аннотацию

            //наименование таблицы + пробел в запросе
            query
                    .append(tableAnnotation.name())
                    .append(" (");

            Field[] fields = clazz.getDeclaredFields();
            // проход по всем колонкам
            for(Field field : fields){
                if(field.isAnnotationPresent(Column.class)){
                    // если доступна аннотация Column
                    Column columnAnnotation = field.getAnnotation(Column.class);    //наименование колонки
                    query.append(columnAnnotation.name()).append(", ");
                }
            }
            //удалим последнюю запятую и пробел
            if (query.charAt(query.length() - 2) == ',') {
                query.delete(query.length() - 2, query.length());
            }

            query.append(") VALUE (");

            //добавим значения в запрос
            for(Field field : fields){
                if(field.isAnnotationPresent(Column.class)){
                    field.setAccessible(true);
                    query.append("'").append(field.get(obj)).append("', ");
                }
            }
            //удалим последнюю запятую и пробел
            if (query.charAt(query.length() - 2) == ',') {
                query.delete(query.length() - 2, query.length());
            }
            query.append(")");
            return query.toString();
        }
        else {
            return null;
        }

    }

    public String buildSelectQuery(Class<?> clazz, UUID primaryKey){
        StringBuilder query = new StringBuilder("SELECT * FROM ");

        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query.append(tableAnnotation.name()).append(" WHERE ");
            // SELECT * FROM tableAnnotation.name()-название таблицы WHERE
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation.primaryKey()) {
                    query.append(columnAnnotation.name()).append(" = ").append(primaryKey);
                    break;
                }
            }
        }
        return query.toString();
    }

    public String buildUpdateQuery(Object obj) throws IllegalAccessException {
        // UPDATE table_name SET ContactName = 'Alfred Schmidt', City= 'Frankfurt' WHERE CustomerID = 1;
        Class<?> clazz = obj.getClass();
        StringBuilder query = new StringBuilder("UPDATE ");

        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class); //берём наименование таблицы
            query.append(tableAnnotation.name()).append(" SET ");

            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    // если попадает первичный ключ -> выходим
                    if (columnAnnotation.primaryKey())
                        continue;
                    query.append(columnAnnotation.name()).append(" = '").append(field.get(obj)).append("', ");
                }
            }

            if (query.charAt(query.length() - 2) == ',') {
                query.delete(query.length() - 2, query.length());
            }

            query.append(" WHERE ");

            for (Field field : fields) {
                if (field.isAnnotationPresent(Column.class)) {
                    field.setAccessible(true);
                    Column columnAnnotation = field.getAnnotation(Column.class);
                    if (columnAnnotation.primaryKey()) {
                        query.append(columnAnnotation.name()).append(" = '").append(field.get(obj)).append("'");
                        break;
                    }

                }
            }

            return query.toString();
        }
        else {
            return null;
        }

    }

    /**
     * TODO: Доработать в ДЗ
     * DELETE FROM <Table> WHERE ID = '<id>'
     * @param clazz
     * @param primaryKey
     * @return
     */
    public String buildDeleteQuery(Class<?> clazz, UUID primaryKey){

        StringBuilder query = new StringBuilder("DELETE FROM ");

        if (clazz.isAnnotationPresent(Table.class)) {
            Table tableAnnotation = clazz.getAnnotation(Table.class);
            query.append(tableAnnotation.name()).append(" WHERE ");
            // DELETE FROM tableAnnotation.name()-название таблицы WHERE
        }

        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(Column.class)) {
                Column columnAnnotation = field.getAnnotation(Column.class);
                if (columnAnnotation.primaryKey()) {
                    query.append(columnAnnotation.name()).append(" = '").append(primaryKey).append("'");
                    break;
                }
            }
        }
        return query.toString();

    }


}
