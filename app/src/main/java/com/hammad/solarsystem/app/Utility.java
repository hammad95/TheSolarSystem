package com.hammad.solarsystem.app;

/**
 * Created by Hassan on 10/14/2016.
 * Provides helper methods and objects
 */
public class Utility {
    // The intent extra key for planet name passed to activities
    public static final String PLANET_NUMBER = "planet_number";

    // A 2D array that holds the questions for each quiz section
    public static String[][] quizQuestionsArray = {
            {
                    "1. How far is the Sun from Earth?",
                    "2. What, approximately, is the temperature at the core of the Sun?",
                    "3. How much more massive is the Sun compared to Earth?"
            },
            {
                    "1. How high and low do Mercury's surface temperatures reach during the day and night, respectively?",
                    "2. How far can Mercury's orbit take it from the Sun?",
                    "3. How is the atmosphere on Mercury like?"
            },
            {
                    "1. Venus is closer to the Earth compared to which of these planets?",
                    "2. What kind of atmosphere does Venus have?",
                    "3. A day on Venus is equal to how many Earth days?"
            },
            {
                    "1. About how long does sunlight take to travel from the Sun to Earth?",
                    "2. Which metals make up the bulk of Earth's inner core?",
                    "3. Which of the following is true?"
            },
            {
                    "1. How does the size of Mars compare to Earth?",
                    "2. Why does Mars experience seasons like Earth?",
                    "3. Which NASA spacecraft in 2008 discovered the occasional presence of liquid water on Mars?"
            },
            {
                    "1. How many times wider is Jupiter compared to Earth?",
                    "2. How long does sunlight take to travel from the Sun to Jupiter?",
                    "3. What does Jupiter's atmosphere mainly consist of?",
            },
            {
                    "1. Which of the following is true about Saturn's size?",
                    "2. What is Saturn mainly composed of?",
                    "3. What are the rings of Saturn primarily made up of?"
            },
            {
                    "1. Which of these best describes Uranus?",
                    "2. How many times wider is Uranus compared to Earth?",
                    "3. Which gas in Uranus's atmosphere gives it its blue-green color?"
            },
            {
                    "1. On average, how far is Neptune from the Sun?",
                    "2. What, in Neptune's atmosphere, gives it its blue color?",
                    "3. The abundance of Hydrogen and Helium in Neptune's atmosphere resembles which of these other planets?"
            }
    };

    // A 2D that holds the options for questions 1 to 3 for each of the planets
    public static String[][] quizOptionsArray = {
            {
                    "50 million miles",
                    "1 billion miles",
                    "77 million miles",
                    "93 million miles",
                    "100,000 degrees Fahrenheit",
                    "1 million degrees Fahrenheit",
                    "27 million degrees Fahrenheit",
                    "52 million degrees Fahrenheit",
                    "12,000 times",
                    "332,946 times",
                    "200,000 times",
                    "445,883 times"
            },
            {
                    "800 degrees Fahrenheit and -290 degrees Fahrenheit respectively",
                    "200 degrees Fahrenheit and -90 degrees Fahrenheit respectively",
                    "1000 degrees Fahrenheit and -1000 degrees Fahrenheit respectively",
                    "Surface temperatures on Mercury remain the same during the day and night.",
                    "29 million miles",
                    "43 million miles",
                    "65 million miles",
                    "67 million miles",
                    "Mercury does not have an atmosphere",
                    "The same as Earth",
                    "A very thick atmosphere",
                    "An atmosphere rich in hydrogen and helium"
            },
            {
                    "Mars",
                    "Mercury",
                    "Jupiter",
                    "All of the above",
                    "A very thick atmosphere rich in carbon dioxide",
                    "A vey thin atmosphere lacking carbon dioxide",
                    "An atmosphere rich in hydrogen and helium",
                    "Venus does not have an atmosphere",
                    "94",
                    "156",
                    "243",
                    "124"
            },
            {
                    "1 second",
                    "1 minute",
                    "8 minutes",
                    "5 seconds",
                    "Copper and Nickel",
                    "Iron and Phosphorus",
                    "Iron and Nickel",
                    "Iron and Magnesium",
                    "The inner core of the Earth is liquid",
                    "The outer core of the Earth is the outermost layer",
                    "The outer core of the Earth is solid",
                    "The mantle is a mixture of hot and molten rock"
            },
            {
                    "Mars is about the same size as Earth ",
                    "Mars is slightly bigger than Earth",
                    "Mars is almost double the size of Earth",
                    "Mars is about half the size of Earth",
                    "Due to its speed of rotation around its axis",
                    "Due to its mass being about the same as Earth's mass",
                    "Due to the fact that it's a terrestrial planet",
                    "Due to the tilt of its rotational axis",
                    "Pioneer",
                    "Phoenix",
                    "Rosetta",
                    "Magellan"
            },
            {
                    "6 times",
                    "9 times",
                    "11 times",
                    "10 times",
                    "10 seconds",
                    "19 minutes",
                    "43 minutes",
                    "1 hour",
                    "Hydrogen and Oxygen",
                    "Hydrogen and Helium",
                    "Oxygen and Carbon dioxide",
                    "Oxygen and Nitrogen"
            },
            {
                    "Saturn is the second largest planet in our solar system",
                    "Saturn is the fourth largest planet in our Solar System",
                    "Saturn is the third largest planet in our Solar System",
                    "Saturn is the smallest planet in our solar system",
                    "Iron and Nickel",
                    "Iron and Hydrogen",
                    "Hydrogen and Helium",
                    "Nitrogen and Helium",
                    "Saturn's magnetic field",
                    "Reflection of sunlight off Saturn's surface",
                    "Saturn's moons",
                    "Pieces of comets, asteroids or shattered moons"
            },
            {
                    "A hot ball of gases",
                    "An ice giant",
                    "A dwarf planet",
                    "The planet with the shortest day in our solar system",
                    "4 times",
                    "10 times",
                    "15 times",
                    "The Earth is wider than Venus",
                    "Ammonia",
                    "Helium",
                    "Methane",
                    "Hydrogen"
            },
            {
                    "77 million miles",
                    "100 million miles",
                    "1.5 billion miles",
                    "2.8 billion miles",
                    "Hydrogen",
                    "Methane",
                    "Helium",
                    "Hydrogen",
                    "Jupiter",
                    "Saturn",
                    "Uranus",
                    "All of the above"
            }
    };

