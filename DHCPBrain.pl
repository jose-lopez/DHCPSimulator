isIp(A,B,C,D,E) :-
	A < 256, A >= 0,
	B < 256, B >= 0,
	C < 256, C >= 0,
	D < 256, D >= 0,
	E < 31, E >= 0.

netAdd(A,B,C,D,E) :- 
	isIp(A,B,C,D,E),
	F is 2**(32-E),
	Y is (256//F),
	between(0,Y,X),
	G is (X*F),
	G is D.
		
serAdd(A,B,C,D,E) :- 
	isIp(A,B,C,D,E),
	\+netAdd(A,B,C,D,E),
	asserta(inUse(A,B,C,D,E)).

:- dynamic(inUse/5).

poolAdd(A,B,C,D,E) :-
	isIp(A,B,C,D,E),
	serAdd(A,B,C,D,E),
	\+inUse(A,B,C,D,E).

		

