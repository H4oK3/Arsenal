##regex:
`re.search(pattern,s)`: return first match
`re.match(pattern,s)`: return first match

`re.findall(pattern,s)`: return all matches

Example:

~~~
pattern = r"(\d+)\[([a-zA-Z]+)\]"
m = re.search(pattern, s)
while m is not None:
    num, alpha = m.groups()
~~~

`(group)`; m.groups() returns all groups match


##data structure:
[1,2,3] : list
(1,2,3) : tuple
{1,2,3} : set
{1:'a',"a":"b"} dict

- list: 

~~~
a = [1,2,3]
a.append(4)
In [7]: a
Out[7]: [1, 2, 3, 4]

In [8]: a.pop()
Out[8]: 4

In [9]: a
Out[9]: [1, 2, 3]
~~~

tuple： immutable


set: sorted and no repeatable

~~~
In [11]: a = {1,2,3}

In [12]: a
Out[12]: {1, 2, 3}

In [13]: a.add(1)

In [14]: a.remove(2)

In [15]: a
Out[15]: {1, 3}

In [16]: a.discard?
Docstring:
Remove an element from a set if it is a member.

If the element is not a member, do nothing.
Type:      builtin_function_or_method

In [17]: a.remove?
Docstring:
Remove an element from a set; it must be a member.

If the element is not a member, raise a KeyError.
Type:      builtin_function_or_method
~~~


dict: `dict.get(<key>)` or `dict[<key>]`

~~~
class Logger(object):
    def __init__(self):
        """
        Initialize your data structure here.
        """
        # history : {message1: timestamp1, xxx: xxx, ...}
        self.history = {}
        
    def shouldPrintMessage(self, timestamp, message):
        """
        Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity.
        :type timestamp: int
        :type message: str
        :rtype: bool
        """
        exist_timestamp = self.history.get(message)
        if (exist_timestamp == None):
            self.history.update({message : timestamp})
            return True
        elif (abs(exist_timestamp - timestamp) >= 10):
            self.history.update({message : timestamp})
            return True
        else:
            return False
~~~


##misc:
`range(0,3) -> [0,1,2]`

~~~
a[start:stop:step] # start through not past stop, by step
a[::-1]    # all items in the array, reversed
a[1::-1]   # the first two items, reversed
a[:-3:-1]  # the last two items, reversed
a[-3::-1]  # everything except the last two items, reversed
~~~
