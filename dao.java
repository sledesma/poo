package Aplicacion;

import java.util.ArrayList;

public class Programa {

  public static void main(String[] args) {

    ArrayList listaAlumnos = new ArrayList();

    listaAlumnos.add("Luciano");

    DbConnection conn = new MySQL();
    // Reflect-Metadata
    Adapter<Persona> adapter = new Adapter<Persona>(conn);
    Adapter<Producto> adapter = new Adapter<Producto>(conn);
  }

}

public class Elemento {
  public Dictionary<String, String> metadatos;

  public Elemento() {
    this.metadatos = new Dictionary<String, String>();
  }
}

public class Entidad extends Elemento {}

public class Persona extends Entidad {
  public String nombre;
  public String apellido;
}

public class MySQL extends DbConnection {
  protected String connectionString = "mysql://sledesma,1234@localhost:3556/tienda";
  
  @Override
  public String get(String esquema) {
    return "Obteniendo todos los datos de la tabla "+esquema+" de MySQL";
  }
}

public class PostgreSQL extends DbConnection {
  protected String connectionString = "pgsql://";

  @Override
  public String get(String esquema) {
    return "Obteniendo todos los datos de la tabla "+esquema+" de PostgreSQL";
  }
}

public abstract class DbConnection extends Elemento {
  protected String connectionString = "";

  public String get(String esquema) {}
  public String get(String esquema, String filtro) {}
}

public class Adapter extends Elemento {}

public class PersonaAdapter extends Adapter {

  private DbConnection db;

  public PersonaMysqlAdapter(DbConnection db) {
    this.db = db;
  }

  public Persona[] getAll() {
    this.db.getAll('persona');
  }

}
/*
public class Adapter<T> extends Elemento {

  private DbConnection db;

  public PersonaMysqlAdapter(DbConnection db) {
    this.db = db;
  }

  public T[] getAll() {
    this.db.getAll('persona');
  }

}
*/
