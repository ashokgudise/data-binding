package com.ashok.demos;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.comparators.ComparatorChain;
import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.lang.reflect.Array;
import java.util.*;

public class App {

    public static void main(String... args) {

        App app = new App();

        //Data Initialization
        List<Contract> contracts = app.populateData();

        //Java 7 Approach...
        
        //Set to Check Duplicates
        Set<Address> addressCheck = new HashSet<>();
        //List to Return as Output
        List<Address> outputAddressList = new ArrayList<>();

        for (Contract eachContract : contracts) {
            for (Owner eachOwner : eachContract.getOwners()) {
                for (Address eachAddress : eachOwner.getAddressList()) {
                    if (addressCheck.add(eachAddress)) {
                        //New Address
                        if (CollectionUtils.isEmpty(eachAddress.getContractIdList())) {
                            eachAddress.setContractIdList(new ArrayList<>());
                        }
                        eachAddress.getContractIdList().add(eachContract.getContractId()+" of "+eachOwner.getOwnerName());
                        outputAddressList.add(eachAddress);
                    } else {
                        //Existing Address
                        outputAddressList.get(outputAddressList.indexOf(eachAddress)).getContractIdList().add(eachContract.getContractId()+" of "+eachOwner.getOwnerName());
                    }
                }
            }
        }


        //Output:
        for (Address processedAddress : outputAddressList) {
            System.out.println("Address Type: \t:" + processedAddress.getAddressType());
            System.out.println("Address FirstLine: \t:" + processedAddress.getAddressLine1());
            System.out.println("Address SecondLine: \t:" + processedAddress.getAddressLine2());

            System.out.println("Contracts :\t");

            processedAddress.getContractIdList().stream().forEach(System.out::println);

            System.out.println("-----");
        }

    }


    public List<Contract> populateData(){

        Address officeAddress = new Address();

        officeAddress.setAddressLine1("Suite 200");
        officeAddress.setAddressLine2("8520 Cliff Cameron Dr");
        officeAddress.setAddressType("Office Address");
        officeAddress.setCountry("US");
        officeAddress.setZipCode("12345");
        officeAddress.setState("AP");

        Address uptown = new Address();

        uptown.setAddressLine1("Suite 700");
        uptown.setAddressLine2("3 Ave Uptown Dr");
        uptown.setAddressType("Office Address");
        uptown.setCountry("US");
        uptown.setZipCode("145345");
        uptown.setState("NY");

        Address harrsbrg = new Address();

        harrsbrg.setAddressLine1("3542 Belmont VW");
        harrsbrg.setAddressLine2("Leonardo Ln");
        harrsbrg.setAddressType("Residential Address");
        harrsbrg.setState("TG");
        harrsbrg.setZipCode("53300");
        harrsbrg.setCountry("US");

        Address rajbhogLn = new Address();

        rajbhogLn.setAddressLine1("7325 Smita St");
        rajbhogLn.setAddressLine2("Vivekanand Colony");
        rajbhogLn.setAddressType("Residential Address");
        rajbhogLn.setState("MP");
        rajbhogLn.setZipCode("54500");
        rajbhogLn.setCountry("US");

        Address southAddress = new Address();

        southAddress.setAddressLine1("8989 South St");
        southAddress.setAddressLine2("Melkot Ln");
        southAddress.setAddressType("Residential Address");
        southAddress.setState("SC");
        southAddress.setZipCode("99500");
        southAddress.setCountry("US");

        Contract sannai_Contract = new Contract();
        sannai_Contract.setContractId("1324");
        sannai_Contract.setOwners(new ArrayList<>());

        Owner santo = new Owner();
        santo.setOwnerName("Santo - The Walk Man");
        santo.setAddressList(new ArrayList<>());
        santo.getAddressList().addAll(Arrays.asList(rajbhogLn,officeAddress));

        Owner kunfupanda = new Owner();
        kunfupanda.setAddressList(new ArrayList<>());
        kunfupanda.getAddressList().addAll(Arrays.asList(southAddress,officeAddress));
        kunfupanda.setOwnerName("Kunfu Panda");

        //Set Contracts -1
        sannai_Contract.getOwners().add(santo);
        sannai_Contract.getOwners().add(kunfupanda);

        Contract tt_Contract = new Contract();
        tt_Contract.setContractId("5232");
        Owner jrRajamouly = new Owner();
        jrRajamouly.setAddressList(new ArrayList<>());
        jrRajamouly.getAddressList().addAll(Arrays.asList(rajbhogLn,officeAddress));
        jrRajamouly.setOwnerName("Bahubali 3");

        Owner ashok = new Owner();
        ashok.setAddressList(new ArrayList<>());
        ashok.getAddressList().addAll(Arrays.asList(southAddress,officeAddress));
        ashok.setOwnerName("AG");

        Owner arjunReddy= new Owner();
        arjunReddy.setAddressList(new ArrayList<>());
        arjunReddy.getAddressList().addAll(Arrays.asList(harrsbrg,officeAddress));
        arjunReddy.setOwnerName("Arjun Reddy");
        //Set Contracts -2
        tt_Contract.setOwners(new ArrayList<>());
        tt_Contract.getOwners().addAll(Arrays.asList(jrRajamouly,ashok,arjunReddy));

        Contract bbt_Contract = new Contract();
        bbt_Contract.setContractId("2948");
        Owner kabali = new Owner();
        kabali.setAddressList(new ArrayList<>());
        kabali.setOwnerName("Kabali");
        kabali.getAddressList().addAll(Arrays.asList(harrsbrg,uptown));

        Owner gabbar = new Owner();
        gabbar.setAddressList(new ArrayList<>());
        gabbar.setOwnerName("Gabbar");
        gabbar.getAddressList().addAll(Arrays.asList(harrsbrg,uptown));

        //Set Contracts -3
        bbt_Contract.setOwners(new ArrayList<>());
        bbt_Contract.getOwners().addAll(Arrays.asList(kabali,gabbar));

        return new ArrayList<>(Arrays.asList(sannai_Contract,tt_Contract,bbt_Contract));

    }


