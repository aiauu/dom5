import java.util.*;

class ConfigurationManager {
    private static ConfigurationManager instance;
    private Map<String,String> settings = new HashMap<>();
    private ConfigurationManager() {}
    public static synchronized ConfigurationManager getInstance() {
        if(instance==null) instance=new ConfigurationManager();
        return instance;
    }
    public void set(String key,String val){ settings.put(key,val); }
    public String get(String key){ return settings.getOrDefault(key,"Not Found"); }
}

class Report {
    String header,content,footer;
    void show(){ System.out.println(header+"\n"+content+"\n"+footer+"\n"); }
}
interface IReportBuilder {
    void setHeader(String h);
    void setContent(String c);
    void setFooter(String f);
    Report getReport();
}
class TextReportBuilder implements IReportBuilder {
    Report r=new Report();
    public void setHeader(String h){ r.header=h; }
    public void setContent(String c){ r.content=c; }
    public void setFooter(String f){ r.footer=f; }
    public Report getReport(){ return r; }
}

class Product implements Cloneable {
    String name; double price;
    public Product clone(){ try{return (Product)super.clone();}catch(Exception e){return null;} }
}
class Order implements Cloneable {
    List<Product> products=new ArrayList<>();
    public Order clone(){
        try{
            Order copy=(Order)super.clone();
            copy.products=new ArrayList<>();
            for(Product p:products) copy.products.add(p.clone());
            return copy;
        }catch(Exception e){return null;}
    }
    void show(){ for(Product p:products) System.out.println(p.name+" - "+p.price); System.out.println("----"); }
}


public class дом5 {
    public static void main(String[] args){
        ConfigurationManager cfg1=ConfigurationManager.getInstance();
        ConfigurationManager cfg2=ConfigurationManager.getInstance();
        cfg1.set("Theme","Dark");
        System.out.println("Singleton same instance? "+(cfg1==cfg2));
        System.out.println("Theme: "+cfg2.get("Theme"));

        IReportBuilder builder=new TextReportBuilder();
        builder.setHeader("Header");
        builder.setContent("Content");
        builder.setFooter("Footer");
        builder.getReport().show();

        Order o1=new Order();
        Product p1=new Product(); p1.name="Phone"; p1.price=500; o1.products.add(p1);
        Order o2=o1.clone();
        o2.products.get(0).name="Laptop"; o2.products.get(0).price=1000;

        o1.show();
        o2.show();
    }
}
