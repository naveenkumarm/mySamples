YUI.add("gallery-port", function(C) {
	var B = {
		lang : C.Lang,
		extend : C.extend,
		util : {
			Dom : C.DOM,
			Event : {
				on : C.Event.nativeAdd,
				getTarget : function(E) {
					return E.target;
				},
				stopEvent : function(E) {
					E.halt();
				},
				stopPropagation : function(E) {
					E.stopPropagation();
				},
				preventDefault : function(E) {
					E.preventDefault();
				}
			},
			Element : C.Base
		},
		getAttributes : function() {
		}
	}, D, A;
	B["log"] = C["log"];
	B.util.Dom.get = function(E) {
		if (C.Lang.isString(E)) {
			E = "#" + E;
		}
		return C.get(E);
	};
	B.util.Dom.getChildren = function(E) {
		return C.Selector.query("> *", E);
	};
	D = function(E) {
		return B;
	};
	C.Port = D;
	A = function(E) {
		this._lazyAddAttrs = false;
		this._portAttrs = E;
		A.superclass.constructor.apply(this, arguments);
	};
	A.NAME = "PortBase";
	A.ATTRS = {
		node : {
			setter : function(E) {
				var F = C.get(E);
				return F.item(0);
			}
		},
		element : {
			getter : function() {
				return this.get("node");
			},
			setter : function(E) {
				this.set("node", E);
				return this.get("node");
			}
		}
	};
	C.extend(A, C.Base, {
		DOM_EVENTS : {
			"click" : true,
			"dblclick" : true,
			"keydown" : true,
			"keypress" : true,
			"keyup" : true,
			"mousedown" : true,
			"mousemove" : true,
			"mouseout" : true,
			"mouseover" : true,
			"mouseup" : true,
			"focus" : true,
			"blur" : true,
			"submit" : true,
			"change" : true
		},
		initializer : function() {
			this.initAttributes(this._portAttrs);
		},
		initAttributes : function() {
		},
		setAttributeConfig : function(F, E) {
			if (E.method) {
				E.setter = E.method;
				delete E.method;
			}
			this.addAttr(F, E);
		},
		addListener : function(G, H, E, F) {
			if (E) {
				if (F) {
					H = C.rbind(H, F, E);
				} else {
					H = C.bind(H, E);
				}
			}
			if (this.DOM_EVENTS[G]) {
				this.get("node").on(G, H);
			} else {
				this.on(G, H);
			}
		},
		getElementsByClassName : function(F, E) {
			var G = E + " ." + F;
			return this.get("node").query(G) || [];
		},
		getElementsByTagName : function(E) {
			var F = this.get("node");
			return ((F) ? F.query(E) : []);
		},
		addClass : function(E) {
			var F = this.get("node");
			if (!F) {
				return false;
			}
			return F.addClass(E);
		},
		removeClass : function(E) {
			var F = this.get("node");
			if (!F) {
				return false;
			}
			return F.removeClass(E);
		},
		hasClass : function(E) {
			var F = this.get("node");
			if (!F) {
				return false;
			}
			return F.hasClass(E);
		},
		appendTo : function(E) {
			C.get(E).appendChild(this.get("node"));
		},
		fireEvent : function(G, F) {
			if (F.target) {
				delete F.target;
			}
			if (F.ev) { 
				F.ev = new C.DOMEventFacade(F.ev);
			}
			var E = false, H;
			this.publish(G, {
				defaultFn : function() {
					E = true;
				}
			});
			H = this.fire(G, F);
			return E;
		}
	});
	C.PortBase = A;
}, "gallery-2009.11.02-20", {
	requires : [ "base", "node" ]
});