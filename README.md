# SegreteriaUniNoi

Lo scopo dell’applicazione realizzata è quello di presentare una piattaforma che permetta le comunicazioni fra studenti e il personale della segreteria di un’eventuale Università. È stato utilizzato il linguaggio di programmazione Java per poter sfruttare al meglio il paradigma della programmazione a oggetti, che ha agevolato la realizzazione dell’intera applicazione.

## Programmazione a oggetti

Come accennato, nella realizzazione sono stati utilizzati alcune caratteristiche della programmazione a oggetti, a partire dalla creazione di classi e relative figlie e metodi, l’overloading (ovvero la possibilità di instanziare un oggetto potendo variare il numero di parametri d’ingresso), l’override (ovvero la possibilità di modificare il comportamento di un metodo a partire da uno base) e l’interfacciamento con Maven. 
Nella nostra applicazione, l'ereditarietà è stata implementata, fra gli altri esempi, facendo ereditare dalla classe padre "Persona" le classi "Studente" e "Membro".
~~~ java
public class persona {
    private String nome;
    private String cognome;
    public studente(String nome, String cognome, int matricola, String password) {
        super(nome, cognome);
        this.matricola=matricola;
        this.password=password;
    }
    public studente(String nome, String cognome, String aa, int matricola) {
        super(nome, cognome);
        this.matricola=matricola;
        this.aa=aa;
    }
    
    ...
    
    public String getDati(){
        String res=("LT: Nome: "+ this.getNome() + "  Cognome: "+ this.getCognome());
        return res;
    }
~~~

~~~ java
public class studente extends persona{
    private int matricola;
    private String password;
    private String aa;
    
    ...
    
    @Override
    public String getDati(){
        String res=(super.getDati()+"   Matricola: "+ this.getMatricola() + "  Anno d'iscrizione: "+ this.getAa());
        return res;
    }
~~~
~~~ java
public class membro extends persona{
     private int codice;
    private String mansione;
    public membro (String nome, String cognome, int codice, String mansione){
        super(nome, cognome);
        this.codice=codice;
        this.mansione=mansione;
    }
    public membro (String nome, String cognome, int codice){
        super(nome, cognome);
        this.codice=codice;
    }
    ...
    
     @Override
    public String getDati(){
        String res=(super.getDati()+"   Codice: "+ this.codice);
        return res;
    }
~~~

Negli snippet di codice sopra, vengono implementati anche Overloading, Override e Polimorfismo. Queste utlime due caratteristiche vengono soddisfatte dal metodo getDati: nel codice sorgente dell'applicazione, in particolare nella procedura membro, all'immissione del comando "Dati" viene caricato un array di tipo Persona con i risultati della query SQL e, successivamente, vengono stampate le informazioni sia degli studenti, che dei membri della segreteria. Il fatto che venga definito un array con la classe padre permette di implementare il polimorfismo: infatti viene sempre richiamato il metodo getDati() e, senza specificare il tipo dell'oggetto processato in quell'istante, è in grado di stampare le informazioni relative a quello studente o a quel membro.


## Maven

La possibilità più importante della programmazione a oggetti è sicuramente quella di sfruttare codice altrui per non dover realizzare nuovamente una o più funzioni. L’utilizzo del repository Maven per Java ha facilitato ulteriormente quest’operazione, in quanto non è più necessario, come in passato, dover reperire il file .jar e installarlo ma basta inserire una “dependency” nel file di configurazione denominato “pom”. Una volta inserite tutte le dipendenze necessarie, è sufficiente far partire la routine di Maven che una volta terminata fornirà un package, nel quale saranno presenti tutti i metodi richiesti.

## Database

Per conservare sia i dati d’accesso degli utenti, sia le richieste effettuate e le relative risposte, si è deciso di utilizzare un semplice database relazionale, di tipo MySQL o MariaDB. L’interfacciamento con Java è stato reso agevole dal driver JDBC, messo a disposizione da Oracle sul repository ufficiale di Maven. Nello specifico, si è instanziato un database in locale, in maniera da facilitare e velocizzare le operazioni. Viene anche utilizzato un file di configurazione XML dove sono stati inseriti i parametri per potersi interfacciare al Database. Ciò è stato fatto per poter utilizzare un qualsiasi Database (e di conseguenza cambiarlo) senza dover modificare il codice Java, per una maggiore sicurezza.
Di seguito il codice usato per la connessione al Db ed un esempio di query.
~~~ java

public class dbconnection {

