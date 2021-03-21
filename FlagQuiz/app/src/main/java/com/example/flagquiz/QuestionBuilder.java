package com.example.flagquiz;

import java.util.Random;

public class QuestionBuilder {

    private String[] allNames; // holds all flag names as a string
    private int[] allImages; // holds a reference to all flag images

    private String[] stringAnswers = new String[4];  // holds the string of the answer options
    private int[] intAnswers = new int[]{999999,999999,999999,999999}; // holds the int index of the answer options
    private int correctAnswer; // index of the correct answer in the intAnswers array
    private int flagImg;

    // NO ARG CONSTRUCTOR
        // Select an image, correct answer, and 3 wrong answers
    QuestionBuilder(){
        createArrays();
        chooseAnswers();
    }

    // creates the arrays that contain all images and country strings
    private void createArrays(){
        allImages = new int[]{R.drawable.albania, R.drawable.antarctica, R.drawable.australia, R.drawable.bahamas, R.drawable.barbados, R.drawable.brazil, R.drawable.cambodia, R.drawable.canada, R.drawable.china, R.drawable.finland, R.drawable.germany, R.drawable.greenland, R.drawable.russia, R.drawable.saudiarabia, R.drawable.turkey, R.drawable.wales,
                R.drawable.afghanistan, R.drawable.algeria, R.drawable.americansamoa, R.drawable.angola, R.drawable.argentina, R.drawable.aruba,
                R.drawable.bahrain, R.drawable.bangladesh, R.drawable.brunei, R.drawable.burundi, R.drawable.centralafricanrepublic, R.drawable.chad, R.drawable.croatia, R.drawable.cuba,
                R.drawable.egypt, R.drawable.eswatini, R.drawable.france, R.drawable.genada, R.drawable.georgia, R.drawable.gibraltar, R.drawable.guam, R.drawable.guyana,
                R.drawable. haiti, R.drawable.iraq, R.drawable.isleofman, R.drawable.kenya, R.drawable.scotland, R.drawable.slovakia, R.drawable.southkorea, R.drawable.spain,
                R.drawable.virginislands, R.drawable.zimbabwe
        };
        allNames = new String[]{"Albania", "Antarctica", "Australia", "Bahamas", "Barbados", "Brazil", "Cambodia", "Canada", "China", "Finland", "Germany", "Greenland", "Russia", "Saudi Arabia", "Turkey", "Wales",
                "Afghanistan", "Algeria", "American Samoa", "Angola", "Argentina", "Aruba",
                "Bahrain", "Bangladesh", "Brunei", "Burundi", "Central African Republic", "Chad", "Croatia", "Cuba",
                "Egypt", "Eswatini", "France", "Genada", "Georgia", "Gibraltar", "Guam", "Guyana",
                "Haiti", "Iraq", "Isle Of Man", "Kenya", "Scotland", "Slovakia", "South Korea", "Spain",
                "Virgin Islands", "Zimbabwe"
        };
    }

    // chooses random non-repeating answer options
    // one of the answers is selected to be correct
    // we then get the image of the selected correct answer and set it
    private void chooseAnswers(){
        int max = allImages.length, min = 0;
        Random r = new Random();
        int i1 = r.nextInt(max - min);
        for(int i = 0; i<4 ; i++){
            int answerInt;
            do{
                answerInt = r.nextInt(max - min);
            }while(arrayContains(intAnswers, answerInt));
            intAnswers[i] = answerInt;
            stringAnswers[i] = allNames[answerInt];
        }
        // choose the correct answer ans set the image
        correctAnswer = r.nextInt(4 - 0);
        flagImg = allImages[intAnswers[correctAnswer]];

    }

    // returns if the passed array contains the passed value
    private boolean arrayContains(int[] array, int value){
        for(int i=0 ; i<array.length ; i++){
            if(array[i] == value){
                return true;
            }
        }
        return false;
    }

    ///////////////////////////////////////
    ///////   GETTERS ////////////////////
    /////////////////////////////////////
    public int getCorrectAnswer(){
        return correctAnswer;
    }
    public int getFlagImg(){
        return flagImg;
    }
    public String getAnswer0(){
        return stringAnswers[0];
    }
    public String getAnswer1(){
        return stringAnswers[1];
    }
    public String getAnswer2(){
        return stringAnswers[2];
    }
    public String getAnswer3(){
        return stringAnswers[3];
    }

    // returns boolean if the passed array index is correct
    public boolean isCorrect(int selected){
        if(selected == correctAnswer){ return true; }
        return false;
    }


}
