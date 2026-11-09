# Proxy Pattern Journal

## Phase 1
I made the MediaItem interface, RealMedia, and RemoteMediaProxy.
The proxy checks if it is connected before using RealMedia.
The interface lets the client use the same methods without
needing to know where the real object is.

## Phase 2
I made VirtualMediaProxy. It waits until a method is called
before creating RealMedia. The remote proxy uses an object
that already exists. I learned to check for null so the virtual
proxy only loads the object once.

## Phase 3
The program ran successfully. The remote proxy connected once,
and the virtual proxy loaded once. I did not find any bugs
during the test. AI helped me check that each method used
the connection or loading check.
