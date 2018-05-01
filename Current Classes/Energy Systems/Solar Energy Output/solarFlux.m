function I = solarFlux(time, yearDay, latitude, longitude, LTSM)
%SOLARFLUX Summary of this function goes here
%   Detailed explanation goes here

% Vernal Equinox
% March 20th
% 12:15 (EDT)a
% Gamma = 0 ( Solar Panel Orentation)
% 39.5070° N, 84.7452° W Latitude Longitude

% needed variables
I = [0 0];
n = yearDay;
A = [376 1230];
B = .156;
C = .071;
v = 54;  % beta 
pg = .2; % ground reflectivity
a = latitude;
l  = longitude;
sigma = 0;

% Declination
d = 23.45*sin(360 * ((284 + n)/365));

% Earth Orbit correction  *********************************
Btime = (yearDay - 81)*(360/365);
E = 9.87*sin(2*Btime)-7.53*cos(Btime)-1.5*sin(Btime);

% solar time calculations
LTSM = (15*LTSM);
TC = 4*(longitude-LTSM) + E;
LST = time+TC/60;

% Hour Angle
h = 15 * (LST - 12);

% zenith angle
theta = acos((cos(l)*cos(h)*cos(d)+sin(l)*sin(d)));

% Beta
beta = asin((cos(l)*cos(h)*cos(d)+sin(l)*sin(d))); 

% Direct Normal sunlight
Idn =  A*exp(-B/sin(beta));

% Diffuse Solar Flux
IdH = C * Idn;

% Direct Normal with incedence of 54
ID = Idn *cos(54)

% total
I = IdH + ID;

end

