% Facts: Define characteristics of cars
car_type(sedan, 4, gasoline).
car_type(suv, 4, gasoline).
car_type(hatchback, 4, hybrid).
car_type(truck, 2, diesel).

% Rules: Define predicates for identifying cars based on characteristics
identify_car(Features, CarType) :-
    member(wheels(Wheels), Features),
    member(fuel(Fuel), Features),
    car_type(CarType, Wheels, Fuel).

% Example query
% To identify a car with 4 wheels and runs on gasoline
% ?- identify_car([wheels(4), fuel(gasoline)], CarType).
% Possible output: CarType = sedan ; CarType = suv.
