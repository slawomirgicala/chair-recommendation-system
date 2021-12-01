package pl.agh.expertsystems.chairrecommendationsystem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Person {
    String name;
    String surname;
    Integer budget;
    Integer discount;
    String age;
    Integer height;
    Integer weight;
    String sex;
    
    @JsonProperty("sitting-time")
    Integer sittingTime;
    
    @JsonProperty("walking-time")
    Integer walkingTime;
    
    Boolean backpain;
    Boolean invoice;
    Boolean joga;
    Boolean gym;
    Boolean student;

    @JsonProperty("sleep-on-back")
    Boolean sleepOnBack;
    @JsonProperty("sleep-on-side")
    Boolean sleepOnSide;
    @JsonProperty("it-job")
    Boolean itJob;

    public Person(){
        super();
    }

    public Person(String name, String surname, Integer budget, Integer discount, Integer sittingTime, String age, Integer height, Integer weight, String sex) {
        this.name = name;
        this.surname = surname;
        this.budget = budget;
        this.discount = discount;
        this.sittingTime = sittingTime;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.sex = sex;
    }

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getBudget() {
        return budget;
    }

    public void setBudget(Integer budget) {
        this.budget = budget;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public Integer getSittingTime() {
        return sittingTime;
    }

    public void setSittingTime(Integer sittingTime) {
        this.sittingTime = sittingTime;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public Integer getWalkingTime() {
        return walkingTime;
    }

    public void setWalkingTime(Integer walkingTime) {
        this.walkingTime = walkingTime;
    }

    public Boolean getBackpain() {
        return backpain;
    }

    public void setBackpain(Boolean backpain) {
        this.backpain = backpain;
    }

    public Boolean getInvoice() {
        return invoice;
    }

    public void setInvoice(Boolean invoice) {
        this.invoice = invoice;
    }

    public Boolean getJoga() {
        return joga;
    }

    public void setJoga(Boolean joga) {
        this.joga = joga;
    }

    public Boolean getGym() {
        return gym;
    }

    public void setGym(Boolean gym) {
        this.gym = gym;
    }

    public Boolean getStudent() {
        return student;
    }

    public void setStudent(Boolean student) {
        this.student = student;
    }

    public Boolean getSleepOnBack() {
        return sleepOnBack;
    }

    public void setSleepOnBack(Boolean sleepOnBack) {
        this.sleepOnBack = sleepOnBack;
    }

    public Boolean getSleepOnSide() {
        return sleepOnSide;
    }

    public void setSleepOnSide(Boolean sleepOnSide) {
        this.sleepOnSide = sleepOnSide;
    }

    public Boolean getItJob() {
        return itJob;
    }

    public void setItJob(Boolean itJob) {
        this.itJob = itJob;
    }
}
