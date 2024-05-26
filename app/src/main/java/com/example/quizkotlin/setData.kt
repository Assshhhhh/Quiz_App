package com.example.quizkotlin

object setData {

    const val name: String = "name"
    const val score: String = "score"

    fun getQuestion(): ArrayList<QuestionData>{

        val que: ArrayList<QuestionData> = arrayListOf()

        val q1 = QuestionData(
            1,
            "Which is the largest country in the world?",
            "Russia",
            "Canada",
            "China",
            "United States",
            1
        )

        val q2 = QuestionData(
            2,
            "What is the deepest ocean in the world?",
            "Philippine Trench",
            "Puerto Rican Trench",
            "Tonga Trench",
            "Mariana Trench",
            4
        )

        val q3 = QuestionData(
            3,
            "What is the largest living organism on Earth?",
            "Honey fungus",
            "Giant sequoia",
            "Whale shark",
            "Blue whale",
            2
        )

        val q4 = QuestionData(
            4,
            "What is the most common element in the universe?",
            "Oxygen",
            "Helium",
            "Hydrogen",
            "Carbon",
            3
        )

        val q5 = QuestionData(
            5,
            "What is the capital of Australia?",
            "Sydney",
            "Canberra",
            "Melbourne",
            "Brisbane",
            2
        )

        val q6 = QuestionData(
            6,
            "What is the chemical symbol for water?",
            "H2O",
            "CO2",
            "NaCl",
            "NH3",
            1
        )

        val q7 = QuestionData(
            7,
            "What is the name of the world's largest desert?",
            "Arabian",
            "Gobi",
            "Sahara",
            "Kalahari",
            3
        )

        val q8 = QuestionData(
            8,
            "What is the name of the largest planet in our solar system?",
            "Saturn",
            "Jupiter",
            "Neptune",
            "Uranus",
            2
        )

        val q9 = QuestionData(
            9,
            "What is the name of the world's tallest mountain?",
            "Mount Everest",
            "K2",
            "Kangchenjunga",
            "Lhotse",
            1
        )

        val q10 = QuestionData(
            10,
            "What is the name of the world's longest river?",
            "Mississippi",
            "Amazon",
            "Yangtze",
            "Nile",
            4
        )


        que.add(q1)
        que.add(q2)
        que.add(q3)
        que.add(q4)
        que.add(q5)
        que.add(q6)
        que.add(q7)
        que.add(q8)
        que.add(q9)
        que.add(q10)

        return que

    }

}