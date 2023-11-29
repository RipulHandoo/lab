% Define facts about symptoms and diseases
symptom(john, fever).
symptom(john, cough).
symptom(jane, headache).
symptom(jane, fatigue).
symptom(bob, cough).
symptom(bob, fatigue).

disease(fever, flu).
disease(cough, cold).
disease(headache, stress).
disease(fatigue, anemia).

% Define rules for diagnosis
diagnose(Person, Disease) :-
    symptom(Person, Symptom),
    disease(Symptom, Disease).

% Example queries
% To diagnose diseases for John
% ?- diagnose(john, Disease).
% Possible output: Disease = flu ; Disease = cold.

% To diagnose diseases for Jane
% ?- diagnose(jane, Disease).
% Possible output: Disease = stress ; Disease = anemia.

% To diagnose diseases for Bob
% ?- diagnose(bob, Disease).
% Possible output: Disease = cold ; Disease = anemia.
