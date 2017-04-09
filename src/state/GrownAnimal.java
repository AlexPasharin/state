/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package state;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class GrownAnimal extends AnimalState {
    
    private static Random rand = new Random();    
    
    private static GrownAnimal INSTANCE;
    
    private GrownAnimal(){}

   // thread-safe double checking lazy singleton initialization
    public static GrownAnimal getInstance(){
        if(INSTANCE == null){
            synchronized(GrownAnimal.class){
                if(INSTANCE == null){
                    INSTANCE = new GrownAnimal();
                }               
            }           
        }       
        return INSTANCE;
    }

    // grown animal eats only if it has food at its store
    @Override
    public void animalEats(Animal animal) {
        if(animal.getAmountOfFood() > 0){
            animal.addFood(-1);
            animal.addWeight(1);
        }
    }
    
    // grown animal can only afford to sleep for 100 seconds
    @Override
    public void animalSleeps(Animal animal) {
        animal.setSleeping(true);
        try {
            Thread.sleep(100000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GrownAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        animal.setSleeping(false);
    }

    @Override
    public void animalHunts(Animal hunter, Animal victum) {
        // if victum is sleeping attack always succeeds. otherwise chances are 50/50
        if(victum.getSleeping() || rand.nextDouble() > 0.5){ // if hunting succeded
            hunter.addFood(victum.getWeight()); // add the weight of killed animal to hunters food store
            
            // if hunter has enough food at his disposal now, he can mate and gets a new baby
            if(hunter.getAmountOfFood() > 20){
                hunter.setState(ParentAnimal.getInstance());
                hunter.getChildren().add(new Animal(hunter)); 
            }
        }
        
        
    }
    
}
