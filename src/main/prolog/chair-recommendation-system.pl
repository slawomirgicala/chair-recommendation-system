% symptomy

:- dynamic person/3,
    sitting_time/2,
    measure/3,
    walking_time/2,
    sex/2,
    age/2,
    backpain/1,
    invoice/1,
    joga/1,
    budget/2,
    gym/1,
    student/1,
    sleep_on_back/1,
    sleep_on_side/1,
    it_job/1,
    children/2,
    married/2,
    discount/2.

% reguły

is_company(Y) :- invoice(Y).

private_order(Y) :- not(invoice(Y)).

teen(Y) :- age(Y, A), A > 0, A < 20.
earlyadulthood(Y) :- age(Y, A), A >= 20, A < 35.
midadulthood(Y) :- age(Y, A), A >= 35, A < 50.
lateadulthood(Y) :- age(Y, A), A >= 50.

sitting_too_long(Y) :- sitting_time(Y, X), X > 50.

has_children(Y) :- children(Y, X), X > 0.
is_married(Y) :- married(Y, X), X=1.

slim(Y) :- sex(Y, m), measure(Y, X, _),  X =< 80.
slim(Y) :- sex(Y, f), measure(Y, X, _), X =< 40.

w_normal(Y) :- sex(Y, m), measure(Y, X, _),  X > 80, X =< 100.
w_normal(Y) :- sex(Y, f), measure(Y, X, _),  X > 40, X =< 60.

fat(Y) :- sex(Y, m), measure(Y, X, _),  X > 100.
fat(Y) :- sex(Y, f), measure(Y, X, _),  X > 60.

active(Y) :- walking_time(Y, X), X > 400.
normal_act(Y) :- walking_time(Y, X), X =< 400, X > 30.
lazy(Y) :- walking_time(Y, X), X =< 30.

tall(Y) :- sex(Y, m), measure(Y, _, H),  H > 190.
tall(Y) :- sex(Y, f), measure(Y, _, H),  H > 170.

h_normal(Y) :- sex(Y, m), measure(Y, _, H), H > 175, H =< 190.
h_normal(Y) :- sex(Y, f), measure(Y, _, H), H > 165, H =< 170.

small(Y) :- sex(Y, m), measure(Y, _, H),  H =< 175.
small(Y) :- sex(Y, f), measure(Y, _, H),  H =< 165.

% fakty pośrednie

upper_budget(Y) :- budget(Y, B), ((is_company(Y), B > 4000);(private_order(Y), B > 2000)).
middle_budget(Y) :- budget(Y, B),((is_company(Y), B >= 2000, B =< 4000);(private_order(Y), B =< 2000, B > 800)).
low_budget(Y) :-budget(Y, B),((is_company(Y), B < 2000);(private_order(Y), B =< 800)).

obese(Y) :- fat(Y), sitting_too_long(Y).

scoliosis_danger(Y) :- sitting_too_long(Y), sleep_on_side(Y).

real_hump(Y) :- sitting_too_long(Y), not(active(Y)), joga(Y), gym(Y).

might_have_backpain(Y) :- obese(Y); it_job(Y); (lazy(Y), sitting_too_long(Y)).

% 1. czy powinienem zarekomendować drogie krzesło ergonomiczne

recommend_ergonomic_chair(Y) :- middle_budget(Y), sitting_too_long(Y);
    upper_budget(Y).

recommend_ergonomic_chair_people(X, Y, Z) :- recommend_ergonomic_chair(X), person(X, Y, Z).

% 2. czy powinienem zarekomendować promocję na zakup krzeseł dla rodziny, z budżetowych materiałów

recommend_family_discount(Y) :- (has_children(Y), low_budget(Y));
    (is_married(Y), low_budget(Y)).

recommend_family_discount_people(X, Y, Z) :- recommend_family_discount(X), person(X, Y, Z).

% 3. krzesło z masażem - starsza soba lub młoda, dużo siedząca  

recommend_massage_chair(Y) :- not(lateadulthood(Y)), sitting_too_long(Y);
    lateadulthood(Y).

recommend_massage_chair_people(X, Y, Z) :- recommend_massage_chair(X), person(X, Y, Z).

% 4. biwak, festiwal, wędkarz - krzesło rozkładane

:- dynamic retirement/2,
    bought_rod/2,
    bought_tent/2,
    bought_sleeping_bag/2.

on_retirement(Y) :- retirement(Y, X), X=1.

fisherman(Y) :- (on_retirement(Y), is_married(Y));
    (bought_rod(Y, X), X=1).

traveller(Y) :- (bought_tent(Y, X), X=1);
    (bought_sleeping_bag(Y, X), X=1);
    (not(earlyadulthood(Y)), not(is_married(Y))).

recommend_portable_chair(Y) :- fisherman(Y);
    traveller(Y).

recommend_portable_chair_people(X, Y, Z) :- recommend_portable_chair(X), person(X, Y, Z).

% 5. krzesło w leasing - prowadzi firmę i ma duży dochód, ale nie bolą go plecy - krzesło szefa

:- dynamic company/1,
    car/2,
    backache/1.

expensive_car(Y) :- car(Y, X), X>100000.

boss(Y) :- company(Y), expensive_car(Y), not(backache(Y)).

programmer(Y) :- upper_budget(Y), not(fisherman(Y)), not(on_retirement(Y)).

recommend_leasing_chair(Y) :- boss(Y), programmer(Y).

recommend_leasing_chair_people(X, Y, Z) :- recommend_leasing_chair(X), person(X, Y, Z).

% fakty pośrednie

scoliosis_danger(Y) :- sitting_too_long(Y), sleep_on_side(Y).

real_hump(Y) :- sitting_too_long(Y), not(active(Y)), joga(Y), gym(Y).

% hipoteza - 5. przez przypadek

leasing(Y) :- is_company(Y), not(sitting_too_long(Y)), active(Y).

% 6. czy tej osobie grozi garb na plecach

hump(Y):- real_hump(Y); it_job(Y); (tall(Y), sitting_too_long(Y)); scoliosis_danger(Y).

hump_people(X, Y, Z) :- hump(X), person(X, Y, Z).

% 7. nie powinienem dzwonić ponieważ ta osoba ma niżki i mniej zarobi

too_big_discount(Y) :- student(Y); not(midadulthood(Y)).

too_big_discount_people(X, Y, Z) :- too_big_discount(X), person(X, Y, Z).

% 8. ta osoba może polecić innym nasze usługi - dużo osób o tym samym nazwisku o podobynm wzroście


% 9. krzesło gamerskie ??? 

gamer_chair(Y) :- teen(Y), obese(Y), upper_budget(Y), not(company(Y)).

gamer_chair_people(X, Y, Z) :- gamer_chair(X), person(X, Y, Z).

% 10. taboret - ktoś kto uprawia jogę i lubi egzoytyczne przyprawy i jest niski

chair_without_backrest(Y) :- not(tall(Y)), active(Y), not(might_have_backpain(Y)).
chair_without_backrest_people(X, Y, Z) :- chair_without_backrest(X), person(X, Y, Z).