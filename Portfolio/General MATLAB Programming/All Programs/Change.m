% Jonathan Kosir
% Homework for ECE 302 Matlab and its engineering applications
% Problem 4.7
% 1/3/18
% Change Calculator
%-------------------------------------------------------------------------
%Variables
hundred = 0;
fifty = 0;
twenty = 0;
ten = 0;
five = 0;
one = 0;
quarter = 0;
dime = 0;
nickle = 0;
penny = 0;

% User Input
purchaseCost = input('Please input how much transaction is: ');
bill  = input('Please input bill size: ');

% Work in pennies
bill = round(bill * 100);
purchaseCost = round(purchaseCost * 100);
rema = bill - purchaseCost;
diff = bill - purchaseCost;

% One Hundred
if(rema >= 10000)
hundred = fix(rema/10000);
rema = rem(rema,10000);
end 

% fifty
if(rema >= 5000)
fifty= fix(rema/5000);
rema = rem(rema,5000);
end

% Twenties
if(rema>=2000)
twenties = fix(rema/2000);
rema = rem(rema,2000);
end

% Ten
if(rema>=1000)
ten = fix(rema/1000);
rema = rem(rema,1000);
end

% Five
if(rema>=500)
five = fix(rema/500);
rema = rem(rema,500);
end

% One
if(rema>=100)
one = fix(rema/100);
rema = rem(rema,100);
end

% quarter
if(rema>=25)
quarter = fix(rema/25);
rema = rem(rema,25);
end

% dime
if(rema>=10)
dime = fix(rema/10);
rema = rem(rema,10)
end

% Nickle
if(rema>=5)
nickle = fix(rema/5);
rema = rem(rema,5);
end

% Penny
if(rema>=1)
penny = fix(rema/1);
rema = rem(rema,1);
end

% Display
disp(['Change meeded: ' num2str(diff/100)])
disp(['Bills: ' num2str(hundred) ' One-Hundreds,' num2str(fifty) ' fifties, ' num2str(twenty) ' twenties, ' num2str(ten) ' tens, ' num2str(five) ' fives, ' num2str(one) ' ones, ' num2str(quarter) ' quarters, ' num2str(dime) ' dimes, ' num2str(nickle) ' nickels, ' num2str(penny) ' pennies.'])