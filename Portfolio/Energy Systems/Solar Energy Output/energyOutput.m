% This is our main script which uses the fucntion solarFlux to calculate
% and plot the solar flux through out an entire given day.  After the flux
% is calcuated the energy output for a solar panel is calculated and
% plotted as well.  All this info is also written to an xls file.

% March 5, 2018
% ECE 291
%--------------------------------------------------------------------------
clear all;

% Needed Variables
yearDay = 59 + 20;
longitude = 84.745;
latitude = 39.507;
LSTM = 5;

% Values for solar panel
A = 1.67;   % Area
r = .96;    % Effeciency ratio
pr = .1408; % Loss

solarHourFlux = [0 0 0 0 0 0 0 0];

% Loop through for every hour in the day
for (i = 6:18)
    solarHourFlux = vertcat(solarHourFlux, solarFlux(i, yearDay, latitude, longitude, LSTM));
end

% Remove first value which was used as a place holder
solarHourFlux(1,:) = [];

% Calculate solar panels output
outputEnergy = solarHourFlux(:,8) .* A * r * pr;

% Plot Solar flux in BTUH/FT^2
figure(1);
plot(6:18,solarHourFlux(:,7));
title('Solar Flux Throughout The Day');
hold on;

% Plot on same graph solar flux in W/m^2
plot(6:18,solarHourFlux(:,8));
legend('BTUH/FT^2', 'W/m^2');
xlabel('Hours on 24 hour scale');

% Plot output of solar panel each hour
figure(2)
plot(6:18, outputEnergy);
title('Energy Output Of Solar Panel');
xlabel('Hours on 24 hour scale');
ylabel('Watts');

% Total energy output of solar panel (Just in case we need this data)
dayTotal = sum(outputEnergy)
sum(solarHourFlux(:,8))
% Output to a xls document (Preparing for table)
C = {'Time', 'Beta', 'Hour Angle', 'Solar Altitude', 'Azimuth',...
    'Incident Angle', 'Flux (BTUH/Ft^2)',...
    'Flux(W/m^2)', 'Output Energy (Watts)'};
putIn = [solarHourFlux(:,:), outputEnergy(:)];
C = [C;num2cell(putIn)];

xlswrite('Solar_Energy_Data',C);
