% Facts: Define characteristics of animals
animal_type(lion, carnivore, four_legs).
animal_type(elephant, herbivore, four_legs).
animal_type(hawk, carnivore, two_legs).
animal_type(turtle, herbivore, four_legs).
animal_type(dolphin, carnivore, no_legs).

% Rules: Define predicates for identifying animals based on characteristics
identify_animal(Features, Animal) :-
    member(diet(Diet), Features),
    member(legs(Legs), Features),
    animal_type(Animal, Diet, Legs).

% Example query
% To identify an animal that is a carnivore and has four legs
% ?- identify_animal([diet(carnivore), legs(four_legs)], Animal).
% Possible output: Animal = lion ; Animal = hawk.
