YUI.add("node-base",
				function(C) {
					var G = ".", E = "nodeName", I = "nodeType", B = "ownerDocument", H = "tagName", D = "_yuid", F = function(
							L) {
						var K = L[D];
						if (K && F._instances[K] && F._instances[K]._node !== L) {
							L[D] = null;
						}
						K = C.stamp(L);
						if (!K) {
							K = C.guid();
						}
						this[D] = K;
						this._node = L;
						F._instances[K] = this;
						this._stateProxy = L;
						if (this._initPlugins) {
							this._initPlugins();
						}
					}, J = function(L) {
						var K = null;
						if (L) {
							K = (typeof L === "string") ? function(M) {
								return C.Selector.test(M, L);
							} : function(M) {
								return L(F.get(M));
							};
						}
						return K;
					};
					F.NAME = "Node";
					F.re_aria = /^(?:role$|aria-)/;
					F.DOM_EVENTS = {
						abort : true,
						beforeunload : true,
						blur : true,
						change : true,
						click : true,
						close : true,
						command : true,
						contextmenu : true,
						drag : true,
						dragstart : true,
						dragenter : true,
						dragover : true,
						dragleave : true,
						dragend : true,
						drop : true,
						dblclick : true,
						error : true,
						focus : true,
						keydown : true,
						keypress : true,
						keyup : true,
						load : true,
						message : true,
						mousedown : true,
						mousemove : true,
						mouseout : true,
						mouseover : true,
						mouseup : true,
						mousemultiwheel : true,
						mousewheel : true,
						submit : true,
						mouseenter : true,
						mouseleave : true,
						scroll : true,
						reset : true,
						resize : true,
						select : true,
						textInput : true,
						unload : true
					};
					C.mix(F.DOM_EVENTS, C.Env.evt.plugins);
					F._instances = {};
					F.getDOMNode = function(K) {
						if (K) {
							return (K.nodeType) ? K : K._node || null;
						}
						return null;
					};
					F.scrubVal = function(L, K) {
						if (K && L) {
							if (typeof L === "object"
									|| typeof L === "function") {
								if (I in L || C.DOM.isWindow(L)) {
									L = F.get(L);
								} else {
									if ((L.item && !L._nodes)
											|| (L[0] && L[0][I])) {
										L = C.all(L);
									}
								}
							}
						} else {
							if (L === undefined) {
								L = K;
							}
						}
						return L;
					};
					F.addMethod = function(K, M, L) {
						if (K && M && typeof M === "function") {
							F.prototype[K] = function() {
								L = L || this;
								var O = C.Array(arguments), N;
								if (O[0] && O[0] instanceof F) {
									O[0] = O[0]._node;
								}
								if (O[1] && O[1] instanceof F) {
									O[1] = O[1]._node;
								}
								O.unshift(this._node);
								N = F.scrubVal(M.apply(L, O), this);
								return N;
							};
						} else {
						}
					};
					F.importMethod = function(M, K, L) {
						if (typeof K === "string") {
							L = L || K;
							F.addMethod(L, M[K], M);
						} else {
							C.each(K, function(N) {
								F.importMethod(M, N);
							});
						}
					};
					F.one = function(N) {
						var K = null, M, L;
						if (N) {
							if (typeof N === "string") {
								if (N.indexOf("doc") === 0) {
									N = C.config.doc;
								} else {
									if (N.indexOf("win") === 0) {
										N = C.config.win;
									} else {
										N = C.Selector.query(N, null, true);
									}
								}
								if (!N) {
									return null;
								}
							} else {
								if (N instanceof F) {
									return N;
								}
							}
							L = N._yuid;
							K = F._instances[L];
							M = K ? K._node : null;
							if (!K || (M && N !== M)) {
								K = new F(N);
							}
						}
						return K;
					};
					F.get = function() {
						return F.one.apply(F, arguments);
					};
					F.create = function() {
						return F.get(C.DOM.create.apply(C.DOM, arguments));
					};
					F.ATTRS = {
						text : {
							getter : function() {
								return C.DOM.getText(this._node);
							},
							setter : function(K) {
								C.DOM.setText(this._node, K);
								return K;
							}
						},
						"options" : {
							getter : function() {
								return this._node
										.getElementsByTagName("option");
							}
						},
						"elements" : {
							getter : function() {
								return C.all(this._node.elements);
							}
						},
						"children" : {
							getter : function() {
								var N = this._node, M = N.children, O, L, K;
								if (!M) {
									O = N.childNodes;
									M = [];
									for (L = 0, K = O.length; L < K; ++L) {
										if (O[L][H]) {
											M[M.length] = O[L];
										}
									}
								}
								return C.all(M);
							}
						},
						value : {
							getter : function() {
								return C.DOM.getValue(this._node);
							},
							setter : function(K) {
								C.DOM.setValue(this._node, K);
								return K;
							}
						},
						data : {
							getter : function() {
								return this._data;
							},
							setter : function(K) {
								this._data = K;
								return K;
							}
						}
					};
					F.DEFAULT_SETTER = function(K, M) {
						var L = this._stateProxy, N;
						if (K.indexOf(G) > -1) {
							N = K;
							K = K.split(G);
							C.Object.setValue(L, K, M);
						} else {
							if (L[K] !== undefined) {
								L[K] = M;
							}
						}
						return M;
					};
					F.DEFAULT_GETTER = function(K) {
						var L = this._stateProxy, M;
						if (K.indexOf && K.indexOf(G) > -1) {
							M = C.Object.getValue(L, K.split(G));
						} else {
							if (L[K] !== undefined) {
								M = L[K];
							}
						}
						return M;
					};
					C.augment(F, C.Event.Target);
					C
							.mix(
									F.prototype,
									{
										toString : function() {
											var M = "", L = this[D]
													+ ": not bound to a node", K = this._node;
											if (K) {
												M += K[E];
												if (K.id) {
													M += "#" + K.id;
												}
												if (K.className) {
													M += "."
															+ K.className
																	.replace(
																			" ",
																			".");
												}
												M += " " + this[D];
											}
											return M || L;
										},
										get : function(K) {
											var L;
											if (this._getAttr) {
												L = this._getAttr(K);
											} else {
												L = this._get(K);
											}
											if (L) {
												L = C.Node.scrubVal(L, this);
											}
											return L;
										},
										_get : function(K) {
											var L = F.ATTRS[K], M;
											if (L && L.getter) {
												M = L.getter.call(this);
											} else {
												if (F.re_aria.test(K)) {
													M = this._node
															.getAttribute(K, 2);
												} else {
													M = F.DEFAULT_GETTER.apply(
															this, arguments);
												}
											}
											return M;
										},
										set : function(K, M) {
											var L = F.ATTRS[K];
											if (this._setAttr) {
												this._setAttr.apply(this,
														arguments);
											} else {
												if (L && L.setter) {
													L.setter.call(this, M);
												} else {
													if (F.re_aria.test(K)) {
														this._node
																.setAttribute(
																		K, M);
													} else {
														F.DEFAULT_SETTER
																.apply(this,
																		arguments);
													}
												}
											}
											return this;
										},
										setAttrs : function(K) {
											if (this._setAttrs) {
												this._setAttrs(K);
											} else {
												C.Object.each(K,
														function(L, M) {
															this.set(M, L);
														}, this);
											}
											return this;
										},
										getAttrs : function(L) {
											var K = {};
											if (this._getAttrs) {
												this._getAttrs(L);
											} else {
												C.Array.each(L, function(M, N) {
													K[M] = this.get(M);
												}, this);
											}
											return K;
										},
										create : F.create,
										compareTo : function(K) {
											var L = this._node;
											if (K instanceof C.Node) {
												K = K._node;
											}
											return L === K;
										},
										inDoc : function(L) {
											var K = this._node;
											L = (L) ? L._node || L : K[B];
											if (L.documentElement) {
												return C.DOM.contains(
														L.documentElement, K);
											}
										},
										getById : function(M) {
											var L = this._node, K = C.DOM.byId(
													M, L[B]);
											if (K && C.DOM.contains(L, K)) {
												K = C.one(K);
											} else {
												K = null;
											}
											return K;
										},
										ancestor : function(K) {
											return F.get(C.DOM.elementByAxis(
													this._node, "parentNode",
													J(K)));
										},
										previous : function(L, K) {
											return F
													.get(C.DOM.elementByAxis(
															this._node,
															"previousSibling",
															J(L), K));
										},
										next : function(M, L, K) {
											return F.get(C.DOM.elementByAxis(
													this._node, "nextSibling",
													J(L), K));
										},
										one : function(K) {
											return C.one(C.Selector.query(K,
													this._node, true));
										},
										query : function(K) {
											return this.one(K);
										},
										all : function(K) {
											var L = C.all(C.Selector.query(K,
													this._node));
											L._query = K;
											return L;
										},
										queryAll : function(K) {
											return this.all(K);
										},
										test : function(K) {
											return C.Selector.test(this._node,
													K);
										},
										remove : function(K) {
											var L = this._node;
											L.parentNode.removeChild(L);
											if (K) {
												this.destroy(true);
											}
											return this;
										},
										replace : function(K) {
											var L = this._node;
											L.parentNode.replaceChild(K, L);
											return this;
										},
										purge : function(L, K) {
											C.Event.purgeElement(this._node, L,
													K);
										},
										destroy : function(K) {
											delete F._instances[this[D]];
											if (K) {
												this.purge(true);
											}
											if (this.unplug) {
												this.unplug();
											}
											this._node._yuid = null;
											this._node = null;
											this._stateProxy = null;
										},
										invoke : function(R, L, K, Q, P, O) {
											var N = this._node, M;
											if (L && L instanceof C.Node) {
												L = L._node;
											}
											if (K && K instanceof C.Node) {
												K = K._node;
											}
											M = N[R](L, K, Q, P, O);
											return C.Node.scrubVal(M, this);
										},
										each : function(L, K) {
											K = K || this;
											return L.call(K, this);
										},
										item : function(K) {
											return this;
										},
										size : function() {
											return this._node ? 1 : 0;
										},
										insert : function(M, K) {
											var L = this._node;
											if (M) {
												if (typeof K === "number") {
													K = this._node.childNodes[K];
												}
												if (typeof M !== "string") {
													if (M._node) {
														M = M._node;
													} else {
														if (M._nodes
																|| (!M.nodeType && M.length)) {
															C
																	.each(
																			M._nodes,
																			function(
																					N) {
																				C.DOM
																						.addHTML(
																								L,
																								N,
																								K);
																			});
															return this;
														}
													}
												}
												C.DOM.addHTML(L, M, K);
											}
											return this;
										},
										prepend : function(K) {
											return this.insert(K, 0);
										},
										append : function(K) {
											return this.insert(K, null);
										},
										setContent : function(K) {
											C.DOM.addHTML(this._node, K,
													"replace");
											return this;
										},
										hasMethod : function(L) {
											var K = this._node;
											return (K && (typeof K === "function"));
										}
									}, true);
					C.Node = F;
					C.get = C.Node.get;
					C.one = C.Node.one;
					var A = function(K) {
						if (typeof K === "string") {
							this._query = K;
							K = C.Selector.query(K);
						} else {
							K = C.Array(K, 0, true);
						}
						A._instances[C.stamp(this)] = this;
						this._nodes = K;
					};
					A.NAME = "NodeList";
					A.getDOMNodes = function(K) {
						return K._nodes;
					};
					A._instances = [];
					A.each = function(K, N, M) {
						var L = K._nodes;
						if (L && L.length) {
							C.Array.each(L, N, M || K);
						} else {
						}
					};
					A.addMethod = function(K, M, L) {
						if (K && M) {
							A.prototype[K] = function() {
								var O = [], N = arguments;
								C.Array
										.each(
												this._nodes,
												function(T) {
													var S = "_yuid", Q = C.Node._instances[T[S]], R, P;
													if (!Q) {
														Q = A._getTempNode(T);
													}
													R = L || Q;
													P = M.apply(R, N);
													if (P !== undefined
															&& P !== Q) {
														O[O.length] = P;
													}
												});
								return O.length ? O : this;
							};
						} else {
						}
					};
					A.importMethod = function(M, K, L) {
						if (typeof K === "string") {
							L = L || K;
							A.addMethod(K, M[K]);
						} else {
							C.each(K, function(N) {
								A.importMethod(M, N);
							});
						}
					};
					A._getTempNode = function(L) {
						var K = A._tempNode;
						if (!K) {
							K = C.Node.create("<div></div>");
							A._tempNode = K;
						}
						K._node = L;
						K._stateProxy = L;
						return K;
					};
					C
							.mix(
									A.prototype,
									{
										item : function(K) {
											return C
													.one((this._nodes || [])[K]);
										},
										each : function(M, L) {
											var K = this;
											C.Array.each(this._nodes, function(
													O, N) {
												O = C.one(O);
												return M.call(L || O, O, N, K);
											});
											return K;
										},
										batch : function(L, K) {
											var M = this;
											C.Array
													.each(
															this._nodes,
															function(P, O) {
																var N = C.Node._instances[P[D]];
																if (!N) {
																	N = A
																			._getTempNode(P);
																}
																return L
																		.call(
																				K
																						|| N,
																				N,
																				O,
																				M);
															});
											return M;
										},
										some : function(M, L) {
											var K = this;
											return C.Array.some(this._nodes,
													function(O, N) {
														O = C.one(O);
														L = L || O;
														return M.call(L, O, N,
																K);
													});
										},
										toFrag : function() {
											return C.one(C.DOM
													._nl2frag(this._nodes));
										},
										indexOf : function(K) {
											return C.Array.indexOf(this._nodes,
													C.Node.getDOMNode(K));
										},
										filter : function(K) {
											return C.all(C.Selector.filter(
													this._nodes, K));
										},
										modulus : function(M, L) {
											L = L || 0;
											var K = [];
											A.each(this, function(O, N) {
												if (N % M === L) {
													K.push(O);
												}
											});
											return C.all(K);
										},
										odd : function() {
											return this.modulus(2, 1);
										},
										even : function() {
											return this.modulus(2);
										},
										destructor : function() {
											delete A._instances[this[D]];
										},
										refresh : function() {
											var L, K = this._nodes;
											if (this._query) {
												if (K && K[0]
														&& K[0].ownerDocument) {
													L = K[0].ownerDocument;
												}
												this._nodes = C.Selector
														.query(this._query, L
																|| C.config.doc);
											}
											return this;
										},
										on : function(N, M, L) {
											var K = C.Array(arguments, 0, true);
											K.splice(2, 0, this._nodes);
											K[3] = L || this;
											return C.on.apply(C, K);
										},
										after : function(N, M, L) {
											var K = C.Array(arguments, 0, true);
											K.splice(2, 0, this._nodes);
											K[3] = L || this;
											return C.after.apply(C, K);
										},
										size : function() {
											return this._nodes.length;
										},
										toString : function() {
											var N = "", M = this[D]
													+ ": not bound to any nodes", K = this._nodes, L;
											if (K && K[0]) {
												L = K[0];
												N += L[E];
												if (L.id) {
													N += "#" + L.id;
												}
												if (L.className) {
													N += "."
															+ L.className
																	.replace(
																			" ",
																			".");
												}
												if (K.length > 1) {
													N += "...[" + K.length
															+ " items]";
												}
											}
											return N || M;
										}
									}, true);
					A.importMethod(C.Node.prototype, [ "append", "detach",
							"detachAll", "insert", "prepend", "remove", "set",
							"setContent" ]);
					A.prototype.get = function(L) {
						var O = [], N = this._nodes, M = false, P = A._getTempNode, K, Q;
						if (N[0]) {
							K = C.Node._instances[N[0]._yuid] || P(N[0]);
							Q = K._get(L);
							if (Q && Q.nodeType) {
								M = true;
							}
						}
						C.Array.each(N, function(R) {
							K = C.Node._instances[R._yuid];
							if (!K) {
								K = P(R);
							}
							Q = K._get(L);
							if (!M) {
								Q = C.Node.scrubVal(Q, K);
							}
							O.push(Q);
						});
						return (M) ? C.all(O) : O;
					};
					C.NodeList = A;
					C.all = function(K) {
						return new A(K);
					};
					C.Node.all = C.all;
					C.Array.each( [ "replaceChild", "appendChild",
							"insertBefore", "removeChild", "hasChildNodes",
							"cloneNode", "hasAttribute", "removeAttribute",
							"scrollIntoView", "getElementsByTagName", "focus",
							"blur", "submit", "reset", "select" ], function(K) {
						C.Node.prototype[K] = function(O, M, L) {
							var N = this.invoke(K, O, M, L);
							return N;
						};
					});
					F.importMethod(C.DOM, [ "contains", "setAttribute",
							"getAttribute" ]);
					C.NodeList.importMethod(C.Node.prototype, [ "getAttribute",
							"setAttribute" ]);
					(function(L) {
						var K = [ "hasClass", "addClass", "removeClass",
								"replaceClass", "toggleClass" ];
						L.Node.importMethod(L.DOM, K);
						L.NodeList.importMethod(L.Node.prototype, K);
					})(C);
					if (!document.documentElement.hasAttribute) {
						C.Node.prototype.hasAttribute = function(K) {
							return C.DOM.getAttribute(this._node, K) !== "";
						};
					}
					C.Node.ATTRS.type = {
						setter : function(L) {
							if (L === "hidden") {
								try {
									this._node.type = "hidden";
								} catch (K) {
									this.setStyle("display", "none");
									this._inputType = "hidden";
								}
							} else {
								try {
									this._node.type = L;
								} catch (K) {
								}
							}
							return L;
						},
						getter : function() {
							return this._inputType || this._node.type;
						},
						_bypassProxy : true
					};
				}, "3.0.0", {
					requires : [ "dom-base", "event-base" ]
	});

