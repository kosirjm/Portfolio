%{
Jonathan Kosir
M/M/2/K With variable prob of server choice
ECE 461

-------------------------Display Function----------------------------------
This is a a simple display function for the data which we would like to
show after the simulation and theoretical calculations.  This could be done
in the main program but moved here to remove reduncy and clutter. 
---------------------------------------------------------------------------
%}
function displayData(queue, theoretical, simulated, error)
% queue        = String Value - The name you want to give the queue you are
% displaying
% theroretical = The matrix which holds all of your theroretical calculation
% data
% simulated    = The matrix which holds all of your simulated calculation
% data
% error        = The matreix which holds the error percentage beetween
% theoretical and simulated values

% Create some arrays to help display our data at the end of the simulation
spaces = ['     '; '     '; '     '; '     '; '     '; '     '; '     ';...
     '     ';  '     '; '     '; '     ';];
values = ['Queue Wait:         ' ; 'Total System Wait:  '; ...
    'Queue Ave Number:   '; 'System Ave Number:  ';...
    'Queue Throughput:   '; 'Proc Throughput:    '; 'Total Throughput:   ';...
    'Utilization:        '; 'P0:                 ';...
    'Ro:                 '; 'Blocking Prob:      '];
simTheo = ['                    ' ' Theoretical ' ' Simulated ' '  Error %'];

% Display our first server and queues result this result will be given in
% three columns first being what is being displayed second being our
% theoretical data and the third being our fourth
disp(' ' );
disp(queue);
disp(simTheo);
disp([values num2str(theoretical') spaces num2str(simulated')...
    spaces num2str(error')]);
end

