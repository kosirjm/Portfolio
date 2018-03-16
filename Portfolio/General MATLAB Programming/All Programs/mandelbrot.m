% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% In class exam 2
% 1/18/18
% a modified mandelbrot function for exam 2 taken from my homework
%-------------------------------------------------------------------------
% Some of this function was found online at mathworks
% n is for linespace
% m to the power of
% top is number of iterations
% i for subplot counter
function mandelbrot(n, top, m, i)

% Define area on plotter
x0 = -1;   
x1 = 1;
y0 = -1; 
y1 = 1;

% meshgrid (found online)
[x,y] = meshgrid(linspace(x0, x1, n), linspace(y0, y1, n));

% Define variables  for upcoming loop
c = x + 1i * y;
z = zeros(size(c));
k = zeros(size(c));

% do the mandelbrot function algorithm (algorithm found online)
for j = 1:top
    z   = z.^m + c;
    k(abs(z) > 2 & k == 0) = top - j;
end

% Subplot so all images are on one figure
subplot(2,2,i);

% define own map black inside white outside
map = [0,0,0
    1,1,1]

% Display
imagesc(k),
colormap(map)
axis square