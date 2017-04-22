/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package visitor;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alex
 */
public class ParentAnimal extends AnimalState{
    
    private static Random rand = new Random();
    
    private static ParentAnimal INSTANCE;
    
    private ParentAnimal(){}

   // thread-safe double checking lazy singleton initialization
    public static ParentAnimal getInstance(){
        if(INSTANCE == null){
            synchronized(ParentAnimal.class){
                if(INSTANCE == null){
                    INSTANCE = new ParentAnimal();
                }               
            }           
        }       
        return INSTANCE;
    }


    // because of the parenthood stress parent Animal eats more then he can get energy from 
    @Override
    public void animalEats(Animal animal) {
        if(animal.getAmountOfFood() > 0){
            animal.addFood(-2);
            animal.addWeight(1);
        }
        
    }

    // parent animal has less time to sleep then grown animal
    @Override
    public void animalSleeps(Animal animal) {
        animal.setSleeping(true);
        try {
            Thread.sleep(50000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GrownAnimal.class.getName()).log(Level.SEVERE, null, ex);
        }
        animal.setSleeping(false);
    }

    /* When parent hunts half of the food he earns goes to its children 
        
    */ 
    @Override
    public void animalHunts(Animal hunter, Animal victum) {
        
        if(victum.getSleeping() || rand.nextDouble() > 0.5){ 
            hunter.addFood(victum.getWeight()/2); // parent gets only half of the animal it killed, rest goes to its children
            
            // if hunter has enough food at his disposal now, he can mate and gets a new baby
            if(hunter.getAmountOfFood() > 20 + 10*hunter.getChildren().size()){
                hunter.setState(ParentAnimal.getInstance());
                hunter.getChildren().add(new Animal(hunter)); 
            }
        }
    }
    
}
