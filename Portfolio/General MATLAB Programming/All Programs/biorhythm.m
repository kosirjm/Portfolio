% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 3.14
% 1/9/18
% A biorythm function
%-------------------------------------------------------------------------
function biorhythm(birthday)

   t0 = datenum(birthday);
   t1 = fix(now);

% Eight week time span centered on today.
t = (t1-28):(t1+28);
y = 100*[sin(2*pi*(t-t0)/23)
         sin(2*pi*(t-t0)/28)
         sin(2*pi*(t-t0)/33)];
plot(t,y)

   grid on
      datetick('x',6,'keeplimits','keepticks')
      leg = legend('Physical','Emotional','Intellectual');
 end % biorhythm