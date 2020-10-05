/**
 * Copyright Murex S.A.S., 2003-2020. All Rights Reserved.
 * <p>
 * This software program is proprietary and confidential to Murex S.A.S and its affiliates ("Murex") and, without limiting the generality of the foregoing reservation of rights, shall not be accessed, used, reproduced or distributed without the
 * express prior written consent of Murex and subject to the applicable Murex licensing terms. Any modification or removal of this copyright notice is expressly prohibited.
 */
package ro.dragos.geornoiu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;


public class GameQuestions {

    private static final List<String> CATEGORIES = List.of("Pop", "Science", "Sports", "Rock");

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Instance fields 
    //~ ----------------------------------------------------------------------------------------------------------------

    private final Map<String, List<String>> categoryToQuestions;

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Constructors 
    //~ ----------------------------------------------------------------------------------------------------------------

    public GameQuestions() {
        categoryToQuestions = new HashMap<>();

        IntStream.range(0, 50).forEach(i ->
                CATEGORIES.forEach(category ->
                        categoryToQuestions.computeIfAbsent(category, k -> new ArrayList<>()).add(category + " Question " + i))
        );
    }

    //~ ----------------------------------------------------------------------------------------------------------------
    //~ Methods 
    //~ ----------------------------------------------------------------------------------------------------------------

    public String retrieveQuestion(String category) {
        List<String> questions = categoryToQuestions.get(category);

        if (questions == null) {
            throw new IllegalArgumentException("Category not recognized: " + category);
        }

        return questions.remove(0);
    }

    public String getQuestionCategory(int playerPlace) {
        int categoriesCount = CATEGORIES.size();
        int categoryIndex = playerPlace % categoriesCount;

        return CATEGORIES.get(categoryIndex);
    }
}
