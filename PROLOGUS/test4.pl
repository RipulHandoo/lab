% Facts: Define parent relationships
parent(john, jim).
parent(john, ann).
parent(sue, jim).
parent(sue, ann).
parent(ann, bob).
parent(bob, mary).

% Rules: Define other relationships based on parent relationships
father(Father, Child) :-
    parent(Father, Child),
    male(Father).

mother(Mother, Child) :-
    parent(Mother, Child),
    female(Mother).

% Rules: Define sibling relationship
sibling(Person1, Person2) :-
    parent(Parent, Person1),
    parent(Parent, Person2),
    Person1 \= Person2.

% Rules: Define grandparent relationship
grandparent(Grandparent, Grandchild) :-
    parent(Grandparent, Parent),
    parent(Parent, Grandchild).

% Facts: Define gender
male(john).
male(jim).
male(bob).

female(sue).
female(ann).
female(mary).




statements to run
?- father(john, jim).
% true.

?- sibling(jim, ann).
% true.

?- grandparent(sue, mary).
% true.

