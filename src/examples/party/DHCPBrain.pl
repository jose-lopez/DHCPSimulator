:- dynamic(inUse/5).
:- dynamic(availableIp/5).

availableIp(A,B,C,D,E).
inUse(000,000,000,000,00).
inUse(255,255,255,255,32).

a_poolAdd(A,B,C,D,E):-
	availableIp(A,B,C,D,E),
	assertz(inIuse(A,B,C,D,E)),
	retract(availableIp(A,B,C,D,E)).

a_FreeAdd(A,B,C,D,E):-
	inUse(A,B,C,D,E),
	assertz(availableIp(A,B,C,D,E)),
	retract(inUse(A,B,C,D,E)).
			

isIp(A,B,C,D,E) :-
	A < 256, A >= 0,
	B < 256, B >= 0,
	C < 256, C >= 0,
	D < 256, D >= 0,
	E < 31, E >= 0.

is_netAdd(A,B,C,D,E) :- 
	isIp(A,B,C,D,E),
	F is 2**(32-E),
	Y is (256//F),
	between(0,Y,X),
	G is (X*F),
	G is D.
		
is_serAdd(A,B,C,D,E) :- 
	isIp(A,B,C,D,E),
	\+netAdd(A,B,C,D,E),
	\+inUse(A,B,C,D,E),
	a_poolAdd(A,B,C,D,E).
	
	
