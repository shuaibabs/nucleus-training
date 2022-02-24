import java.util.*;
import java.lang.*;
import java.io.*;

abstract class Customer
{
    abstract void setCustomer(String Name);
    abstract void setCustomer(int Phone);
    abstract void setCustomer(String Name,int Phone);
    abstract void setCustomerData();
    abstract void showCustomerData();
}

class Application extends Customer
{
    private int cusAppId;
    private String appName;
    private int appNo;
    private Scanner sc= new Scanner(System.in);

    private String cusName;
    private int cusId;
    private int cusSalary;
    private String cusEmail;
    private String cusAddr;
    private int cusMobile;
    private int age;

    void setCustomer(String Name)
    {
        this.cusName=Name;
    }
    void setCustomer(int Phone)
    {
        this.cusMobile = Phone;
    }
    void setCustomer(String Name,int Phone)
    {
        this.cusName=Name;
        this.cusMobile = Phone;
    }
    void setCustomerData()
    {
        System.out.println("Set Customer data: ");

        System.out.println("Enter Customer Name: ");
        this.cusName=sc.nextLine();

        System.out.println("Enter Customer Id: ");
        this.cusId=sc.nextInt();

        System.out.println("Enter Customer Salary : ");
        this.cusSalary=sc.nextInt();

        System.out.println("Enter Customer Email : ");
        this.cusEmail=sc.next();

        System.out.println("Enter Customer Address: ");
        this.cusAddr=sc.next();

        System.out.println("Enter Customer Mobile : ");
        this.cusMobile=sc.nextInt();

        System.out.println("Enter Customer Age : ");
        this.age=sc.nextInt();
    }

    void showCustomerData()
    {
        System.out.println(this.cusName);
        System.out.println(this.cusId);
        System.out.println(this.cusSalary);
        System.out.println(this.cusEmail);
        System.out.println(this.cusAddr);
        System.out.println(this.cusMobile);
        System.out.println(this.age);
    }
    boolean isLoanApproved()
    {
        if(this.age>18 && this.cusSalary >35000)
            return true;
        else
            return false;
    }
    synchronized void LoanApproved()
    {
        System.out.println("Check for Loan Approval...");
        if(!isLoanApproved())
        {
            System.out.println("Waiting for Loan Approval...");
            try{wait();}catch(Exception e){System.out.println("Exception: " + e);}

        }else{
            System.out.println("Loan Approved...");
            notify();
        }
        System.out.println("Loan Approved...");
    }
    synchronized void makeLoanApproved()
    {
        if(this.cusSalary < 35000)
        {
            System.out.println("Update Salary : ");
            this.cusSalary=sc.nextInt();
            notify();

        }
        else if(this.age < 18) {
            System.out.println("Update Age : ");
            this.age = sc.nextInt();
            notify();
        }
    }
    public void setApplicatonData(){

        System.out.println("application Set Data :");

        System.out.println("enter Customer app id :" );
        cusAppId= sc.nextInt();
        System.out.println("enter application Type :" );
        appName=sc.nextLine();
        System.out.println("enter application  Number :" );
        appNo= sc.nextInt();
    }
    void showApplication()
    {
        System.out.println("application Set Data :");
        System.out.println("enter application Type :" );
        System.out.println("application  Number :" + appNo );
    }
}

abstract class Loan extends Customer
{
    private String loanType;
    private int loanId;
    private int loanCusId;
    Scanner sc= new Scanner(System.in);

    public void setLoanData()
    {
        System.out.println("ENTER LOAN TYPE :");
        loanType= sc.nextLine();
        System.out.println("ENTER LOAN ID :");
        loanId= sc.nextInt();
        System.out.println("ENTER LOAN COUSTMER ID :");
        loanCusId=sc.nextInt();
    }

    public void showLoanData()
    {
        System.out.println("loan Type : "+loanType);
        System.out.println("loan ID : "+loanId);
        System.out.println("loan Customer ID : "+loanCusId);
    }

}

abstract class Bank {
    abstract public void setBankData();
    abstract public void showBankData();
}
class Approval extends Bank implements Limits
{
    private boolean check;
    private int app_approval_id;
    private Scanner sc= new Scanner(System.in);

    private String bank_name;
    private int bank_id;
    private String branch;
    private int bank_cus_id;

    int l_bal;
    int l_cus_id;

    public void setBankData() {
        System.out.println("bank_id");
        this.bank_id = sc.nextInt();

        System.out.println("bank_cus_id");
        this.bank_cus_id = sc.nextInt();

        System.out.println("bank_name");
        this.bank_name = sc.nextLine();

        System.out.println("branch");
        this.branch = sc.nextLine();
    }
    public void showBankData() {
        System.out.println(this.bank_id);
        System.out.println(this.bank_cus_id);
        System.out.println(this.bank_name);
        System.out.println(this.branch);
    }

    void setApprovalData(){
        System.out.println("ENTER APPLICATION ID : ");
        app_approval_id = sc.nextInt();
        check = false;
    }

    void showApprovalData(){
        System.out.println("Your application approval ID : " + app_approval_id);
        System.out.println("your loan approval status is : " + check);
    }


    public void setLimitData()
    {
        System.out.println("ENTER LIMIT MAXIMUM BALANCE : ");
        l_bal=sc.nextInt();
        System.out.println("COUSTMER ID : ");
        l_cus_id=sc.nextInt();

    }

    public void showLimitData(){
        System.out.println("LIMIT BALANCE IS : "+l_bal);
        System.out.println("LIMIT CUSTOMER ID IS : "+l_cus_id);

    }
}
interface Limits
{
    void setLimitData();
    void showLimitData();
}

public class LMS
{
    public static void main (String[] args) throws java.lang.Exception
    {
        Application appObj = new Application();

        appObj.setCustomerData();
        appObj.showCustomerData();


        new Thread(){
            public void run(){appObj.LoanApproved();}
        }.start();

        new Thread(){
            public void run(){appObj.makeLoanApproved();}
        }.start();
    }
}