    class Contract{

        private String contractId;
        private List<Owner> owners;

        public String getContractId() {
            return contractId;
        }

        public void setContractId(String contractId) {
            this.contractId = contractId;
        }

        public List<Owner> getOwners() {
            return owners;
        }

        public void setOwners(List<Owner> owners) {
            this.owners = owners;
        }
    }

    class Owner{
        private String ownerName;
        private List<Address> addressList;

        public String getOwnerName() {
            return ownerName;
        }

        public void setOwnerName(String ownerName) {
            this.ownerName = ownerName;
        }

        public List<Address> getAddressList() {
            return addressList;
        }

        public void setAddressList(List<Address> addressList) {
            this.addressList = addressList;
        }
    }

    class Address implements Comparable{

        private String addressLine1;
        private String addressLine2;
        private String addressType;
        private String zipCode;
        private String state;
        private String country;
        private List<String> contractIdList;


        public List<String> getContractIdList() {
            return contractIdList;
        }

        public void setContractIdList(List<String> contractIdList) {
            this.contractIdList = contractIdList;
        }

        public String getAddressType() {
            return addressType;
        }

        public void setAddressType(String addressType) {
            this.addressType = addressType;
        }

        public String getAddressLine1() {
            return addressLine1;
        }

        public void setAddressLine1(String addressLine1) {
            this.addressLine1 = addressLine1;
        }

        public String getAddressLine2() {
            return addressLine2;
        }

        public void setAddressLine2(String addressLine2) {
            this.addressLine2 = addressLine2;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        @Override
        public int compareTo(Object o) {
            return new CompareToBuilder()
                    .append(this.getAddressLine1(), ((Address)o).getAddressLine1())
                    .append(this.getAddressLine2(), ((Address)o).getAddressLine2())
                    .append(this.getZipCode(), ((Address)o).getZipCode())
                    .append(this.getCountry(), ((Address)o).getCountry())
                    .append(this.getAddressType(), ((Address)o).getAddressType())
                    .append(this.getState(), ((Address)o).getState()).toComparison();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;

            if (o == null || getClass() != o.getClass()) return false;

            Address address = (Address) o;

            return new EqualsBuilder()
                    .append(this.getAddressLine1(), address.getAddressLine1())
                    .append(this.getAddressLine2(), address.getAddressLine2())
                    .append(this.getZipCode(),      address.getZipCode())
                    .append(this.getAddressType(),  address.getAddressType())
                    .append(this.getState(),        address.getState())
                    .append(this.getCountry(),      address.getCountry())
                    .isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37)
                    .append(this.getAddressLine1())
                    .append(this.getAddressLine2())
                    .append(this.getAddressType())
                    .append(this.getState())
                    .append(this.getZipCode())
                    .append(this.getCountry())
                    .toHashCode();
        }
    }
}
