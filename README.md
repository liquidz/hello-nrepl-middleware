# hello-nrepl-middleware
[![CircleCI](https://img.shields.io/circleci/project/github/liquidz/hello-nrepl-middleware/master.svg)](https://circleci.com/gh/liquidz/hello-nrepl-middleware)
[![Dependencies Status](https://versions.deps.co/liquidz/hello-nrepl-middleware/status.svg)](https://versions.deps.co/liquidz/hello-nrepl-middleware)

[nREPL](https://github.com/nrepl/nrepl) Middleware Example

## Usage

starting test nREPL server
```
$ lein repl
user=> (go)
```

connect to test nREPL server
```
$ nc localhost 12345
d2:op5:helloe

d5:hello5:world7:session36:YOUR_SESSION_ID:statusl4:doneee
```

## License

Copyright © 2018 [Masashi Iizuka](https://twitter.com/uochan/)

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
