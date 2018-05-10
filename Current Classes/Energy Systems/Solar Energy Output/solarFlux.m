function k = solarFlux(time, yearDay, latitude, longitude, LTSM)
% Calculates the solar flux for a given time of day

% time - The hour of day to be calculated
% yearDay - The number of the day of the year IE March 20th = 79
% latitude - The latitude of the location
% longitude - The longitude of the location
% LTSM      - The difference in time from Greenwhich

% Vernal Equinox
% March 20th
% 12:15 (EDT)a
% Gamma = 0 ( Solar Panel Orentation)
% 39.5070° N, 84.7452° W Latitude Longitude

% Zenith Angle - Angle of the sun if looking at it from the ground (ground
% angle 90 degrees at solar noon 0 at sunrise and sunsetchighest altitude at 90
% degrees.  Altitutde depends on summer and winter solstice

% Azimuth Angle - North to south orientation ( distance from true south)
% at solar noon the angle is 0 degrees to the left (east) the value is zero to the
% right (west) it is positive

% Solar Panel Orientation gamma  = 0
% beta = 54;

% Needed variables
long = longitude;
l  = latitude;

% Given coeffecience
A = [376 1230];   
B = .156;

% Declination
% d = 23.45*sind(360 * ((284 + n)/365)) This is how I originally calculated
% declination.  According to Dr. Niskode its 0 therefor thats what we will
% use
d = 0;

% Solar time calculations 
LST = time-(.04*((15*LTSM)-long));
beta  = 54;

% Hour Angle
h = (time * 15)-180;

% Solar Altitude
solarAlt= asind((cosd(l)*cosd(h)*cosd(d))+(sind(l)*sind(d)));

% Azimuth
azimuth = asind(((1/cosd(beta))*((cosd(d)*sind(h)))));

% Incident Angle
incAngle= acosd((sind(solarAlt)*cosd(beta))+(cosd(solarAlt)*sind(beta)...
    * cosd(-azimuth)))

% Direct Normal sunlight
Idn =  A/exp(B/sind(solarAlt));

k = [time beta h solarAlt azimuth incAngle Idn];
end