YUI	.add("gallery-treeview",
				function(A) {
					(function() {
						var B = A.Port(), E = A.DOM, J = B.util.Event, G = B.lang, F = {
							ALT : 18,
							BACK_SPACE : 8,
							CAPS_LOCK : 20,
							CONTROL : 17,
							DELETE : 46,
							DOWN : 40,
							END : 35,
							ENTER : 13,
							ESCAPE : 27,
							HOME : 36,
							LEFT : 37,
							META : 224,
							NUM_LOCK : 144,
							PAGE_DOWN : 34,
							PAGE_UP : 33,
							PAUSE : 19,
							PRINTSCREEN : 44,
							RIGHT : 39,
							SCROLL_LOCK : 145,
							SHIFT : 16,
							SPACE : 32,
							TAB : 9,
							UP : 38
						};
						B.widget = B.widget || {};
						var H = B.widget;
						var C = function(L, K) {
							C.superclass.constructor.call(this);
							if (L) {
								this._treeinit(L);
							}
							if (K) {
								this.buildTreeFromObject(K);
							} else {
								if (G.trim(this._el.innerHTML)) {
									this.buildTreeFromMarkup(L);
								}
							}
						};
						C.KEY = F;
						H.TreeView = C;
						var D = A.namespace("apm");
						D.TreeView = C;
						A
								.extend(
										C,
										A.PortBase,
										{
											id : null,
											_el : null,
											_nodes : null,
											locked : false,
											_expandAnim : null,
											_collapseAnim : null,
											_animCount : 0,
											maxAnim : 2,
											_hasDblClickSubscriber : false,
											_dblClickTimer : null,
											currentFocus : null,
											singleNodeHighlight : false,
											_currentlyHighlighted : null,
											setExpandAnim : function(K) {
												this._expandAnim = (H.TVAnim
														.isValid(K)) ? K : null;
											},
											setCollapseAnim : function(K) {
												this._collapseAnim = (H.TVAnim
														.isValid(K)) ? K : null;
											},
											animateExpand : function(M, N) {
												if (this._expandAnim
														&& this._animCount < this.maxAnim) {
													var K = this;
													var L = H.TVAnim
															.getAnim(
																	this._expandAnim,
																	M,
																	function() {
																		K
																				.expandComplete(N);
																	});
													if (L) {
														++this._animCount;
														this
																.fire(
																		"animStart",
																		{
																			"node" : N,
																			"type" : "expand"
																		});
														L.animate();
													}
													return true;
												}
												return false;
											},
											animateCollapse : function(M, N) {
												if (this._collapseAnim
														&& this._animCount < this.maxAnim) {
													var K = this;
													var L = H.TVAnim
															.getAnim(
																	this._collapseAnim,
																	M,
																	function() {
																		K
																				.collapseComplete(N);
																	});
													if (L) {
														++this._animCount;
														this
																.fire(
																		"animStart",
																		{
																			"node" : N,
																			"type" : "collapse"
																		});
														L.animate();
													}
													return true;
												}
												return false;
											},
											expandComplete : function(K) {
												--this._animCount;
												this.fire("animComplete", {
													"node" : K,
													"type" : "expand"
												});
											},
											collapseComplete : function(K) {
												--this._animCount;
												this.fire("animComplete", {
													"node" : K,
													"type" : "collapse"
												});
											},
											_treeinit : function(L) {
												this._el = A.Selector.query("#"
														+ L, null, true);
												this.id = A.guid(this._el,
														"yui-tv-auto-id-");
												this.publish("animStart", this);
												this.publish("animComplete",
														this);
												this.publish("collapse", this);
												this.publish(
														"collapseComplete",
														this);
												this.publish("expand", this);
												this.publish("expandComplete",
														this);
												this.publish("enterKeyPressed",
														this);
												this
														.publish("clickEvent",
																this);
												this.publish("focusChanged",
														this);
												var K = this;
												this
														.publish(
																"dblClickEvent",
																{
																	onSubscribeCallback : function() {
																		K._hasDblClickSubscriber = true;
																	}
																});
												this
														.publish("labelClick",
																this);
												this.publish("highlightEvent",
														this);
												this._nodes = [];
												C.trees[this.id] = this;
												this.root = new H.RootNode(this);
												if (this._initEditor) {
													this._initEditor();
												}
											},
											buildTreeFromObject : function(K) {
												var L = function(T, Q) {
													var P, U, O, N, S, M, R;
													for (P = 0; P < Q.length; P++) {
														U = Q[P];
														if (G.isString(U)) {
															O = new H.TextNode(
																	U, T);
														} else {
															if (G.isObject(U)) {
																N = U.children;
																delete U.children;
																S = U.type
																		|| "text";
																delete U.type;
																switch (G
																		.isString(S)
																		&& S
																				.toLowerCase()) {
																case "text":
																	O = new H.TextNode(
																			U,
																			T);
																	break;
																case "menu":
																	O = new H.MenuNode(
																			U,
																			T);
																	break;
																case "html":
																	O = new H.HTMLNode(
																			U,
																			T);
																	break;
																default:
																	if (G
																			.isString(S)) {
																		M = H[S];
																	} else {
																		M = S;
																	}
																	if (G
																			.isObject(M)) {
																		for (R = M; R
																				&& R !== H.Node; R = R.superclass.constructor) {
																		}
																		if (R) {
																			O = new M(
																					U,
																					T);
																		} else {
																		}
																	} else {
																	}
																}
																if (N) {
																	L(O, N);
																}
															} else {
															}
														}
													}
												};
												if (!G.isArray(K)) {
													K = [ K ];
												}
												L(this.root, K);
											},
											buildTreeFromMarkup : function(M) {
												var L = function(N) {
													var R, U, Q = [], P = {}, O, S;
													for (R = E.getFirstChild(N); R; R = E
															.getNextSibling(R)) {
														switch (R.tagName
																.toUpperCase()) {
														case "LI":
															O = "";
															P = {
																expanded : E
																		.hasClass(
																				R,
																				"expanded"),
																title : R.title
																		|| R.alt
																		|| null,
																className : G
																		.trim(R.className
																				.replace(
																						/\bexpanded\b/,
																						""))
																		|| null
															};
															U = R.firstChild;
															if (U.nodeType == 3) {
																O = G
																		.trim(U.nodeValue
																				.replace(
																						/[\n\t\r]*/g,
																						""));
																if (O) {
																	P.type = "text";
																	P.label = O;
																} else {
																	U = E
																			.getNextSibling(U);
																}
															}
															if (!O) {
																if (U.tagName
																		.toUpperCase() == "A") {
																	P.type = "text";
																	P.label = U.innerHTML;
																	P.href = U.href;
																	P.target = U.target;
																	P.title = U.title
																			|| U.alt
																			|| P.title;
																} else {
																	P.type = "html";
																	var T = document
																			.createElement("div");
																	T
																			.appendChild(U
																					.cloneNode(true));
																	P.html = T.innerHTML;
																	P.hasIcon = true;
																}
															}
															U = E
																	.getNextSibling(U);
															switch (U
																	&& U.tagName
																			.toUpperCase()) {
															case "UL":
															case "OL":
																P.children = L(U);
																break;
															}
															if (B.lang.JSON) {
																S = R
																		.getAttribute("yuiConfig");
																if (S) {
																	S = B.lang.JSON
																			.parse(S);
																	P = B.lang
																			.merge(
																					P,
																					S);
																}
															}
															Q.push(P);
															break;
														case "UL":
														case "OL":
															P = {
																type : "text",
																label : "",
																children : L(U)
															};
															Q.push(P);
															break;
														}
													}
													return Q;
												};
												var K = A.Selector.query("#"
														+ this.id + "> ul,ol");
												if (K.length) {
													this
															.buildTreeFromObject(L(K[0]));
												} else {
												}
											},
											_getEventTargetTdEl : function(M) {
												var O = M.target, L = "td .ygtvrow", P, N = O
														.get("parentNode");
												if (O
														&& N
														&& !N
																.hasClass("ygtvrow")) {
													O = O.ancestor("td");
													N = O
															&& O
																	.get("parentNode");
												}
												if (!O) {
													return null;
												}
												P = O._node || O;
												if (/\bygtv(blank)?depthcell/
														.test(P.className)) {
													return null;
												}
												if (P.id) {
													var K = P.id
															.match(/\bygtv([^\d]*)(.*)/);
													if (K
															&& K[2]
															&& this._nodes[K[2]]) {
														return P;
													}
												}
												return null;
											},
											_onClickEvent : function(N) {
												var L = this, P = this
														._getEventTargetTdEl(N), M, O, K = function(
														Q) {
													M.focus();
													if (Q || !M.href) {
														M.toggle();
														try {
															N.preventDefault();
														} catch (R) {
														}
													}
												};
												if (!P) {
													return;
												}
												M = this.getNodeByElement(P);
												if (!M) {
													return;
												}
												O = N.target;
												if (O.hasClass(M.labelStyle)
														|| O
																.ancestor(M.labelStyle)) {
													this.fire("labelClick", M);
												}
												if (/\bygtv[tl][mp]h?h?/
														.test(P.className)) {
													K(true);
												} else {
													if (this._dblClickTimer) {
														window
																.clearTimeout(this._dblClickTimer);
														this._dblClickTimer = null;
													} else {
														if (this._hasDblClickSubscriber) {
															this._dblClickTimer = window
																	.setTimeout(
																			function() {
																				L._dblClickTimer = null;
																				if (L
																						.fire(
																								"clickEvent",
																								{
																									event : N,
																									node : M
																								}) !== false) {
																					K();
																				}
																			},
																			200);
														} else {
															if (L
																	.fire(
																			"clickEvent",
																			{
																				event : N,
																				node : M
																			}) !== false) {
																K();
															}
														}
													}
												}
											},
											_onDblClickEvent : function(K) {
												if (!this._hasDblClickSubscriber) {
													return;
												}
												var L = this
														._getEventTargetTdEl(K);
												if (!L) {
													return;
												}
												if (!(/\bygtv[tl][mp]h?h?/
														.test(L.className))) {
													this
															.fire(
																	"dblClickEvent",
																	{
																		event : K,
																		node : this
																				.getNodeByElement(L)
																	});
													if (this._dblClickTimer) {
														window
																.clearTimeout(this._dblClickTimer);
														this._dblClickTimer = null;
													}
												}
											},
											_onMouseOverEvent : function(K) {
												var L;
												if ((L = this
														._getEventTargetTdEl(K))
														&& (L = this
																.getNodeByElement(L))
														&& (L = L.getToggleEl())) {
													L.className = L.className
															.replace(
																	/\bygtv([lt])([mp])\b/gi,
																	"ygtv$1$2h");
												}
											},
											_onMouseOutEvent : function(K) {
												var L;
												if ((L = this
														._getEventTargetTdEl(K))
														&& (L = this
																.getNodeByElement(L))
														&& (L = L.getToggleEl())) {
													L.className = L.className
															.replace(
																	/\bygtv([lt])([mp])h\b/gi,
																	"ygtv$1$2");
												}
											},
											_onKeyDownEvent : function(O) {
												var Q = O.target, R = Q._node, N = this
														.getNodeByElement(R), M = N;
												switch (O.keyCode) {
												case F.UP:
													do {
														if (M.previousSibling) {
															M = M.previousSibling;
														} else {
															M = M.parent;
														}
													} while (M
															&& !M
																	._canHaveFocus());
													if (M) {
														M.focus();
													}
													O.preventDefault();
													break;
												case F.DOWN:
													do {
														if (M.nextSibling) {
															M = M.nextSibling;
														} else {
															M.expand();
															M = (M.children.length || null)
																	&& M.children[0];
														}
													} while (M
															&& !M._canHaveFocus);
													if (M) {
														M.focus();
													}
													O.preventDefault();
													break;
												case F.LEFT:
													do {
														if (M.parent) {
															M = M.parent;
														} else {
															M = M.previousSibling;
														}
													} while (M
															&& !M
																	._canHaveFocus());
													if (M) {
														M.focus();
													}
													O.preventDefault();
													break;
												case F.RIGHT:
													var L = this, P, K = function(
															S) {
														L
																.unsubscribe(
																		"expandComplete",
																		K);
														P(S);
													};
													P = function(S) {
														do {
															if (S.isDynamic()
																	&& !S.childrenRendered) {
																L
																		.subscribe(
																				"expandComplete",
																				K);
																S.expand();
																S = null;
																break;
															} else {
																S.expand();
																if (S.children.length) {
																	S = S.children[0];
																} else {
																	S = S.nextSibling;
																}
															}
														} while (S
																&& !S
																		._canHaveFocus());
														if (S) {
															S.focus();
														}
													};
													P(M);
													O.preventDefault();
													break;
												case F.ENTER:
													if (N.href) {
														if (N.target) {
															window.open(N.href,
																	N.target);
														} else {
															window
																	.location(N.href);
														}
													} else {
														N.toggle();
													}
													this.fire(
															"enterKeyPressed",
															N);
													O.preventDefault();
													break;
												case F.HOME:
													M = this.getRoot();
													if (M.children.length) {
														M = M.children[0];
													}
													if (M._canHaveFocus()) {
														M.focus();
													}
													O.preventDefault();
													break;
												case F.END:
													M = M.parent.children;
													M = M[M.length - 1];
													if (M._canHaveFocus()) {
														M.focus();
													}
													O.preventDefault();
													break;
												case 107:
													if (O.shiftKey) {
														N.parent.expandAll();
													} else {
														N.expand();
													}
													break;
												case 109:
													if (O.shiftKey) {
														N.parent.collapseAll();
													} else {
														N.collapse();
													}
													break;
												default:
													break;
												}
											},
											render : function() {
												var K = this.root.getHtml(), L = this
														.getEl();
												L.innerHTML = K;
												if (!this._hasEvents) {
													A.on("click",
															this._onClickEvent,
															L, this);
													A
															.on(
																	"dblclick",
																	this._onDblClickEvent,
																	L, this,
																	true);
													A
															.on(
																	"mouseover",
																	this._onMouseOverEvent,
																	L, this,
																	true);
													A
															.on(
																	"mouseout",
																	this._onMouseOutEvent,
																	L, this,
																	true);
													A
															.on(
																	"keydown",
																	this._onKeyDownEvent,
																	L, this,
																	true);
												}
												this._hasEvents = true;
											},
											getEl : function() {
												if (!this._el) {
													var K = A.Selector.query(
															"#" + this.id,
															null, true);
													this._el = K;
												}
												return this._el;
											},
											regNode : function(K) {
												this._nodes[K.index] = K;
											},
											getRoot : function() {
												return this.root;
											},
											setDynamicLoad : function(K, L) {
												this.root.setDynamicLoad(K, L);
											},
											expandAll : function() {
												if (!this.locked) {
													this.root.expandAll();
												}
											},
											collapseAll : function() {
												if (!this.locked) {
													this.root.collapseAll();
												}
											},
											getNodeByIndex : function(L) {
												var K = this._nodes[L];
												return (K) ? K : null;
											},
											getNodeByProperty : function(M, L) {
												for ( var K in this._nodes) {
													if (this._nodes
															.hasOwnProperty(K)) {
														var N = this._nodes[K];
														if ((M in N && N[M] == L)
																|| (N.data && L == N.data[M])) {
															return N;
														}
													}
												}
												return null;
											},
											getNodesByProperty : function(N, M) {
												var K = [];
												for ( var L in this._nodes) {
													if (this._nodes
															.hasOwnProperty(L)) {
														var O = this._nodes[L];
														if ((N in O && O[N] == M)
																|| (O.data && M == O.data[N])) {
															K.push(O);
														}
													}
												}
												return (K.length) ? K : null;
											},
											getNodesBy : function(M) {
												var K = [];
												for ( var L in this._nodes) {
													if (this._nodes
															.hasOwnProperty(L)) {
														var N = this._nodes[L];
														if (M(N)) {
															K.push(N);
														}
													}
												}
												return (K.length) ? K : null;
											},
											getNodeByElement : function(M) {
												var N = M, K, L = /ygtv([^\d]*)(.*)/;
												do {
													if (N && N.id) {
														K = N.id.match(L);
														if (K && K[2]) {
															return this
																	.getNodeByIndex(K[2]);
														}
													}
													N = N.parentNode;
													if (!N || !N.tagName) {
														break;
													}
												} while (N.id !== this.id
														&& N.tagName
																.toLowerCase() !== "body");
												return null;
											},
											getHighlightedNode : function() {
												return this._currentlyHighlighted;
											},
											removeNode : function(L, K) {
												if (L.isRoot()) {
													return false;
												}
												var M = L.parent;
												if (M.parent) {
													M = M.parent;
												}
												this._deleteNode(L);
												if (K && M
														&& M.childrenRendered) {
													M.refresh();
												}
												return true;
											},
											_removeChildren_animComplete : function(
													K) {
												this
														.unsubscribe(this._removeChildren_animComplete);
												this.removeChildren(K.node);
											},
											removeChildren : function(K) {
												if (K.expanded) {
													if (this._collapseAnim) {
														this
																.subscribe(
																		"animComplete",
																		this._removeChildren_animComplete,
																		this,
																		true);
														H.Node.prototype.collapse
																.call(K);
														return;
													}
													K.collapse();
												}
												while (K.children.length) {
													this
															._deleteNode(K.children[0]);
												}
												if (K.isRoot()) {
													H.Node.prototype.expand
															.call(K);
												}
												K.childrenRendered = false;
												K.dynamicLoadComplete = false;
												K.updateIcon();
											},
											_deleteNode : function(K) {
												this.removeChildren(K);
												this.popNode(K);
											},
											popNode : function(N) {
												var O = N.parent;
												var L = [];
												for ( var M = 0, K = O.children.length; M < K; ++M) {
													if (O.children[M] != N) {
														L[L.length] = O.children[M];
													}
												}
												O.children = L;
												O.childrenRendered = false;
												if (N.previousSibling) {
													N.previousSibling.nextSibling = N.nextSibling;
												}
												if (N.nextSibling) {
													N.nextSibling.previousSibling = N.previousSibling;
												}
												if (this.currentFocus == N) {
													this.currentFocus = null;
												}
												if (this._currentlyHighlighted == N) {
													this._currentlyHighlighted = null;
												}
												N.parent = null;
												N.previousSibling = null;
												N.nextSibling = null;
												N.tree = null;
												delete this._nodes[N.index];
											},
											destroy : function() {
												if (this._destroyEditor) {
													this._destroyEditor();
												}
												var L = this.getEl();
												J.removeListener(L, "click");
												J.removeListener(L, "dblclick");
												J
														.removeListener(L,
																"mouseover");
												J.removeListener(L, "mouseout");
												J.removeListener(L, "keydown");
												for ( var K = 0; K < this._nodes.length; K++) {
													var M = this._nodes[K];
													if (M && M.destroy) {
														M.destroy();
													}
												}
												L.innerHTML = "";
												this._hasEvents = false;
											},
											toString : function() {
												return "TreeView " + this.id;
											},
											getNodeCount : function() {
												return this.getRoot()
														.getNodeCount();
											},
											getTreeDefinition : function() {
												return this.getRoot()
														.getNodeDefinition();
											},
											onExpand : function(K) {
											},
											onCollapse : function(K) {
											},
											setNodesProperty : function(K, M, L) {
												this.root
														.setNodesProperty(K, M);
												if (L) {
													this.root.refresh();
												}
											},
											onEventToggleHighlight : function(L) {
												var K;
												if ("node" in L
														&& L.node instanceof H.Node) {
													K = L.node;
												} else {
													if (L instanceof H.Node) {
														K = L;
													} else {
														return false;
													}
												}
												K.toggleHighlight();
												return false;
											}
										});
						var I = C.prototype;
						I.draw = I.render;
						C.nodeCount = 0;
						C.trees = [];
						C.getTree = function(L) {
							var K = C.trees[L];
							return (K) ? K : null;
						};
						C.getNode = function(L, M) {
							var K = C.getTree(L);
							return (K) ? K.getNodeByIndex(M) : null;
						};
						C.FOCUS_CLASS_NAME = "ygtvfocus";
					})();
					(function() {
						var E = A.Port(), B = A.DOM, D = E.lang;
						E.widget.Node = function(H, G, F) {
							if (H) {
								this._nodeinit(H, G, F);
							}
						};
						var C = A.namespace("apm");
						C.Node = E.widget.Node;
						A
								.extend(
										E.widget.Node,
										A.PortBase,
										{
											index : 0,
											children : null,
											tree : null,
											data : null,
											parent : null,
											depth : -1,
											expanded : false,
											multiExpand : true,
											renderHidden : false,
											childrenRendered : false,
											dynamicLoadComplete : false,
											previousSibling : null,
											nextSibling : null,
											_dynLoad : false,
											dataLoader : null,
											isLoading : false,
											hasIcon : true,
											iconMode : 0,
											nowrap : false,
											isLeaf : false,
											contentStyle : "",
											contentElId : null,
											enableHighlight : true,
											highlightState : 0,
											propagateHighlightUp : false,
											propagateHighlightDown : false,
											className : null,
											_type : "Node",
											_nodeinit : function(I, H, F) {
												E.widget.Node.superclass.constructor
														.call(this);
												this.data = {};
												this.children = [];
												this.index = E.widget.TreeView.nodeCount;
												++E.widget.TreeView.nodeCount;
												this.contentElId = "ygtvcontentel"
														+ this.index;
												if (D.isObject(I)) {
													for ( var G in I) {
														if (I.hasOwnProperty(G)) {
															if (G.charAt(0) != "_"
																	&& !D
																			.isUndefined(this[G])
																	&& !D
																			.isFunction(this[G])) {
																this[G] = I[G];
															} else {
																this.data[G] = I[G];
															}
														}
													}
												}
												if (!D.isUndefined(F)) {
													this.expanded = F;
												}
												this.publish("parentChange");
												if (H) {
													H.appendChild(this);
												}
											},
											applyParent : function(G) {
												if (!G) {
													return false;
												}
												this.tree = G.tree;
												this.parent = G;
												this.depth = G.depth + 1;
												this.tree.regNode(this);
												G.childrenRendered = false;
												for ( var H = 0, F = this.children.length; H < F; ++H) {
													this.children[H]
															.applyParent(this);
												}
												this.fire("parentChange", this);
												return true;
											},
											appendChild : function(G) {
												if (this.hasChildren()) {
													var F = this.children[this.children.length - 1];
													F.nextSibling = G;
													G.previousSibling = F;
												}
												this.children[this.children.length] = G;
												G.applyParent(this);
												if (this.childrenRendered
														&& this.expanded) {
													this.getChildrenEl().style.display = "";
												}
												return G;
											},
											appendTo : function(F) {
												return F.appendChild(this);
											},
											insertBefore : function(F) {
												var H = F.parent;
												if (H) {
													if (this.tree) {
														this.tree.popNode(this);
													}
													var G = F.isChildOf(H);
													H.children.splice(G, 0,
															this);
													if (F.previousSibling) {
														F.previousSibling.nextSibling = this;
													}
													this.previousSibling = F.previousSibling;
													this.nextSibling = F;
													F.previousSibling = this;
													this.applyParent(H);
												}
												return this;
											},
											insertAfter : function(F) {
												var H = F.parent;
												if (H) {
													if (this.tree) {
														this.tree.popNode(this);
													}
													var G = F.isChildOf(H);
													if (!F.nextSibling) {
														this.nextSibling = null;
														return this.appendTo(H);
													}
													H.children.splice(G + 1, 0,
															this);
													F.nextSibling.previousSibling = this;
													this.previousSibling = F;
													this.nextSibling = F.nextSibling;
													F.nextSibling = this;
													this.applyParent(H);
												}
												return this;
											},
											isChildOf : function(G) {
												if (G && G.children) {
													for ( var H = 0, F = G.children.length; H < F; ++H) {
														if (G.children[H] === this) {
															return H;
														}
													}
												}
												return -1;
											},
											getSiblings : function() {
												var F = this.parent.children
														.slice(0);
												for ( var G = 0; G < F.length
														&& F[G] != this; G++) {
												}
												F.splice(G, 1);
												if (F.length) {
													return F;
												}
												return null;
											},
											showChildren : function() {
												if (!this.tree.animateExpand(
														this.getChildrenEl(),
														this)) {
													if (this.hasChildren()) {
														this.getChildrenEl().style.display = "";
													}
												}
											},
											hideChildren : function() {
												if (!this.tree.animateCollapse(
														this.getChildrenEl(),
														this)) {
													this.getChildrenEl().style.display = "none";
												}
											},
											getElId : function() {
												return "ygtv" + this.index;
											},
											getChildrenElId : function() {
												return "ygtvc" + this.index;
											},
											getToggleElId : function() {
												return "ygtvt" + this.index;
											},
											getEl : function() {
												return document
														.getElementById(this
																.getElId());
											},
											getChildrenEl : function() {
												return document
														.getElementById(this
																.getChildrenElId());
											},
											getToggleEl : function() {
												return document
														.getElementById(this
																.getToggleElId());
											},
											getContentEl : function() {
												return document
														.getElementById(this.contentElId);
											},
											collapse : function() {
												if (!this.expanded) {
													return;
												}
												var F = this.tree
														.onCollapse(this);
												if (false === F) {
													return;
												}
												F = this.tree.fire("collapse",
														this);
												if (false === F) {
													return;
												}
												if (!this.getEl()) {
													this.expanded = false;
												} else {
													this.hideChildren();
													this.expanded = false;
													this.updateIcon();
												}
												F = this.tree.fire(
														"collapseComplete",
														this);
											},
											expand : function(H) {
												if (this.isLoading
														|| (this.expanded && !H)) {
													return;
												}
												var F = true;
												if (!H) {
													F = this.tree
															.onExpand(this);
													if (false === F) {
														return;
													}
													F = this.tree.fire(
															"expand", this);
												}
												if (false === F) {
													return;
												}
												if (!this.getEl()) {
													this.expanded = true;
													return;
												}
												if (!this.childrenRendered) {
													this.getChildrenEl().innerHTML = this
															.renderChildren();
												} else {
												}
												this.expanded = true;
												this.updateIcon();
												if (this.isLoading) {
													this.expanded = false;
													return;
												}
												if (!this.multiExpand) {
													var I = this.getSiblings();
													for ( var G = 0; I
															&& G < I.length; ++G) {
														if (I[G] != this
																&& I[G].expanded) {
															I[G].collapse();
														}
													}
												}
												this.showChildren();
												F = this.tree.fire(
														"expandComplete", this);
											},
											updateIcon : function() {
												if (this.hasIcon) {
													var F = this.getToggleEl();
													if (F) {
														F.className = F.className
																.replace(
																		/\bygtv(([tl][pmn]h?)|(loading))\b/gi,
																		this
																				.getStyle());
													}
												}
												F = document
														.getElementById("ygtvtableel"
																+ this.index);
												if (F) {
													if (this.expanded) {
														B
																.replaceClass(
																		F,
																		"ygtv-collapsed",
																		"ygtv-expanded");
													} else {
														B
																.replaceClass(
																		F,
																		"ygtv-expanded",
																		"ygtv-collapsed");
													}
												}
											},
											getStyle : function() {
												if (this.isLoading) {
													return "ygtvloading";
												} else {
													var G = (this.nextSibling) ? "t"
															: "l";
													var F = "n";
													if (this.hasChildren(true)
															|| (this
																	.isDynamic() && !this
																	.getIconMode())) {
														F = (this.expanded) ? "m"
																: "p";
													}
													return "ygtv" + G + F;
												}
											},
											getHoverStyle : function() {
												var F = this.getStyle();
												if (this.hasChildren(true)
														&& !this.isLoading) {
													F += "h";
												}
												return F;
											},
											expandAll : function() {
												var F = this.children.length;
												for ( var G = 0; G < F; ++G) {
													var H = this.children[G];
													if (H.isDynamic()) {
														break;
													} else {
														if (!H.multiExpand) {
															break;
														} else {
															H.expand();
															H.expandAll();
														}
													}
												}
											},
											collapseAll : function() {
												for ( var F = 0; F < this.children.length; ++F) {
													this.children[F].collapse();
													this.children[F]
															.collapseAll();
												}
											},
											setDynamicLoad : function(F, G) {
												if (F) {
													this.dataLoader = F;
													this._dynLoad = true;
												} else {
													this.dataLoader = null;
													this._dynLoad = false;
												}
												if (G) {
													this.iconMode = G;
												}
											},
											isRoot : function() {
												return (this == this.tree.root);
											},
											isDynamic : function() {
												if (this.isLeaf) {
													return false;
												} else {
													return (!this.isRoot() && (this._dynLoad || this.tree.root._dynLoad));
												}
											},
											getIconMode : function() {
												return (this.iconMode || this.tree.root.iconMode);
											},
											hasChildren : function(F) {
												if (this.isLeaf) {
													return false;
												} else {
													return (this.children.length > 0 || (F
															&& this.isDynamic() && !this.dynamicLoadComplete));
												}
											},
											toggle : function() {
												if (!this.tree.locked
														&& (this
																.hasChildren(true) || this
																.isDynamic())) {
													if (this.expanded) {
														this.collapse();
													} else {
														this.expand();
													}
												}
											},
											getHtml : function() {
												this.childrenRendered = false;
												return [
														'<div class="ygtvitem" id="',
														this.getElId(), '">',
														this.getNodeHtml(),
														this.getChildrenHtml(),
														"</div>" ].join("");
											},
											getChildrenHtml : function() {
												var F = [];
												F[F.length] = '<div class="ygtvchildren" id="' + this
														.getChildrenElId() + '"';
												if (!this.expanded
														|| !this.hasChildren()) {
													F[F.length] = ' style="display:none;"';
												}
												F[F.length] = ">";
												if ((this.hasChildren(true) && this.expanded)
														|| (this.renderHidden && !this
																.isDynamic())) {
													F[F.length] = this
															.renderChildren();
												}
												F[F.length] = "</div>";
												return F.join("");
											},
											renderChildren : function() {
												var F = this;
												if (this.isDynamic()
														&& !this.dynamicLoadComplete) {
													this.isLoading = true;
													this.tree.locked = true;
													if (this.dataLoader) {
														setTimeout(
																function() {
																	F
																			.dataLoader(
																					F,
																					function() {
																						F
																								.loadComplete();
																					});
																}, 10);
													} else {
														if (this.tree.root.dataLoader) {
															setTimeout(
																	function() {
																		F.tree.root
																				.dataLoader(
																						F,
																						function() {
																							F
																									.loadComplete();
																						});
																	}, 10);
														} else {
															return "Error: data loader not found or not specified.";
														}
													}
													return "";
												} else {
													return this
															.completeRender();
												}
											},
											completeRender : function() {
												var G = [];
												for ( var F = 0; F < this.children.length; ++F) {
													G[G.length] = this.children[F]
															.getHtml();
												}
												this.childrenRendered = true;
												return G.join("");
											},
											loadComplete : function() {
												this.getChildrenEl().innerHTML = this
														.completeRender();
												if (this.propagateHighlightDown) {
													if (this.highlightState === 1
															&& !this.tree.singleNodeHighlight) {
														for ( var F = 0; F < this.children.length; F++) {
															this.children[F]
																	.highlight(true);
														}
													} else {
														if (this.highlightState === 0
																|| this.tree.singleNodeHighlight) {
															for (F = 0; F < this.children.length; F++) {
																this.children[F]
																		.unhighlight(true);
															}
														}
													}
												}
												this.dynamicLoadComplete = true;
												this.isLoading = false;
												this.expand(true);
												this.tree.locked = false;
											},
											getAncestor : function(G) {
												if (G >= this.depth || G < 0) {
													return null;
												}
												var F = this.parent;
												while (F.depth > G) {
													F = F.parent;
												}
												return F;
											},
											getDepthStyle : function(F) {
												return (this.getAncestor(F).nextSibling) ? "ygtvdepthcell"
														: "ygtvblankdepthcell";
											},
											getNodeHtml : function() {
												var G = [];
												G[G.length] = '<table id="ygtvtableel'
														+ this.index
														+ '" border="0" cellpadding="0" cellspacing="0" class="ygtvtable ygtvdepth'
														+ this.depth;
												G[G.length] = " ygtv-"
														+ (this.expanded ? "expanded"
																: "collapsed");
												if (this.enableHighlight) {
													G[G.length] = " ygtv-highlight"
															+ this.highlightState;
												}
												if (this.className) {
													G[G.length] = " "
															+ this.className;
												}
												G[G.length] = '"><tr class="ygtvrow">';
												for ( var F = 0; F < this.depth; ++F) {
													G[G.length] = '<td class="ygtvcell ' + this
															.getDepthStyle(F) + '"><div class="ygtvspacer"></div></td>';
												}
												if (this.hasIcon) {
													G[G.length] = '<td id="' + this
															.getToggleElId();
													G[G.length] = '" class="ygtvcell ';
													G[G.length] = this
															.getStyle();
													G[G.length] = '"><a href="#" class="ygtvspacer">&#160;</a></td>';
												}
												G[G.length] = '<td id="' + this.contentElId;
												G[G.length] = '" class="ygtvcell ';
												G[G.length] = this.contentStyle + ' ygtvcontent" ';
												G[G.length] = (this.nowrap) ? ' nowrap="nowrap" '
														: "";
												G[G.length] = " >";
												G[G.length] = this
														.getContentHtml();
												G[G.length] = "</td></tr></table>";
												return G.join("");
											},
											getContentHtml : function() {
												return "";
											},
											refresh : function() {
												this.getChildrenEl().innerHTML = this
														.completeRender();
												if (this.hasIcon) {
													var F = this.getToggleEl();
													if (F) {
														F.className = F.className
																.replace(
																		/\bygtv[lt][nmp]h*\b/gi,
																		this
																				.getStyle());
													}
												}
											},
											toString : function() {
												return this._type + " ("
														+ this.index + ")";
											},
											_focusHighlightedItems : [],
											_focusedItem : null,
											_canHaveFocus : function() {
												return this.getEl()
														.getElementsByTagName(
																"a").length > 0;
											},
											_removeFocus : function() {
												if (this._focusedItem) {
													A.Event.detach("blur",
															this._focusedItem);
													this._focusedItem = null;
												}
												var F;
												while ((F = this._focusHighlightedItems
														.shift())) {
													B
															.removeClass(
																	F,
																	E.widget.TreeView.FOCUS_CLASS_NAME);
												}
											},
											focus : function() {
												var J = false, G = this;
												if (this.tree.currentFocus) {
													this.tree.currentFocus
															._removeFocus();
												}
												var I = function(K) {
													if (K.parent) {
														I(K.parent);
														K.parent.expand();
													}
												};
												I(this);
												var F = new A.Node(
														G.getEl().firstChild);
												var H = F.all("td");
												H
														.each(function(L) {
															if ((/ygtv(([tl][pmn]h?)|(content))/)
																	.test(L
																			.get("className"))) {
																L
																		.addClass(E.widget.TreeView.FOCUS_CLASS_NAME);
																if (!J) {
																	var K = L
																			.one(
																					"a",
																					null,
																					true);
																	if (K) {
																		var M = K._node;
																		K
																				.focus();
																		G._focusedItem = M;
																		K
																				.on(
																						"blur",
																						function() {
																							G.tree
																									.fire(
																											"focusChanged",
																											{
																												oldNode : G.tree.currentFocus,
																												newNode : null
																											});
																							G.tree.currentFocus = null;
																							G
																									._removeFocus();
																						});
																		J = true;
																	}
																}
																G._focusHighlightedItems
																		.push(L._node);
															}
														});
												if (J) {
													this.tree
															.fire(
																	"focusChanged",
																	{
																		oldNode : this.tree.currentFocus,
																		newNode : this
																	});
													this.tree.currentFocus = this;
												} else {
													this.tree
															.fire(
																	"focusChanged",
																	{
																		oldNode : G.tree.currentFocus,
																		newNode : null
																	});
													this.tree.currentFocus = null;
													this._removeFocus();
												}
												return J;
											},
											getNodeCount : function() {
												for ( var F = 0, G = 0; F < this.children.length; F++) {
													G += this.children[F]
															.getNodeCount();
												}
												return G + 1;
											},
											getNodeDefinition : function() {
												if (this.isDynamic()) {
													return false;
												}
												var I, F = D.merge(this.data), H = [];
												if (this.expanded) {
													F.expanded = this.expanded;
												}
												if (!this.multiExpand) {
													F.multiExpand = this.multiExpand;
												}
												if (!this.renderHidden) {
													F.renderHidden = this.renderHidden;
												}
												if (!this.hasIcon) {
													F.hasIcon = this.hasIcon;
												}
												if (this.nowrap) {
													F.nowrap = this.nowrap;
												}
												if (this.className) {
													F.className = this.className;
												}
												if (this.editable) {
													F.editable = this.editable;
												}
												if (this.enableHighlight) {
													F.enableHighlight = this.enableHighlight;
												}
												if (this.highlightState) {
													F.highlightState = this.highlightState;
												}
												if (this.propagateHighlightUp) {
													F.propagateHighlightUp = this.propagateHighlightUp;
												}
												if (this.propagateHighlightDown) {
													F.propagateHighlightDown = this.propagateHighlightDown;
												}
												F.type = this._type;
												for ( var G = 0; G < this.children.length; G++) {
													I = this.children[G]
															.getNodeDefinition();
													if (I === false) {
														return false;
													}
													H.push(I);
												}
												if (H.length) {
													F.children = H;
												}
												return F;
											},
											getToggleLink : function() {
												return "return false;";
											},
											setNodesProperty : function(F, I, H) {
												if (F.charAt(0) != "_"
														&& !D
																.isUndefined(this[F])
														&& !D
																.isFunction(this[F])) {
													this[F] = I;
												} else {
													this.data[F] = I;
												}
												for ( var G = 0; G < this.children.length; G++) {
													this.children[G]
															.setNodesProperty(
																	F, I);
												}
												if (H) {
													this.refresh();
												}
											},
											toggleHighlight : function() {
												if (this.enableHighlight) {
													if (this.highlightState == 1) {
														this.unhighlight();
													} else {
														this.highlight();
													}
												}
											},
											highlight : function(G) {
												if (this.enableHighlight) {
													if (this.tree.singleNodeHighlight) {
														if (this.tree._currentlyHighlighted) {
															this.tree._currentlyHighlighted
																	.unhighlight(G);
														}
														this.tree._currentlyHighlighted = this;
													}
													this.highlightState = 1;
													this
															._setHighlightClassName();
													if (!this.tree.singleNodeHighlight) {
														if (this.propagateHighlightDown) {
															for ( var F = 0; F < this.children.length; F++) {
																this.children[F]
																		.highlight(true);
															}
														}
														if (this.propagateHighlightUp) {
															if (this.parent) {
																this.parent
																		._childrenHighlighted();
															}
														}
													}
													if (!G) {
														this.tree
																.fire(
																		"highlightEvent",
																		this);
													}
												}
											},
											unhighlight : function(G) {
												if (this.enableHighlight) {
													this.tree._currentlyHighlighted = null;
													this.highlightState = 0;
													this
															._setHighlightClassName();
													if (!this.tree.singleNodeHighlight) {
														if (this.propagateHighlightDown) {
															for ( var F = 0; F < this.children.length; F++) {
																this.children[F]
																		.unhighlight(true);
															}
														}
														if (this.propagateHighlightUp) {
															if (this.parent) {
																this.parent
																		._childrenHighlighted();
															}
														}
													}
													if (!G) {
														this.tree
																.fire(
																		"highlightEvent",
																		this);
													}
												}
											},
											_childrenHighlighted : function() {
												var H = false, G = false;
												if (this.enableHighlight) {
													for ( var F = 0; F < this.children.length; F++) {
														switch (this.children[F].highlightState) {
														case 0:
															G = true;
															break;
														case 1:
															H = true;
															break;
														case 2:
															H = G = true;
															break;
														}
													}
													if (H && G) {
														this.highlightState = 2;
													} else {
														if (H) {
															this.highlightState = 1;
														} else {
															this.highlightState = 0;
														}
													}
													this
															._setHighlightClassName();
													if (this.propagateHighlightUp) {
														if (this.parent) {
															this.parent
																	._childrenHighlighted();
														}
													}
												}
											},
											_setHighlightClassName : function() {
												var F = document
														.getElementById("ygtvtableel"
																+ this.index);
												if (F) {
													F.className = F.className
															.replace(
																	/\bygtv-highlight\d\b/gi,
																	"ygtv-highlight"
																			+ this.highlightState);
												}
											}
										});
					})();
					(function() {
						var D = A.Port();
						var B = function(E) {
							this._nodeinit(null, null, true);
							this.tree = E;
						};
						D.widget.RootNode = B;
						var C = A.namespace("apm");
						C.RootNode = D.widget.RootNode;
						A
								.extend(
										B,
										D.widget.Node,
										{
											_type : "RootNode",
											getNodeHtml : function() {
												return "";
											},
											toString : function() {
												return this._type;
											},
											loadComplete : function() {
												this.tree.draw();
											},
											getNodeCount : function() {
												for ( var E = 0, F = 0; E < this.children.length; E++) {
													F += this.children[E]
															.getNodeCount();
												}
												return F;
											},
											getNodeDefinition : function() {
												for ( var G, E = [], F = 0; F < this.children.length; F++) {
													G = this.children[F]
															.getNodeDefinition();
													if (G === false) {
														return false;
													}
													E.push(G);
												}
												return E;
											},
											collapse : function() {
											},
											expand : function() {
											},
											getSiblings : function() {
												return null;
											},
											focus : function() {
											}
										});
					})();
					(function() {
						var D = A.Port(), C = D.lang;
						D.widget.TextNode = function(G, F, E) {
							if (G) {
								if (C.isString(G)) {
									G = {
										label : G
									};
								}
								this._nodeinit(G, F, E);
								this.setUpLabel(G);
							}
						};
						var B = A.namespace("apm");
						B.TextNode = D.widget.TextNode;
						A
								.extend(
										D.widget.TextNode,
										D.widget.Node,
										{
											labelStyle : "ygtvlabel",
											labelElId : null,
											label : null,
											title : null,
											href : null,
											target : "_self",
											_type : "TextNode",
											setUpLabel : function(E) {
												if (C.isString(E)) {
													E = {
														label : E
													};
												} else {
													if (E.style) {
														this.labelStyle = E.style;
													}
												}
												this.label = E.label;
												this.labelElId = "ygtvlabelel"
														+ this.index;
											},
											getLabelEl : function() {
												return document
														.getElementById(this.labelElId);
											},
											getContentHtml : function() {
												var E = [];
												E[E.length] = this.href ? "<a"
														: "<span";
												E[E.length] = ' id="' + this.labelElId + '"';
												E[E.length] = ' class="' + this.labelStyle + '"';
												if (this.href) {
													E[E.length] = ' href="' + this.href + '"';
													E[E.length] = ' target="' + this.target + '"';
												}
												if (this.title) {
													E[E.length] = ' title="' + this.title + '"';
												}
												E[E.length] = " >";
												E[E.length] = this.label;
												E[E.length] = this.href ? "</a>"
														: "</span>";
												return E.join("");
											},
											getNodeDefinition : function() {
												var E = D.widget.TextNode.superclass.getNodeDefinition
														.call(this);
												if (E === false) {
													return false;
												}
												E.label = this.label;
												if (this.labelStyle != "ygtvlabel") {
													E.style = this.labelStyle;
												}
												if (this.title) {
													E.title = this.title;
												}
												if (this.href) {
													E.href = this.href;
												}
												if (this.target != "_self") {
													E.target = this.target;
												}
												return E;
											},
											toString : function() {
												return D.widget.TextNode.superclass.toString
														.call(this)
														+ ": " + this.label;
											},
											onLabelClick : function() {
												return false;
											},
											refresh : function() {
												D.widget.TextNode.superclass.refresh
														.call(this);
												var E = this.getLabelEl();
												E.innerHTML = this.label;
												if (E.tagName.toUpperCase() == "A") {
													E.href = this.href;
													E.target = this.target;
												}
											}
										});
					})();
					(function() {
						var C = A.Port();
						C.widget.MenuNode = function(F, E, D) {
							C.widget.MenuNode.superclass.constructor.call(this,
									F, E, D);
							this.multiExpand = false;
						};
						var B = A.namespace("apm");
						B.MenuNode = C.widget.MenuNode;
						A.extend(C.widget.MenuNode, C.widget.TextNode, {
							_type : "MenuNode"
						});
					})();
					(function() {
						var D = A.Port(), C = D.lang;
						D.widget.HTMLNode = function(H, G, F, E) {
							if (H) {
								this.init(H, G, F);
								this.initContent(H, E);
							}
						};
						var B = A.namespace("apm");
						B.HTMLNode = D.widget.HTMLNode;
						A
								.extend(
										D.widget.HTMLNode,
										D.widget.Node,
										{
											contentStyle : "ygtvhtml",
											html : null,
											_type : "HTMLNode",
											initContent : function(F, E) {
												this.setHtml(F);
												this.contentElId = "ygtvcontentel"
														+ this.index;
												if (!C.isUndefined(E)) {
													this.hasIcon = E;
												}
											},
											setHtml : function(F) {
												this.html = (typeof F === "string") ? F
														: F.html;
												var E = this.getContentEl();
												if (E) {
													E.innerHTML = this.html;
												}
											},
											getContentHtml : function() {
												return this.html;
											},
											getNodeDefinition : function() {
												var E = D.widget.HTMLNode.superclass.getNodeDefinition
														.call(this);
												if (E === false) {
													return false;
												}
												E.html = this.html;
												return E;
											}
										});
					})();
					(function() {
						var I = A.Port(), F = I.util.Dom, G = I.lang, C = I.util.Event, E = I.widget.TreeView, D = E.prototype;
						E.editorData = {
							active : false,
							whoHasIt : null,
							nodeType : null,
							editorPanel : null,
							inputContainer : null,
							buttonsContainer : null,
							node : null,
							saveOnEnter : true,
							oldValue : undefined
						};
						D.validator = null;
						D._initEditor = function() {
						};
						D._nodeEditing = function(O) {
							if (O.fillEditorContainer && O.editable) {
								var K, M, N, L, J = E.editorData;
								J.active = true;
								J.whoHasIt = this;
								if (!J.nodeType) {
									J.editorPanel = K = document.body
											.appendChild(document
													.createElement("div"));
									F.addClass(K, "ygtv-label-editor");
									N = J.buttonsContainer = K
											.appendChild(document
													.createElement("div"));
									F.addClass(N, "ygtv-button-container");
									L = N.appendChild(document
											.createElement("button"));
									F.addClass(L, "ygtvok");
									L.innerHTML = " ";
									L = N.appendChild(document
											.createElement("button"));
									F.addClass(L, "ygtvcancel");
									L.innerHTML = " ";
									A
											.on(
													"click",
													function(Q) {
														var R = Q.target, S = R._node, P = E.editorData.node;
														if (R
																.hasClass("ygtvok")) {
															Q.halt();
															this
																	._closeEditor(true);
														}
														if (R
																.hasClass("ygtvcancel")) {
															Q.halt();
															this
																	._closeEditor(false);
														}
													}, N, this);
									J.inputContainer = K.appendChild(document
											.createElement("div"));
									F.addClass(J.inputContainer, "ygtv-input");
									A
											.on(
													"keydown",
													function(R) {
														var Q = E.editorData, P = A.TreeView.KEY;
														switch (R.keyCode) {
														case P.ENTER:
															R.halt();
															if (Q.saveOnEnter) {
																this
																		._closeEditor(true);
															}
															break;
														case P.ESCAPE:
															R.halt();
															this
																	._closeEditor(false);
															break;
														}
													}, K, this);
								} else {
									K = J.editorPanel;
								}
								J.node = O;
								if (J.nodeType) {
									F.removeClass(K, "ygtv-edit-" + J.nodeType);
								}
								F.addClass(K, " ygtv-edit-" + O._type);
								M = F.getXY(O.getContentEl());
								F.setStyle(K, "left", M[0] + "px");
								F.setStyle(K, "top", M[1] + "px");
								F.setStyle(K, "display", "block");
								K.focus();
								O.fillEditorContainer(J);
								return true;
							}
						};
						D.onEventEditNode = function(J) {
							if (J instanceof I.widget.Node) {
								J.editNode();
							} else {
								if (J.node instanceof I.widget.Node) {
									J.node.editNode();
								}
							}
						};
						D._closeEditor = function(L) {
							var J = E.editorData, K = J.node, M = true;
							if (L) {
								M = (J.node.saveEditorValue(J) !== false);
							} else {
								this.fire("editorCancelEvent", K);
							}
							if (M) {
								F.setStyle(J.editorPanel, "display", "none");
								J.active = false;
								K.focus();
							}
						};
						D._destroyEditor = function() {
							var J = E.editorData;
							if (J && J.nodeType
									&& (!J.active || J.whoHasIt === this)) {
								A.Event.detach("keydown", J.editorPanel);
								A.Event.detach("click", J.buttonContainer);
								J.node.destroyEditorContents(J);
								document.body.removeChild(J.editorPanel);
								J.nodeType = J.editorPanel = J.inputContainer = J.buttonsContainer = J.whoHasIt = J.node = null;
								J.active = false;
							}
						};
						var H = I.widget.Node.prototype;
						H.editable = false;
						H.editNode = function() {
							this.tree._nodeEditing(this);
						};
						H.fillEditorContainer = null;
						H.destroyEditorContents = function(J) {
							C.purgeElement(J.inputContainer, true);
							J.inputContainer.innerHTML = "";
						};
						H.saveEditorValue = function(J) {
							var M = J.node, N, L = M.tree.validator;
							N = this.getEditorValue(J);
							if (G.isFunction(L)) {
								N = L(N, J.oldValue, M);
								if (G.isUndefined(N)) {
									return false;
								}
							}
							var K = this.tree.fire("editorSaveEvent", {
								newValue : N,
								oldValue : J.oldValue,
								node : M
							});
							if (K !== false) {
								this.displayEditedValue(N, J);
							}
						};
						H.getEditorValue = function(J) {
						};
						H.displayEditedValue = function(K, J) {
						};
						var B = I.widget.TextNode.prototype;
						B.fillEditorContainer = function(K) {
							var J;
							if (K.nodeType != this._type) {
								K.nodeType = this._type;
								K.saveOnEnter = true;
								K.node.destroyEditorContents(K);
								K.inputElement = J = K.inputContainer
										.appendChild(document
												.createElement("input"));
							} else {
								J = K.inputElement;
							}
							K.oldValue = this.label;
							J.value = this.label;
							J.focus();
							J.select();
						};
						B.getEditorValue = function(J) {
							return J.inputElement.value;
						};
						B.displayEditedValue = function(L, J) {
							var K = J.node;
							K.label = L;
							K.getLabelEl().innerHTML = L;
						};
						B.destroyEditorContents = function(J) {
							J.inputContainer.innerHTML = "";
						};
					})();
				}, "gallery-2009.11.19-20", {
					requires : [ "node-base", "gallery-port" ]
				});