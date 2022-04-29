package netcracker.danilavlebedev.db;

public class DBQueries {
    public static String getContractsCount() {
        return "select count(c.*) from contracts c;";
    }

    public static String getContractsCountByPersonsId() {
        return "select count(c.*) from contracts c where c.person_id = ?;";
    }

    public static String getContracts() {
        return "select d.* from (select row_number() over (order by c.id) as rownum, c.* from contracts c) d where rownum between ? and ?;";
    }

    public static String getContract() {
        return "select d.* from (select row_number() over (order by c.id) as rownum, c.* from contracts c) d where rownum = ?;";
    }

    public static String getContractById () {
        return "select c.* from contracts c where c.id = ?;";
    }

    public static String addContract() {
        return "insert into contracts (id, date_start, date_end, number, person_id, type, properties) values (?, to_date(?, 'YYYY MM DD'), to_date(?, 'YYYY MM DD'), ?, ?, ?, ?);";
    }

    public static String deleteContract() {
        return "delete from contracts c where c.id = ?;";
    }

    public static String getPerson() {
        return "select p.* from people p where p.id = ?;";
    }

    public static String addPerson() {
        return "insert into people (id, full_name, date_of_birth, sex, id_series_number) values (?, ?, to_date(?, 'YYYY MM DD'), ?, ?);";
    }

    public static String deletePerson() {
        return "delete from people p where p.id = ?;";
    }
}