    public static Statement connettiti() throws Exception{
    String[] conf;
    conf=new String[5];
    conf=xmlHandler.getDbConfiguration();
    String address=conf[0].trim();
    String portS=conf[1].trim();
    int port = Integer.parseInt(portS);
    String dbname=conf[2].trim();
    String user=conf[3].trim();
    String pass=conf[4].trim();
    Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection conn = DriverManager.getConnection("jdbc:mysql://"+address+":"+port+"/"+dbname+"", ""+user+"", ""+pass+"");
                        Statement st = conn.createStatement();
                        return st;
                    }
    
}

~~~

~~~ java

...

try {
            LocalDate data = LocalDate.now();
            Statement stt = dbconnection.connettiti();
            stt.executeUpdate("INSERT INTO Richieste (richiesta, matricola, codice, giorno) " +
            "VALUES ('" + richiesta + "', '" + mat + "', '"+codice+" ', '" + data + "')");
            
            pw.println("NO: Richiesta effettuata correttamente");
            pw.flush();
                                                                        
            } catch (Exception ee) {
            ee.printStackTrace();
            pw.println("C'è stato un problema di connessione, riavviare");
}
            
...

~~~

## XML

Anche per quanto riguarda l’utilizzo di XML si è importata la dependency JDOM, anch’essa presente sul repository Maven. Si è preferito utilizzare JDOM rispetto a DOM e SAX perché esso rappresenta un’evoluzione rispetto agli ultimi due, prendendo il meglio da entrambi per quanto riguarda parsing e ricerca.
Di seguito, un esempio su come vengono prelevati i dati dal file di configurazione del Database:

~~~ java
 public static String[] getDbConfiguration() throws JDOMException, IOException{
        SAXBuilder sax = new SAXBuilder();
        sax.setProperty(XMLConstants.ACCESS_EXTERNAL_DTD, "");
        sax.setProperty(XMLConstants.ACCESS_EXTERNAL_SCHEMA, "");
        Document doc = sax.build(new File("src/main/databaseConfiguration.xml"));

        Element rootNode = doc.getRootElement();
        List<Element> list = rootNode.getChildren("infos");
        String[] res;
        res= new String[5];
        for (Element target : list) {
            String address = target.getChildText("address");
            String port = target.getChildText("port");
            String dbname = target.getChildText("databasename");
            String user = target.getChildText("username");
            String pass = target.getChildText("password");
            res[0]=address;
            res[1]=port;
            res[2]=dbname;
            res[3]=user;
            res[4]=pass;
    }
    return res;
    }
~~~

## Client-Server

Naturalmente il funzionamento dell’applicazione è dipendente da un’architettura client server, dove il Server è la macchina dove giace il Db e che realizza le query, mentre il client può essere una qualsiasi altra macchina tramite la quale lo studente o il membro della segreteria può interagire. Per realizzare ciò, sono stati utilizzati i metodi built-in di java ServerSocket. Viene dunque stabilita una connessione TCP al Server. Per facilità di realizzazione, nel test dell’applicazione il Server e il Client erano la stessa macchina: nel codice, infatti, viene stabilita una connessione all’indirizzo di loopback su due porte differenti. In un utilizzo reale dell’applicazione, è sufficiente modificare l’indirizzo IP al quale connettersi e la porta alla quale connettersi direttamente dal file di configurazione XML.
Di importanza fondamentale è prevedere una corretta routine di funzionamento del Client rispetto alle azioni del Server: perché l'app funzioni a dovere, è necessario che ad ogni input del Client corrisponda l'attesa di un input da parte del Server, e così per ogni tipo di interazione.
A seguire un breve esempio della routine di funzionamento del Client:
~~~ java
public class Client {
    private Socket s;
    public Client (String ip, int porta) throws IOException{
        s =new Socket(ip, porta);
        System.out.println(("Connesso"));
    }

public void inizio() throws Exception{
    PrintStream ps;
    BufferedReader br;
    Scanner sc = new Scanner(System.in);
    String msg="null";
    String comando;
    boolean flag= true;
    ps = new PrintStream(s.getOutputStream(),true);
    br = new BufferedReader(new InputStreamReader(s.getInputStream()));
    while(flag){
        msg=null;
            msg=br.readLine();
            ...
            
            else if(msg.startsWith("LT: ")){
               msg=msg.substring(4);
                System.out.println(msg);}
        }
    ps.close();
    br.close();
    s.close();

 }
}
~~~

## Multithreading

Per poter permettere a più utenti di usufruite contemporaneamente del servizio, è stato necessario implementare il multithreading. Ogni volta che un nuovo utente si collega, viene creato un thread sulla porta adibita, in maniera che ogni esecuzione sia totalmente indipendente dall’altra per garantire la privacy e il corretto funzionamento. Nell’app ciò è stato realizzato mediante l’interfaccia Runnable. Sarebbe stato possibile estendere la classe Thread ma si è preferito il primo approccio poiché, in futuro, potrebbe essere necessario estendere un’altra classe (Java, infatti, non permette che una classe sia estensione di più classi).
A seguire, una porzione di codice di esempio su come è stato implementato il multithreading:

### Server

~~~ java
public class Server {
    public static void main(String[] args) throws Exception {
        
        
        ...
            
            Thread proceduram = new Thread(new proceduraMembro(6869));
            proceduram.start();
        }
    }
~~~

### Procedura membro
~~~ java
public class proceduraMembro implements Runnable{
    ...
        public void run(){
          
           PrintWriter pw;
            BufferedReader br;
            Socket s;
            boolean flag;
            while(true){
            System.out.println("\nIn attesa di connessioni, servizio Membro\n");
            try{
            s = ss.accept();
            flag = true;
            System.out.println("\nMembro Connesso\n");
            pw = new PrintWriter(s.getOutputStream(), true);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            ...
            
            }
      }
~~~
Il metodo start() genera un thread e inizia la sua normale procedura di esecuzione, avviando la porzione di codice all'interno del metodo run(), rispettando tutte le fasi di un processo (Create, Ready, Wait, Run, Terminated). In teoria è possibile avviare il codice all'interno di run() direttamente, ma è altamente sconsigliato.

## UML
Al fine di dare una panoramica visiva di tutti le classi, i metodi e le dipendenze che costituiscono l’applicazione si è realizzata una rappresentazione in UML mostrata in seguito:

- Java classes diagram
![mysqlconnector5](https://user-images.githubusercontent.com/95317308/188880391-b6c7a1d6-069a-42d6-a578-e206bcc65946.png)

- Project Modules
![MySQLConnector2](https://user-images.githubusercontent.com/95317308/188880353-52dcd213-cf72-4a26-a007-b47476b0c4cb.png)

## Utilizzo

Chiunque si voglia connettere dovrà, per prima cosa, effettuare il login come studente o come membro della segreteria: a seconda della scelta, verrà aperta una connessione sull'indirizzo IP della macchina server su una delle due porte adibite, differenti a seconda del tipo di servizio scelto. Una volta stabilita la connessione, verrà generato un thread che rappresenta l'applicazione vera e propria. Dopo aver immesso le credenziali, lo studente dovrà decidere in quale sezione entrare fra Attribuzione CFU, Materie a scelta, Rosetta Stone e Altre richieste o, in alternativa, visualizzare i suoi dati. Una volta scelta la sezione, potrà decidere se inoltrare una nuova richiesta o visualizzare quelle che ha già immesso ed, eventualmente, verificare se esse hanno avuto risposta. Lo studente sarà anche in grado di leggere la risposta ricevuta e anche il nominativo del membro della segreteria che si è occupato della sua pratica. Per quanto riguarda il funzionamento per un membro della segreteria abbiamo che esso, successivamente al log-in, sarà in grado di vedere tutte le richieste "In Attesa", ovvero per la quale non vi è stata data ancora nessuna risposta. Dopo aver immesso l'ID della pratica di cui si vuole occupare, potrà decidere se rispondere affermativamente, negativamente o chiedendo un'integrazione. In ogni caso, sarà necessario allegare una giustificazione. Il membro della segreteria, digitando "Dati", potrà controllare nominativo ed ID di tutti i membri presenti sul Database. Una volta che l'utente ha concluso le sue operazioni, viene chiusa la connessione. Come detto, avendo un'implementazione multithreading si è in grado di gestire più di un utente alla volta. Una possibile miglioria al servizio potrebbe essere l'interfacciamento ad un DB NoSQL, in maniera da essere certi poter sopportare un numero anche maggiore di utenti contemporanamente e la realizzazione di un'interfaccia utente, al fine di rendere ancora più facilmente fruibile l'esperienza d'uso.