    // A 2D array that contains the right answers for questions 1 through 3
    // for all the quiz sections
    public static int[][] quizAnswersArray = {
            {R.id.q1_option_4, R.id.q2_option_3, R.id.q3_option_2},
            {R.id.q1_option_1, R.id.q2_option_2, R.id.q3_option_1},
            {R.id.q1_option_4, R.id.q2_option_1, R.id.q3_option_3},
            {R.id.q1_option_3, R.id.q2_option_3, R.id.q3_option_4},
            {R.id.q1_option_4, R.id.q2_option_4, R.id.q3_option_2},
            {R.id.q1_option_3, R.id.q2_option_3, R.id.q3_option_2},
            {R.id.q1_option_1, R.id.q2_option_3, R.id.q3_option_4},
            {R.id.q1_option_2, R.id.q2_option_1, R.id.q3_option_3},
            {R.id.q1_option_4, R.id.q2_option_2, R.id.q3_option_4}
    };

    // A 2D array that contains images for detail view
    // for each planet indexed by planet numbers; the
    // same image is used for thumbnail and full image
    public static int[][] planetDetailImagesArray = {
            {
                    R.drawable.sun_image_1,
                    R.drawable.sun_image_2,
                    R.drawable.sun_image_3,
                    R.drawable.sun_image_4,
                    R.drawable.sun_image_5
            },
            {
                    R.drawable.mercury_image_1,
                    R.drawable.mercury_image_2,
                    R.drawable.mercury_image_3,
                    R.drawable.mercury_image_4,
                    R.drawable.mercury_image_5
            },
            {
                    R.drawable.venus_image_1,
                    R.drawable.venus_image_2,
                    R.drawable.venus_image_3,
                    R.drawable.venus_image_4,
                    R.drawable.venus_image_5
            },
            {
                    R.drawable.earth_image_1,
                    R.drawable.earth_image_2,
                    R.drawable.earth_image_3,
                    R.drawable.earth_image_4,
                    R.drawable.earth_image_5
            },
            {
                    R.drawable.mars_image_1,
                    R.drawable.mars_image_2,
                    R.drawable.mars_image_3,
                    R.drawable.mars_image_4,
                    R.drawable.mars_image_5
            },
            {
                    R.drawable.jupiter_image_1,
                    R.drawable.jupiter_image_2,
                    R.drawable.jupiter_image_3,
                    R.drawable.jupiter_image_4,
                    R.drawable.jupiter_image_5
            },
            {
                    R.drawable.saturn_image_1,
                    R.drawable.saturn_image_2,
                    R.drawable.saturn_image_3,
                    R.drawable.saturn_image_4,
                    R.drawable.saturn_image_5,
            },
            {
                    R.drawable.uranus_image_1,
                    R.drawable.uranus_image_2,
                    R.drawable.uranus_image_3,
                    R.drawable.uranus_image_4,
                    R.drawable.uranus_image_5
            },
            {
                    R.drawable.neptune_image_1,
                    R.drawable.neptune_image_2,
                    R.drawable.neptune_image_3,
                    R.drawable.neptune_image_4,
                    R.drawable.neptune_image_5
            }
    };
}
