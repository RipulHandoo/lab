likes(shivam, ripul).
likes(ripul, shivam).
likes(ripul, rites).
likes(uday, vaju).

friendship(X, Y) :- likes(X, Y) ; likes(Y, X).

% Example queries
% Try querying like: friendship(ripul, shivam).
% This will check if ripul and shivam are friends based on the likes relationship.
