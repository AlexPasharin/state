/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitor;

import java.util.ArrayList;
import java.util.List;

/** Animal is a thread that sleeps, eats and hunts during its lifecycle
 * 
 * For the sake of simplicity run() method is not implemented in details, as it is not the point of this exercise
 * 
 * Animal has parents and children. In order to avoid chicken-egg problem null value for parent is allowed
 * 
 * Animal has 3 states: BabyAnimal, GrownAnimal and ParentAnimal. All states are thread-safe singletons.
 *
 * @author Alex
 */
public class Animal extends Thread{
        
    private static final int INITIAL_WEIGHT = 5;
    private int weight = INITIAL_WEIGHT;
    // new animal is born as a baby animal and starts one's life by eating
    private boolean alive = true;
    private boolean eating = true;
    private boolean sleeping = false;
    
    private int amountOfFood = 0; // food hunted and stored by this animal
    
    private AnimalState state = BabyAnimal.getInstance();
    
    private Animal parent;
    private List<Animal> children = new ArrayList<>();
    
    public Animal(Animal parent){
        this.parent = parent;
    }
    
    public void run(){
        while(this.alive){
            /** Here animal sleeps, eats, hunts etc.
             * Not implemented since would be too complicated and not the purposes of this exercise
             * 
             * 
             */
        }
    }
    
    public void eat(){
            this.state.animalEats(this);
    }
    
    public void sleep(){
            this.state.animalSleeps(this);
    }
    
    public void hunt(Animal victum){
            this.state.animalHunts(this, victum);
    }
    
    public void setState(AnimalState state){
        this.state = state;
    }
    
    
    public void addWeight(int weight){
        this.weight += weight;
    }
    
    public int getWeight(){
        return this.weight;
    }

    public void setEating(boolean eating) {
        this.eating = eating;
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }
    
    public boolean getSleeping() {
        return this.sleeping;
    }
    
    public int getAmountOfFood() {
        return amountOfFood;
    }

    public void addFood(int amount) {
        this.amountOfFood += amount;
    }

    public Animal getParent() {
        return parent;
    }

    public List<Animal> getChildren() {
        return children;
    }
    
    public void growUp(){
        this.setState(GrownAnimal.getInstance());
        this.parent.getChildren().remove(this); // remove itself from the list of its parent children to support
        if(this.parent.getChildren().isEmpty()) this.parent.setState(GrownAnimal.getInstance()); // if parent now has no children changde its state
        
        this.parent = null; // null the parent since we don't need it anymore
    }
    
    public void die(){
        this.alive = false;
    }
    
}
