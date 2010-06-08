---
layout: default
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
