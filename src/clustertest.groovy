@GrabConfig(systemClassLoader=true)
@Grab(group='org.postgresql', module='postgresql', version='9.4-1205-jdbc42')

import groovy.sql.Sql

def dbUrl      = "jdbc:postgresql://localhost/postgres"
def dbUser     = "postgres"
def dbPassword = "admin@@090"
def dbDriver   = "org.postgresql.Driver"

def sql = Sql.newInstance(dbUrl, dbUser, dbPassword, dbDriver)

def dropTableIfExists = """ DROP TABLE IF EXISTS article """
def createTableScript = """
        CREATE TABLE article (
            article_id bigserial primary key,
            article_name varchar(20) NOT NULL,
            article_desc text NOT NULL,
            date_added timestamp default NULL
        );"""

sql.execute(dropTableIfExists)
sql.execute(createTableScript)

def nTrans = 10000

def start = new Date()
println start.format("YYYYMMdd-HH:mm:ss") 

def qry = 'INSERT INTO article (article_name, article_desc) VALUES (?,?)'

sql.withBatch(1000, qry) { ps ->
    nTrans.times {
        ps.addBatch('article_name', 'article_description')
    }      
}


sql.close()

def end = new Date()
println end.format("YYYYMMdd-HH:mm:ss") 
