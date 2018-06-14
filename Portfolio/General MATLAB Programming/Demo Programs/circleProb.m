% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 5 Exam take home 2
% 1/18/18
% % Problem 4. If you place n points randomly on the sides of a square,
% they form a n-sided polygon. What is the probability that the center of
% the square falls within the polygon for n=3 to 6? You have to do at least
% 100000 random trials to determine the probability. You are welcome to use
% the inpolygon function.
% %-------------------------------------------------------------------------

% Define some viariables
polySides = [3 4 5 6]; % what polygons you want to solve prob for
shapeBuilds = 10000; % how many builds you want the more builds the more accurate the probability
probTries = 1; % how many probabilities you get then takes mean more tries equals greater accuracy
r = 1; % radius of circle
total = 0;
stepSize = .1;
ones1 = ones(1,r*2/stepSize);
% Loop through all the polygons
for k = 1:length(polySides)
    % Change color every poly just for fun
    color = rand(1,3);
    % create new figure for each poly
    figure(k);
    title([ 'An circumscribed square by a ' num2str(polySides(k)) ' sided polygon']);
    % Loop through how many probabilities you want to take the mean of
    for j = 1:probTries
        
        % Loop through shape build
        for i = 1:shapeBuilds
            
            % Clear vars to save on memory
            clearvars polyX polyY w;
            
            % Build the x and y arrays of the square
            square = -r:stepSize:r;
            thisSide = -r:stepSize:r;
            x = [-ones1 ones1 thisSide thisSide];
            y = [thisSide thisSide ones1 -ones1];
            
            % plot the square
            rectangle('Position' , [-1 -1 2 2]);
            hold on
            
            % Get random points on the border of the square
            xyRand = randperm((length(square)*4)-2,polySides(k));
            polyX = x(xyRand(:)) ;
            polyY = y(xyRand(:)) ;
            
            
            % Order those points clock wise to create a normal shhape
            % Kind of a hack job on coding shoulding be looping through
            % like this
            % always loops unless no error is thrown if an error is thrown
            % by the convhull then a new set of points are chosen
            % convhull throws error when the points chosen make a straight
            % line
            while(true)
                try
                    w = convhull(polyX,polyY);
                    break;
                catch
                    xyRand = randperm((length(square)*4)-2,polySides(k));
                    polyX = x(xyRand(:)) ;
                    polyY = y(xyRand(:)) ;
                end
            end
            
            % Organize points
            polyX = polyX(w);
            polyY = polyY(w);
            
            % Plot shape
            plot(polyX(:), polyY(:), 'color', color);
            hold off
            
            % See if 0,0 (center) is inside shape if so add to total
            total = total + inpolygon(0,0,polyX(:),polyY(:));
        end
        % Get prob by deviding times center in shape by attempts
        prob = total/shapeBuilds;
        total = 0;
    end
    % Mean of all the probs
    totalProb(k) = mean(prob);
    clearvars prob
end

%Output answer
finished(:,2) = totalProb';
finished(:,1) = polySides'



