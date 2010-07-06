---
layout: landing
title: Reflective
---
Let's use an example where we bootstrap an instance of a repository of
resources, giving it a repository URL. Different implementations of repository
fetch resources using different protocols. This is a pluggable component of our
application. When the program starts, it reads a configuration file and all the
repositories are loaded and made ready to serve resources.

Rather than create a factory interface, we simply ask that a @Repository@
implementation have a constructor that takes a single URL.

Here is a method that does this.

The `ReflectiveException` catch block is very hard to test. You need to concoct
a test class that will raise a `RuntimeException` without somehow having that
exception wrapped in an `InvocationTargetException`. I'm not even sure if this
is possible, and if it was the case, then our wrapper exception would merely be
incorrectly wrapped.
